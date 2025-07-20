<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
    <h1>Вход</h1>
    <form action="${pageContext.request.contextPath}/api/login" method="post">
        <label for="login">Логин:</label>
        <input type="text" id="login" name="login" required><br><br>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Войти</button>
    </form>

    <p><a href="${pageContext.request.contextPath}/ui">На главную</a></p>

    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <p style="color:red;">Ошибка: <%= error %></p>
    <%
        }
    %>
</body>
</html>
