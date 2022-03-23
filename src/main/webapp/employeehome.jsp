<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.training.DAO.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Home Page</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	String message = (String) session.getAttribute("message");
	session.removeAttribute("message");
	%>
	<h1>Employee Home Page</h1>
	<hr>
	<div>Welcome <%=user.getFullName() %></div>
	<% 
		if (message != null)
			out.println("<div>" + message + "</div>");
	%>
	<br />
	<a href="requestreimbursement.jsp">Request New Reimbursement</a>
	<br />
	<a href="viewpending.jsp">View Pending Reimbursement Requests</a>
	<br/>
	<a href="viewresolved.jsp">View Resolved Reimbursement Requests</a>
	<br/>
	<a href="viewuser.jsp">Your Account</a>
	<hr/>
	<a href="index.jsp">Log out</a>
</body>
</html>
