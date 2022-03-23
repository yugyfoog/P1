<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.training.DAO.ReimbursementDAO" %>
<%@ page import="com.training.DAO.ReimbursementDAOImpl" %>
<%@ page import="com.training.DAO.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
	User user = (User) session.getAttribute("user");
	ReimbursementDAO db = new ReimbursementDAOImpl();
	List<User> emplys = db.getEmployees();
%>
<h1>Employee List</h1>
<hr/>
<%
	if (emplys.size() > 0) {
%>
<table>
	<tr>
		<th>User Id</th>
		<th>Name</th>
		<th>Username</th>
	</tr>
	<%
		for (User e : emplys) {
	%>
	<tr>
		<td><%=e.getUserId() %></td>
		<td><%=e.getFullName() %></td>
		<td><%=e.getUsername() %></td>
	</tr>
	<% } %>	
</table>
<%
	}
	else {
%>
<div>No Employees</div>
<%
	}
%>
<hr/>
<a href="managerhome.jsp">Home</a>
<a href="index.jsp">Log Out</a>
</body>
</html>
