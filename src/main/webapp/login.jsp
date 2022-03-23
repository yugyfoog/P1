<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expense Reimbursement System &#8212; Log In</title>
<link rel="stylesheet" href="styles.css">
<script>
	function elementOk(element) {
		return document.getElementById(element).value != 0;
	}
	function usernameOk() {
		return elementOk("username");
	}
	function passwdOk() {
		var x = document.getElementById("password").value;
		return x.length >= 8 && x.length <= 12;
	}
	function usernameChk() {
		submitChk();
	}
	function passwdChk() {
		submitChk();
	}
	function submitChk() {
		if (usernameOk() && passwdOk()) {
			document.getElementById("submit").disabled = false;
		}
		else
			document.getElementById("submit").disabled = true;
	}
</script>
</head>
<body>
	<%
		String message = (String) session.getAttribute("message");
		session.removeAttribute("message");
	%>
	<h1>Expense Reimbursement System &#8212; Log In</h1>
	<hr/>
	<%
		if (message != null)
			out.println("<div class='errormsg'>" + message + "</div>");
	%>
	<form action="LoginController" method="post">
		<table>
			<tr>
				<td><label>Username</label></td>	
				<td><input type="text" name="username" id="username" oninput="usernameChk()" autocomplete="username"></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><input type="password" name="password" id="password" oninput="passwdChk()" autocomplete="password"></td>
			</tr>
		</table>
		<input type="submit" value="Log In" id="submit" disabled>
	</form>
	<hr/>
	<a href="index.jsp">Back</a>
</body>
</html>