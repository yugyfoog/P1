<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="com.training.DAO.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Account</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
	%>
	<h1>User Account</h1>
	<hr/>
	<table>
		<tr>
			<td>User Id</td>
			<td><%=user.getUserId()%></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><%=user.getFullName()%></td>
		</tr>
		<tr>
			<td>Username</td>
			<td><%=user.getUsername()%></td>
		</tr>	
	</table>
	<br/>
	<a href="edituser.jsp">Edit Data</a>
	<br/>
		<a href="resetpassword.jsp">Reset Password</a>
	<br/>
	<hr/>
	<a href="employeehome.jsp">Home</a>
	<a href="index.jsp">Log Out</a>
</body>
</html>