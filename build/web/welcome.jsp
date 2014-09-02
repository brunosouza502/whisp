<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.usuario}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_index.css" rel="stylesheet">
        <title>[BD 2014] Início</title>
    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>
        
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
         
        <p><a  href="${pageContext.servletContext.contextPath}/post/newsfeed?id=${usuario.id}">newsfeed</a></p>
        <p><a href="${pageContext.servletContext.contextPath}/grupos?id=${usuario.id}">Meus grupos</a></p>
        <p><a href="${pageContext.servletContext.contextPath}/membro/imember?id=${usuario.id}">Grupos que participo</a></p>
        <p><a href="${pageContext.servletContext.contextPath}/allposts?id=${usuario.id}">Meus posts</a></p>
        <p><a href="${pageContext.servletContext.contextPath}/followers?id=${usuario.id}">Seguidores
           </a></p>
           <p><a href="${pageContext.servletContext.contextPath}/following?id=${usuario.id}">Seguindo</a>
        
        <form action="${pageContext.servletContext.contextPath}/busca" method="POST">
            <input type="text" name="termo">
            <input type="submit" value="Buscar">
        </form>
               
        <h1><a href="${pageContext.servletContext.contextPath}/usuario">Usuários</a></h1>

        <h1><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></h1>
        
        <div class="modal" id="modal_visualiza_seguidores">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        <h4 class="modal-title">Detalhes</h4>
                    </div>
                    <div class="modal-body">
                        <p id="modal_nome"></p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" type="button" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <%@include file="/view/include/scripts.jsp"%>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
        
    </body>
</html>