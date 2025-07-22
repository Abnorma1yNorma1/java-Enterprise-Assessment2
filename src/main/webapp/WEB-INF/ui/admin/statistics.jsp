<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Статистика</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            border-collapse: collapse;
            width: 50%;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
<h1>Статистика приложения</h1>

<table>
    <tr>
        <th>Показатель</th>
        <th>Значение</th>
    </tr>
    <tr>
        <td>Активные пользователи (сессии)</td>
        <td><c:out value="${activeUsers}" /></td>
    </tr>
    <tr>
        <td>Всего зарегистрированных пользователей</td>
        <td><c:out value="${totalUsers}" /></td>
    </tr>
    <tr>
        <td>Всего сообщений в системе</td>
        <td><c:out value="${totalMessages}" /></td>
    </tr>
</table>

<p><a href="${pageContext.request.contextPath}/ui/">Вернуться на главную</a></p>

</body>
</html>
