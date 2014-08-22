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
        Grupo: <c:out value="${grupo.nomeGrupo}"/> <br>
        
        <p>Membros:</p>
        <form action="${pageContext.servletContext.contextPath}/membro/removemember" method="POST">
            <c:forEach var="a" items="${all}">
                <c:out value="${a.nomeMembro}"/> 
                <input type="checkbox" name="member" value="${a.idParticipante}"><br>
                <input type="hidden" name="group" value="${grupo.idGrupo}">
            </c:forEach>
                <br><br>
                <input type="submit" value="Remover usuarios">
        </form>
        
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>    
    </body>
</html>
