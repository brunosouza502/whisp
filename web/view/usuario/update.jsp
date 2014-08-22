<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.usuario}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <h1>Edição do usuário <c:out value="${usuario.nome}"/></h1>

        <form action="${pageContext.servletContext.contextPath}/usuario/update" method="POST">
            <label>ID:</label><br>
            <input type="text" name="id_disabled" value="${usuario.id}" disabled><br><br>

            <label>Login:</label><br>
            <input type="text" name="login" value="${usuario.login}"><br><br>

            <label>Senha:</label><br>
            <input type="password" name="senha"><br><br>

            <label>Nome:</label><br>
            <input type="text" name="nome" value="${usuario.nome}"><br><br>

            <label>Data de nascimento:</label><br>
            <input type="text" name="nascimento" value="${usuario.nascimento}"><br><br>

            <input type="hidden" name="id" value="${usuario.id}">

            <input type="submit" value="Enviar">
        </form>
            
            <a href="${pageContext.servletContext.contextPath}/usuario/delete?id=${usuario.id}">Excluir conta</a>
        <h1><a href="${pageContext.servletContext.contextPath}/">Voltar</a></h1>
    </body>
</html>