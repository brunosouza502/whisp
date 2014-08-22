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
                    if (document.getElementById("editName").disabled && document.getElementById("editDesc").disabled){
                        document.getElementById("editName").disabled=false;
                        document.getElementById("editDesc").disabled=false;
                        document.getElementById("edit").disabled=false;
                    }else{
                            document.getElementById("editName").disabled=true;
                            document.getElementById("editDesc").disabled=true;
                            document.getElementById("edit").disabled=true;
                        }
                }
        </script>
    </head>
    <body>
        <h1>Bem-vindo, <c:out value="${usuario.nome}"/>!</h1>
        <button onclick="myFunction()">Alterar</button> 
        <a href="${pageContext.servletContext.contextPath}/grupo/delete?idgrupo=${grupo.idGrupo}">Remover Grupo</a>
        
        <form action="${pageContext.servletContext.contextPath}/grupo/update" method="POST">
            
            
            <input id="editName" name="nomegrupo" value="${grupo.nomeGrupo}" disabled><br>
            <textarea id="editDesc" name="descgroup" rows="2" cols="20" disabled><c:out value="${grupo.descricaoGrupo}"/></textarea>
            <input type="hidden" name="idgroup" value="${grupo.idGrupo}">
            <br><br>
            
            <input id="edit" type="submit" value="Editar" disabled>
        
        </form>
            
        <a href="${pageContext.servletContext.contextPath}/membro/allmembers?idgrupo=${grupo.idGrupo}">Membros</a>
        
        <a href="${pageContext.servletContext.contextPath}/membro/add?idgroup=${grupo.idGrupo}">Adicionar Usuarios</a><br>
        <a href="${pageContext.servletContext.contextPath}/">Home Page</a>
    </body>
</html>
