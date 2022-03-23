<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.training.DAO.User" %>
<%@ page import="com.training.DAO.ReimbursementRequest" %>
<%@ page import="com.training.DAO.ReimbursementDAO" %>
<%@ page import="com.training.DAO.ReimbursementDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
	ReimbursementDAO db = new ReimbursementDAOImpl();
	List<ReimbursementRequest> requests = db.getAllResolvedRequests();
%>
<h1>Resolved Reimbursement Requests</h1>
<hr/>
<%
	if (requests.size() > 0) {
%>
<table>
	<tr>
		<th>Status</th>
		<th>User Id</th>
		<th>Amount</th>
		<th>Category</th>
		<th>Details</th>
	</tr>
	<%
		for (ReimbursementRequest r : requests) {
	%>
	<tr>
		<td><%=r.getStatus() %></td>
		<td><%=r.getUserId() %></td>
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
<div>No Resolved Reimbursements</div>
<%
	}
%>
<hr/>
<a href="managerhome.jsp">Home</a>
<a href="index.jsp">Log Out</a>
</body>
</html>