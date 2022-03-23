<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Reset Password</title>
<link rel="stylesheet" href="styles.css">
<script>
	function passwdOk() {
		var x = document.getElementById("password").value;
		return x.length >= 8 && x.length <= 12;
	}
	function passwd2Ok() {
		var x = document.getElementById("password").value;
		var y = document.getElementById("password2").value;
		return x === y;
	}
	function submitChk() {
		if (passwdOk() && passwd2Ok()) {
			document.getElementById("submit").disabled = false;
		}
		else {
			document.getElementById("submit").disabled = true;
		}
	}
	function passwdChk() {
		submitChk();
		if (passwdOk()) {
			document.getElementById("passwderr").innerHTML = "";
		}
		else {
			document.getElementById("passwderr").innerHTML = "password must be between 8 and 12 characters";
		}
	}
	function passwd2Chk() {
		submitChk();
		if (passwd2Ok()) {
			document.getElementById("passwd2err").innerHTML = "";
		}
		else {
			document.getElementById("passwd2err").innerHTML = "passwords do not match";
		}
	}
</script>
</head>
<body>
	<h1>Reset Password</h1>
	<hr/>
	<form action="PasswordChangeController" method="post">
		<table>
			<tr>
				<td><label>New Password</label></td>
				<td><input type="password" name="password" id="password" oninput="passwdChk()" autocomplete="new-password"></td>
				<td><span id="passwderr" class="errormsg"></span></td>
			</tr>
			<tr>
				<td><label>re-enter Password</label></td>
				<td><input type="password" name="password2" id="password2" oninput="passwd2Chk()" autocomplete="new-password"></td>
				<td><span id="passwd2err" class="errormsg"></span></td>
			</tr>
		</table>
		<input type="submit" value="Reset" id="submit" disabled>
	</form>
	<hr/>
	<a href="employeehome.jsp">Home</a>
	<a href="index.jsp">Log Out</a>
</body>
</html>