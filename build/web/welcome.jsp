<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.usuario}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>[BD 2014] Início</title>
    </head>
    <body>
        <h1>Bem-vindo, <c:out value="${usuario.nome}"/>!</h1>
        <a href="${pageContext.servletContext.contextPath}/usuario/update?id=${usuario.id}">Editar perfil</a>
        Nascimento: <c:out value="${usuario.nascimento} "/>
        <c:out value="${usuario.sexo}"/><br>
        <c:out value="${usuario.nomeGrupoOwn}"/><br>
        <c:out value="${usuario.descricao}"/><br><br>
        
        <form action="${pageContext.servletContext.contextPath}/post" method="POST">
            <textarea rows="3" cols="20" name="wallpost"></textarea>
            <input type="hidden" name="idusuario" value="${usuario.id}">
            <input type="submit" value="Postar">
        </form>
            
        <c:forEach var="p" items="${posts}">
                <textarea  id="MyBtn" name="editpost" disabled><c:out value="${p.texto}"/></textarea> <br>  
        </c:forEach>
        <br><br>
         
        <p><a href="${pageContext.servletContext.contextPath}/grupos?id=${usuario.id}">Meus grupos</a></p>
        <p><a href="${pageContext.servletContext.contextPath}/membro/imember?id=${usuario.id}">Grupos que participo</a></p>
        <p><a href="${pageContext.servletContext.contextPath}/allposts?id=${usuario.id}">Meus posts</a></p>
        
        <h1><a href="${pageContext.servletContext.contextPath}/usuario">Usuários</a></h1>

        <h1><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></h1>
        
    </body>
</html>