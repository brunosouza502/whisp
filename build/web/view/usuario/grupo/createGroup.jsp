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
        
        <form action="${pageContext.servletContext.contextPath}/grupo/create" method="POST">
            <input type="hidden" name="idowner" value="${usuario.id}">
            Nome do grupo: <input type="text" name="nomegrupo"><br><br>
            Descrição do grupo <textarea rows="2" cols="20" name="descricaogrupo"></textarea><br><br>
            
            <input type="submit" value="Criar grupo">
        </form>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
        
    </body>
</html>
