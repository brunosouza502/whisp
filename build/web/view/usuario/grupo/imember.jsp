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
        
        <c:forEach var="group" items="${listgroup}">
            <a href="${pageContext.servletContext.contextPath}/membro/group?idgroup=${group.grupoId}"><c:out value="${group.grupoNome}"/></a>
                <a href="${pageContext.servletContext.contextPath}/membro/leavegroup?id=${group.idParticipante}&idgroup=${group.grupoId}">Abandonar grupo</a><br>
        </c:forEach>
         
        
        <br><br>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>