<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f8f8f8;
        }
        h1 {
            color: #333;
        }
        .nav {
            margin-top: 20px;
        }
        .nav a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #337ab7;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .nav a:hover {
            background-color: #23527c;
        }
    </style>
</head>
<body>
    <h1>Добро пожаловать в систему сообщений</h1>

    <div class="nav">
        <a href="${pageContext.request.contextPath}/ui/signIn">Вход</a>
        <a href="${pageContext.request.contextPath}/ui/signUp">Регистрация</a>
        <a href="${pageContext.request.contextPath}/ui/user/message">Отправить сообщение</a>
        <a href="${pageContext.request.contextPath}/ui/user/chats">Мои сообщения</a>
        <a href="${pageContext.request.contextPath}/api/admin/statistics">Статистика</a>
    </div>
</body>
</html>
