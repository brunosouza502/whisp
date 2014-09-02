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


        <c:forEach var="news" items="${newsfeed}">
            <a href="${pageContext.servletContext.contextPath}/usuario/read?id=${news.idUsuario}"><c:out value="${news.nomeUsuarioPost}"/>: </a><br>
            <textarea  name="editpost" disabled><c:out value="${news.texto}"/></textarea> <br>
            
            <a href="${pageContext.servletContext.contextPath}/like?iduserliker=${usuario.id}&idpostliked=${news.idPost}&idusuarioown=${news.idUsuario}">
                    Curtir
                </a>
                    <a href="${pageContext.servletContext.contextPath}/undolike?iduserliker=${usuario.id}&idpostdisliked=${news.idPost}">
                    Descurtir
                </a> 
                    <a href="${pageContext.servletContext.contextPath}/post/comentarios?idpost=${news.idPost}&id=${usuario.id}">Ver comentarios</a>
                    <form action="${pageContext.servletContext.contextPath}/comentar" method="POST">
                        <input type="hidden" name="idpost" value="${news.idPost}">
                        <input type="hidden" name="idcomentador" value="${usuario.id}">
                        <textarea name="comentario" rows="1" cols="10"></textarea>
                        <input type="submit" value="Comentar">
                    </form>
                        <a 
                            href="${pageContext.servletContext.contextPath}/post/republicar?idrepublicador=${usuario.id}&idusuariopost=${news.idUsuario}&idpostrepublicado=${news.idPost}">
                            Republicar
                        </a><br>
                <br>
        </c:forEach>
         
                <br>

        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>