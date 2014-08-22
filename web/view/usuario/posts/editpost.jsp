<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.usuario}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <h1>Bem-vindo, <c:out value="${usuario.nome}"/>!</h1>
        Editar post: id: <c:out value="${postedit.idPost}"/>
        <form action="${pageContext.servletContext.contextPath}/post/update" method="POST">
            <textarea name="editpost"><c:out value="${postedit.texto}"/></textarea> <br>
            <input type="hidden" name="postid" value="${postedit.idPost}">
            <input type="submit" value="Editar">
        </form>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>
