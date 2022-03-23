<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.training.DAO.ReimbursementDAO" %>
<%@page import="com.training.DAO.ReimbursementDAOImpl" %>
<%@page import="com.training.DAO.User" %>
<%@page import="com.training.DAO.ReimbursementRequest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending Reimbursement Requests</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
	User user = (User) session.getAttribute("user");
	ReimbursementDAO db = new ReimbursementDAOImpl();
	List<ReimbursementRequest> requests = db.getPendingRequests(user);
%>
<h1>Pending Reimbursement Requests</h1>
<hr/>
Pending Reimbursements For
<%
	out.println(user.getFullName());
%>  
<%
	if (requests.size() > 0) {
%>
<table>
	<tr>
		<th>Amount</th><th>Category</th><th>Details</th>
	</tr>
	<%
		for (ReimbursementRequest r : requests) {
	%>
	<tr>
		<td><%=r.getAmount() %></td>
		<td><%=r.getCategory() %></td>
		<td><%=r.getDetails() %></td>
	</tr>
	<% } %>
</table>
<%
	}
	else {
%>
<div>No Pending Reimbursements</div>
<%
	}
%>
<hr/>
<a href="employeehome.jsp">Home</a>
<a href="index.jsp">Log Out</a>
</body>
</html>
