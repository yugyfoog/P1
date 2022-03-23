<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.training.DAO.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Home Page</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	%>
	<h1>Manager Home Page</h1>
	<hr/>
	<div>Welcome <%=user.getFullName() %></div>
	<br/>
	<a href="viewpendingrequests.jsp">View Pending Requests</a>
	<br/>
	<a href="viewrequests.jsp">View Resolved Requests</a>
	<br/>
	<a href="byemployee.jsp">View Pending Requests of Employee</a>
	<br/>
	<a href="viewemployees.jsp">View Employees</a> 
	<hr/>
	<a href="index.jsp">Log out</a>
</body>
</html>