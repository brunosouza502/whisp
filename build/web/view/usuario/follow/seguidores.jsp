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
        <h1>Bem-vindo, <c:out value="${usuario.nome}"/>!</h1>


        <c:forEach var="f" items="${followers}">
            <a href="${pageContext.servletContext.contextPath}/usuario/read?id=${f.idSeguidor}">
                <c:out value="${f.nomeSeguidor}"/></a><br>
        </c:forEach>
         
                <br>
        <a href="${pageContext.servletContext.contextPath}/usuario">Voltar</a>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>