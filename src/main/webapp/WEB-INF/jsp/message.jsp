<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отправка сообщения</title>
</head>
<body>
<h2>Отправка сообщения</h2>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <p style="color: red;"><%= error %></p>
<% } %>

<form method="post" action="${pageContext.request.contextPath}/api/message">
    <label for="toWhom">Кому (логин):</label><br>
    <input type="text" id="toWhom" name="toWhom" required><br><br>

    <label for="text">Сообщение:</label><br>
    <textarea id="text" name="text" rows="4" cols="50" required></textarea><br><br>

    <input type="submit" value="Отправить сообщение">
</form>

<br>
<a href="/ui/user/chats">Перейти к чату</a> |
<a href="/ui">Главная</a>
</body>
</html>
