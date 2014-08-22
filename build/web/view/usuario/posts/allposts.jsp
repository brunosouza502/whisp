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
        
        <script>
                function myFunction()
                {
                    document.getElementById("MyBtn").disabled=false;
                }
            </script>
            
    </head>
    <body>
        <h1>Bem-vindo, <c:out value="${usuario.nome}"/>!</h1>
        
        Meus Posts: <br>
        <form action="${pageContext.servletContext.contextPath}/post/delete" method="POST">
            <c:forEach var="p" items="${posts}">
                <textarea  id="MyBtn" name="editpost" disabled><c:out value="${p.texto}"/></textarea> <br>
                <!--<button onclick="myFunction()">Editar</button>-->
                <a href="${pageContext.servletContext.contextPath}/post/update?idpost=${p.idPost}">Editar</a>
                <a href="${pageContext.servletContext.contextPath}/post/delete?idpost=${p.idPost}">Excluir</a>
                <input type="checkbox" name="delete" value="${p.idPost}"> <br><br>
  
            </c:forEach>
                
                <input type="submit" value="Excluir Posts">    
        </form>
            
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>
