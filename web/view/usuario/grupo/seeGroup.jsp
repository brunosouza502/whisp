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
        <p>Grupo: <c:out value="${grupo.nomeGrupo}"/> <br> Descrição: <c:out value="${grupo.descricaoGrupo}"/></p>
        
        Membros:<br>
            <c:forEach var="m" items="${members}">
                <a href="${pageContext.servletContext.contextPath}/usuario/read?id=${m.idParticipante}"> 
                    <c:out value="${m.nomeMembro}"/></a> <br>
            </c:forEach>

        <br><br>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>    
    </body>
</html>
