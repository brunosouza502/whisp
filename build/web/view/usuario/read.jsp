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
       <h1>Visualização do usuário </h1>
        
       <p><c:out value="${userprofile.nome}"/></p>  
       <a href="${pageContext.servletContext.contextPath}/follow?idfollower=${usuario.id}&idfollowed=${userprofile.id}">
           Seguir</a>
       
        <ul>
            <li>Login: <c:out value="${userprofile.login}"/></li>
            <li>Nome: <c:out value="${userprofile.nome}"/></li>
            <li> <c:out value="${userprofile.sexo}"/></li>
            <li>Data de nascimento: <c:out value="${userprofile.nascimento}"/></li>
            <li>Sobre: <c:out value="${userprofile.descricao}"/></li>
        </ul>
        
        
        <c:forEach var="p" items="${userposts}">
                <textarea  id="MyBtn" name="editpost" disabled><c:out value="${p.texto}"/></textarea> <br>
                <a href="${pageContext.servletContext.contextPath}/like?iduserliker=${usuario.id}&idpostliked=${p.idPost}&idusuarioown=${userprofile.id}">
                    Curtir
                </a> 
                    <a href="${pageContext.servletContext.contextPath}/post/comentarios?idpost=${p.idPost}&id=${usuario.id}">Ver comentarios</a>
                    <form action="${pageContext.servletContext.contextPath}/comentar" method="POST">
                        <input type="hidden" name="idpost" value="${p.idPost}">
                        <input type="hidden" name="idcomentador" value="${usuario.id}">
                        <textarea name="comentario" rows="1" cols="10"> </textarea>
                        <input type="submit" value="Comentar">
                    </form>
                        <a 
                            href="${pageContext.servletContext.contextPath}/post/republicar?idrepublicador=${usuario.id}&idusuariopost=${userprofile.id}&idpostrepublicado=${p.idPost}">
                            Republicar
                        </a>
                <br>
        </c:forEach>
                
        <a href="${pageContext.servletContext.contextPath}/usuario">Voltar</a>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>