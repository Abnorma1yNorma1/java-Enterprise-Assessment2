<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Мои сообщения</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .message {
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            background: #f9f9f9;
        }
        .header {
            font-weight: bold;
        }
        .date {
            color: gray;
            font-size: 0.85em;
        }
    </style>
</head>
<body>
<h1>Входящие сообщения</h1>

<c:choose>
    <c:when test="${empty messages}">
        <p>Сообщений нет.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="msg" items="${messages}">
            <div class="message">
                <div class="header">
                    От: <c:out value="${msg.fromWho}"/>
                    <span class="date"> — <fmt:formatDate value="${msg.date}" pattern="dd.MM.yyyy HH:mm:ss"/></span>
                </div>
                <div class="content">
                    <c:out value="${msg.text}" escapeXml="true"/>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/ui/api/message">Отправить сообщение</a></p>
<p><a href="${pageContext.request.contextPath}/ui/">Главная</a></p>

</body>
</html>
