<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="session"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <link href="${pageContext.servletContext.contextPath}/assets/css/login.css" rel="stylesheet">
        <title>[BD 2014] Login</title>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/login" method="POST">
                <h2 class="form-signin-heading">Por favor, faça login.</h2>

                <input class="form-control" type="text" name="login" placeholder="Usuário" autofocus>
                <input class="form-control" type="password" name="senha" placeholder="Senha">

                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>

        <session:erro mensagem="${sessionScope.erro}"/>

        <%@include file="/view/include/scripts.jsp"%>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
    </body>
</html>