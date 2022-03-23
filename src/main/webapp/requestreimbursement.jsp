<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.training.DAO.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Reimbursement</title>
<link rel="stylesheet" href="styles.css">
<script>
function checkElement(e) {
	var val = document.getElementById(e).value;
	return val.length != 0;
}
function amountOk() {
	var val = document.getElementById("amount").value;
	console.log("amount: " + val);
	return val >= 0;
}
function catagoryOk() {
	return document.getElementById("catagory").value != 0;
}
function detailOk() {
	return document.getElementById("details").value != 0;
}
function submitChk() {
	if (amountOk() && catagoryOk() && detailOk())
		document.getElementById("submit").disabled = false;
	else
		document.getElementById("submit").disabled = true;
}
function amountChk() {
	submitChk();
	var t = amountOk();
	if (t) {
		document.getElementById("amounterr").innerHTML = "";
	}
	else {
		document.getElementById("amounterr").innerHTML = "must be a number";
	}
}
</script>
</head>
<body>
<%
	User user = (User) session.getAttribute("user");
%>
<h1>Request Reimbursement</h1>
<hr>
Request Reimbursement For  
<%
	out.println(user.getFullName());
%>
<form action="SubmitController" method="post">
<table>
<tr>
<td><label>Amount</label></td>
<td><input type="text" name="amount" id="amount" oninput="amountChk()"></td>
<td><span id="amounterr" class="errormsg"></span></td>
</tr>
<tr>
<td></td>
<td><select name="catagory" id="catagory" onclick="submitChk()">
<option disabled>--Select One--</option>
<option>Travel</option>
<option>Office Equipment</option>
<option>Miscellaneous</option>
</select></td>
<tr>
<td><label>Detail</label></td>
<td><textarea name="details" id="details" oninput="submitChk()"></textarea></td>
</tr>
</table>
<input type="submit" value="Submit" id="submit" disabled>
</form>
<hr/>
<a href="employeehome.jsp">Home</a>
<a href="index.jsp">Log Out</a>
</body>
</html>