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
        <p>Usuários conhecidos</p>

        <form action="${pageContext.servletContext.contextPath}/usuario/delete" method="POST">
            <table>
                <thead>
                    <tr>
                        <th></th>
                    </tr>
                </thead>
                    <tbody>
                        <c:forEach var="u" items="${usuarioList}">
                            <tr>
                                <td></td>
                                <td>
                                    <a href="${pageContext.servletContext.contextPath}/usuario/read?id=${u.id}">
                                        <c:out value="${u.login}"/>
                                    </a>
                                </td>
                                <td>
                                </td>
                                <td>
                                    
                                </td>
                                <td>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
            </table>

        </form>


        <h1><a href="${pageContext.servletContext.contextPath}/">Home page</a></h1>
    </body>
</html>