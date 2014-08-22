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
        Grupo: <c:out value="${grupo.nomeGrupo}"/> <c:out value="${grupo.idGrupo}"/><br>
        
        <form action="${pageContext.servletContext.contextPath}/membro/add" method="POST">
            <c:forEach var="add" items="${addList}">
                <a href="${pageContext.servletContext.contextPath}/usuario/read?id=${add.id}"> 
                    <c:out value="${add.nome}"/> </a>
                <input type="checkbox" name="idmembro" value="${add.id}" ><br>
                <input type="hidden" name="idgrupo" value="${grupo.idGrupo}">
                
            </c:forEach>
                
                <input type="submit" value="Adicionar">
        </form>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>        
    </body>
</html>
