<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/lib/normalize.css" rel="stylesheet">
    <title>Students</title>
</head>
<body>
<%@include file="jspSnippets/header.jsp" %>
<div class="content">
    <p>Table of students</p>
    <table width="100%">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>surname</th>
            <th>phone</th>
            <th>birth date</th>
            <th>e-mail</th>
        </tr>
        <c:forEach items="${students}" var="st">
            <jsp:useBean id="st" type="ua.mykytenko.model.Student"/>
            <tr class="body-row">
                <td>${st.id}</td>
                <td>${st.firstName}</td>
                <td>${st.lastName}</td>
                <td>${st.phoneNumber}</td>
                <td>${st.birthDate}</td>
                <td>${st.email}</td>
                <td class="td-last-cells">
                    <a href="students?action=update&id=${st.id}">edit</a>
                </td>
                <td class="td-last-cells">
                    <a href="students?action=delete&id=${st.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="jspSnippets/footer.jsp" %>
</body>
</html>
