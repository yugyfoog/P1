<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.training.DAO.ReimbursementRequest" %>
<%@ page import="com.training.DAO.ReimbursementDAO" %>
<%@ page import="com.training.DAO.ReimbursementDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending Reimbursement Requests</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
	ReimbursementDAO db = new ReimbursementDAOImpl();
	List<ReimbursementRequest> requests = db.getAllPending();
%>
<h1>Pending Reimbursement Requests</h1>
<hr/>
<%
	if (requests.size() > 0) {
%>
<form action="ApproveControloer" method="post">
	<table>
		<tr>
			<th></th>  <!-- select button column -->
			<th>User Id</th>
			<th>Amount</th>
			<th>Catagory</th>
			<th>Details</th>
		</tr>
		<%
			for (ReimbursementRequest r : requests) {
		%>
		<tr>
			<td><input type="checkbox" name="requestid" value=<%= r.getRequestid() %>></td>
			<td><%=r.getUserId() %></td>
			<td><%=r.getAmount() %></td>
			<td><%=r.getCategory() %></td>
			<td><%=r.getDetails() %></td>
		</tr>
		<% } %>
	</table>
	<input type="submit" name="action" value="Approve">
	<input type="submit" name="action" value="Deny">
</form>
<%
	}
	else {
%>
<div>No Pending Reimbursement Requests</div>
<%
	}
%>
<hr/>
<a href="managerhome.jsp">Home</a>
<a href="index.jsp">Log Out</a>
</body>
</html>
