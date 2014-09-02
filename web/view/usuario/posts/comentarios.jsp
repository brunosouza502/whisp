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
        
        <textarea disabled><c:out value="${post.texto}"/></textarea><br>
        <c:out value="${likes.qtdLikes}"/> Gostaram <br>
        
        <form action="${pageContext.servletContext.contextPath}/comentar" method="POST">
                        <input type="hidden" name="idpost" value="${post.idPost}">
                        <input type="hidden" name="idcomentador" value="${usuario.id}">
                        <textarea name="comentario" rows="1" cols="10"></textarea>
                        <input type="submit" value="Comentar">
        </form>
        <br><br>
        
        <c:forEach var="comm" items="${comentarios}">
            <a href="${pageContext.servletContext.contextPath}/usuario/read?id=${comm.idComentador}"><c:out value="${comm.nomeComentador}"/>: </a><br>
            <c:out value="${comm.textoComentario}"/><br><br>
        </c:forEach>
                
        <p><a  href="${pageContext.servletContext.contextPath}/post/newsfeed?id=${usuario.id}">Voltar</a></p>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>