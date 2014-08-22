<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <h1>Cadastrar usuário</h1>

        <form action="${pageContext.servletContext.contextPath}/usuario/create" method="POST">
            <label>Login:</label><br>
            <input type="text" name="login"><br><br>

            <label>Senha:</label><br>
            <input type="password" name="senha"><br><br>
            
            <label>Sexo</label><br>
            <input type="radio" name="gender" value="Masculino"> Masculino
            <input type="radio" name="gender" value="Feminino"> Feminino<br><br>
            
            <label>Nome de exibição:</label><br>
            <input type="text" name="nome"><br><br>

            <label>Data de nascimento:</label><br>
            <input type="date" name="nascimento"><br><br>
            
            <label>Descrição sobre você:</label><br>
            <textarea rows="4" cols="50" name="desc"></textarea><br><br>

            <input type="submit" value="Enviar">
            
        </form>
            
            <form action="FileUploadServlet" method="post" enctype="multipart/form-data">
                Select File to Upload:<input type="file" name="fileName">
                <br>
                <input type="submit" value="Upload">
            </form>

        <h1><a href="${pageContext.servletContext.contextPath}/usuario">Voltar</a></h1>
    </body>
</html>