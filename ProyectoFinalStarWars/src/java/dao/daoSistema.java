/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import modelo.Comentarios;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
public class daoSistema {
    public static Connection conectarBD() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mijuego?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "");
    }
    
    public static void desconectarBD(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
    
    /*
    PARTE DEL USUARIO
    */
    
    public static void insertarUsuario(Usuario u) throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into usuarios (usuario, clave, gmail, admin, experienciaAct, monedas, nivel, recordExp, partidasJugadas) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        consulta.setString(1, u.getUsuario());
        consulta.setString(2, u.getClave());
        consulta.setString(3, u.getEmail());
        consulta.setBoolean(4, u.isAdmin());
        consulta.setInt(5, u.getExperienciaAct());
        consulta.setInt(6, u.getMonedas());
        consulta.setInt(7, u.getNivel());
        consulta.setInt(8, u.getRecordExp());
        consulta.setInt(9, u.getPartidasJugadas());
        consulta.execute();
        desconectarBD(con);
    }
    public static Usuario obtenerUsuario(String usuario)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("select * from usuarios where usuario=?");
        consulta.setString(1, usuario);
        ResultSet rs = consulta.executeQuery();
        Usuario u = null;
        if(rs.next()){
            String clave = rs.getString("clave");
            String email = rs.getString("gmail");
            boolean admin = rs.getBoolean("admin");
            int experienciaAct = rs.getInt("experienciaAct");
            int monedas = rs.getInt("monedas");
            int nivel = rs.getInt("nivel");
            int recordExp = rs.getInt("recordExp");
            int partidasJugadas = rs.getInt("partidasJugadas");
            u = new Usuario(usuario, clave, email, admin, experienciaAct, monedas, nivel, recordExp, partidasJugadas);
        }
        desconectarBD(con);
        return u;
    }
    public static void eliminarUsuario(Usuario u)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("delete from usuarios where usuario=?");
        consulta.setString(1, u.getUsuario());
        consulta.executeUpdate();
        desconectarBD(con);
    }
    
    /*
        Parte del comentarios
    */
    public static void añadirComentario(Usuario uDest, String miUsuario, String coment)throws SQLException{//Usuario al que escribimos el comentario
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into comentarios (usuarioRemitente, usuarioDestinatario, fecha, meGustas, comentario) values (?, ?, ?, ?, ?)");
        consulta.setString(1, miUsuario);
        consulta.setString(2, uDest.getUsuario());
        consulta.setTimestamp(3, Timestamp.from(Instant.MIN));//Cogemos el instante de tiempo del momento
        consulta.setInt(4, 0);
        consulta.setString(5, coment);
        
    }
    public static int obtenerMegustaComentario(int idComentario)throws SQLException{
        int meGustas = 0;
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("select meGustas from comentarios where idComentario = ?");
        consulta.setInt(1, idComentario);
        ResultSet rs = consulta.executeQuery();
        if(rs.next()){
            meGustas = rs.getInt("meGustas");
        }
        
        return meGustas;
    }
    
    public static void agregarMegusta(int idComentario)throws SQLException{
        Connection con = conectarBD();
        int meGustasAct = obtenerMegustaComentario(idComentario);
        PreparedStatement consulta = con.prepareStatement("update comentarios set megusta = ?");
        
    }
    public static void eliminarMegusta(Usuario u){
    
    }
    
}
