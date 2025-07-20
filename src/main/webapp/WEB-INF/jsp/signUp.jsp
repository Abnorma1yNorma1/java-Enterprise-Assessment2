<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h1>Регистрация</h1>

<%
        String error = (String) request.getAttribute("error");
        if (error == null) {
            error = request.getParameter("error");
        }
        if (error != null) {
    %>
        <p style="color: red;"><%= error %></p>
    <%
        }
    %>

<form method="post" action="/api/user">
    <label for="login">Логин:</label><br>
    <input type="text" id="login" name="login" required><br><br>

    <label for="password">Пароль:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <label for="name">ФИО:</label><br>
    <input type="text" id="name" name="name"><br><br>

    <label for="birthDate">Дата рождения:</label><br>
    <input type="date" id="birthDate" name="birthDate"><br><br>

    <button type="submit">Зарегистрироваться</button>
</form>

<p><a href="/ui/signin">Уже есть аккаунт? Войти</a></p>
<p><a href="/ui/">Назад на главную</a></p>

</body>
</html>
