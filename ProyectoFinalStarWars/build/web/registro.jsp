<%-- 
    Document   : registro
    Created on : 11-abr-2022, 13:26:06
    Author     : rcane
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- OFFCANVAS -->
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Registro</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="src/registro.css">
    <link rel="stylesheet" href="src/TyF.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    
</head>    
<body>
    <header>
      <div class="header">
        <div class="titulo">
          <h1>registrate joven aprendiz</h1>
        </div>
        <div class="home">
            <a href="index.html"><i class="bi bi-box-arrow-left"></i></a>
        </div>
      </div>ç
      <script>
            <c:if test="${!(empty param.media)}">
                alert("${param.media}");
            </c:if>
       </script>
    </header> 
    <section class="registro">
        <div class="formReg">
            <form action="Registro" enctype="multipart/form-data" method="POST">
                <div class="form-group">
                    <label for="usuario">Usuario:</label>
                    <br>
                    <input type="text" name="usuario" id="usuario" value="${usuario}">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <br>
                    <input type="email" name="email" id="email" value="${email}">
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <br>
                    <input type="password" name="password" id="password" value="${password}">
                </div>
                <div class="form-group">
                    <label for="passwordr">Repetir Contraseña:</label>
                    <br>
                    <input type="password" name="passwordr" id="passwordr" value="${passwordr}">
                </div>
                <div class="form-group inputFileGroup">
                    <label for="imgUsuario">Imagen Perfil:</label>
                    <br>
                    <p class="inputFileDisp"><input type="file" class="inputFile" name="imgUsuario" id="imgUsuario"></p>
                </div>
                <br>
                <input type="submit" class="btn-danger"value="Enviar">
                <div class="error" ${empty error?"hidden":""}>
                    <h4>${error}</h4>
                </div>
            </form>
        </div>
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="linkedin">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
