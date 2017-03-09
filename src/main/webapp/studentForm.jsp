<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/lib/normalize.css" rel="stylesheet">
    <title>Student form</title>
</head>
<body>
<%@include file="jspSnippets/header.jsp" %>
<jsp:useBean id="student" class="ua.mykytenko.model.Student" scope="request"/>
<div class="content">
    <form name="studentForm" action="students" method="post">

        <h5>Имя:</h5>
        <input title="First Name" type="text" value="${student.firstName}" name="firstName"/>
        <h5>Фамилия:</h5>
        <input title="Last Name" type="text" value="${student.lastName}" name="lastName"/>
        <h5>Дата рождения:</h5>
        <input title="Date of birth" type="date" value="${student.birthDate}" name="birthDate"/>
        <h5>Номер телефона(в формате +380ХХХХХХХ):</h5>
        <input title="Contact phone" type="number" value="${student.phoneNumber}" name="phoneNumber"/>
        <h5>Адрес электронной почты:</h5>
        <input title="Contact email" type="text" value="${student.email}" name="email"/>

        <input type="hidden" name="action" value="save"/>
        <input type="hidden" name="id" value="${student.id}"/>
        <button type="submit">Go!</button>
        <button onclick="window.history.back()">cancel</button>
    </form>
</div>
<%@include file="jspSnippets/footer.jsp" %>
</body>
</html>
