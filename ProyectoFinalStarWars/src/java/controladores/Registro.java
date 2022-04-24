/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.daoSistema;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
@WebServlet(name = "registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = null;
        BufferedReader bfru = new BufferedReader(new InputStreamReader(request.getPart("usuario").getInputStream()));
        usuario = bfru.readLine();
        String password = null;
        BufferedReader bfrp = new BufferedReader(new InputStreamReader(request.getPart("password").getInputStream()));
        password = bfrp.readLine();
        String passwordr = null;
        BufferedReader bfrpr = new BufferedReader(new InputStreamReader(request.getPart("passwordr").getInputStream()));
        passwordr = bfrpr.readLine();
        String email = null;
        BufferedReader bfre = new BufferedReader(new InputStreamReader(request.getPart("email").getInputStream()));
        email = bfre.readLine();
        
        /*
         * Subimos el fichero
        */
        Part fichero = request.getPart("imgUsuario");
        String nombreFichero = fichero.getSubmittedFileName();
        InputStream datosFichero = fichero.getInputStream();
        String path = getServletContext().getRealPath("imgUsuarios");
        String ruta = path+ "/" + usuario;
        FileOutputStream fos = new FileOutputStream(ruta);
        while (datosFichero.available() > 0) {
            fos.write(datosFichero.read());
        }
        
        /*
         * Validación de los campos
         */
        String error = null;
        if(!usuario.isEmpty() || usuario != null){
            if(usuario.length() > 10){
                error = "El usuario no puede superar las 10 letras";
            }else{
                //Comprobacion contraseñas
                if(!passwordr.isEmpty() || passwordr != null || !password.isEmpty() || password != null){
                    if(password.equals(passwordr)){
                        if(comprobarEmail(email)){//Si el email da bien
                            Usuario u = new Usuario(usuario, password, email);
                            try {
                                daoSistema.insertarUsuario(u);//Subimos el usuario a la base de datos
                            } catch (SQLException ex) {
                                if (ex.getMessage().contains("Duplicate entry")) {//Si el usuario ya existe
                                    error = "Ya existe el usuario " + usuario;
                                } else {
                                    error = "Error al insertar usuario (" + ex.getMessage() + ")";
                                }
                            }
                        }else{
                            error = "Error en el email";
                        }
                    }else{
                        error = "Las contraseñas no coinciden";
                    }
                }else{
                    error = "Los campos de contraseñas no pueden estar vacíos";
                }
                
            }
        }else{
            error = "El usuario no puede estar vacio";
        }
        if (error == null) {//Si no hay error
            String mensaje = URLEncoder.encode("Bienvenido al imperio de los juegos " 
                    + usuario, "latin1");
            response.sendRedirect(response.encodeRedirectURL("inicio.jsp?mensaje="
                    + mensaje));
            return;
        } else {
            request.setAttribute("usuario", usuario);
            request.setAttribute("password", password);
            request.setAttribute("passwordr", passwordr);
            request.setAttribute("email", email);
            //Enviamos el error
            request.setAttribute("error", error);
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
        
    }
    public boolean comprobarEmail(String email){
    boolean resp = true;
    
    return resp;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
