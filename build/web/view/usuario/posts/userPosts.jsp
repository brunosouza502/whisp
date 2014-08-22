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
        
        <p><c:out value="${userprofile.nome}"/></p><br>
        
        <c:forEach var="p" items="${userposts}">
                <textarea  id="MyBtn" name="editpost" disabled><c:out value="${p.texto}"/></textarea> <br>  
        </c:forEach>
                
        <a href="${pageContext.servletContext.contextPath}/usuario">Voltar</a>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>
