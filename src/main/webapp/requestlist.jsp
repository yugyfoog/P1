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
<title>Reimbursement Requests By Employee</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%
	List<ReimbursementRequest> requests = (List<ReimbursementRequest>)session.getAttribute("requests");
%>
	<h1>Reimbursement Requests By Employee</h1>
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
		<%
			}
		%>
	</table>
	<%
		}
		else {
	%>
	<div>No Reimbursements for Employee</div>
	<%
		}
	%>
	<hr/>
	<a href="managerhome.jsp">Home</a>
	<a href="index.jsp">Log Out</a>
</body>
</html>