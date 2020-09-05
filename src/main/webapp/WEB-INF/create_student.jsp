<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
    <%@ include file="/WEB-INF/common/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Create Student</h3>
<form:form action="save_student" method="POST" modelAttribute="student">
Student Name: <form:input path="name" /><form:errors path="name" cssClass="error"></form:errors> <br>
Roll No:<form:input path="rollNo" /><br>

<input type="submit" value="Register" />
</form:form>
</body>
</html>