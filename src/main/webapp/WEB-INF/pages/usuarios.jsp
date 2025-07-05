<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<html>
<body>
    <c:if test="${acao == '/usuario/editar'}">
        <h1>Editar Usuário</h1>
    </c:if>
    <c:if test="${acao == '/usuario'}">
        <h1>Cadastrar Usuário</h1>
    </c:if>

    <form:form action="${acao}" method="post" modelAttribute="usuario">
        <form:label path="email">Email</form:label>
        <form:input path="email" />

        <form:label path="senha">Senha</form:label>
        <form:password path="senha" />

        <c:if test="${acao == '/usuario/editar'}">
            <form:hidden path="id" />
            <input type="submit" value="Editar" />
        </c:if>
        <c:if test="${acao == '/usuario'}">
            <input type="submit" value="Cadastrar" />
        </c:if>

    </form:form>

    <c:if test="${not empty msg}">
        <h2>${msg}</h2>
    </c:if>
<c:if test="${not empty usuarios}">
    <h2>Lista de Usuários</h2>
    <table>
        <th>Email</th>
        <th>Ativo</th>
        <th>Ações</th>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
              <!--  <td>${usuario.id}</td> -->
                <td>${usuario.email}</td>
                <td>${usuario.ativo ? 'Sim' : 'Não'}</td>
                <td>
                    <a href="usuario/editar/${usuario.id}">Editar</a>
                </td>
                <td>
                    <a href="usuario/excluir/${usuario.id}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
