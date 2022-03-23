<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Account Information</title>
<link rel="stylesheet" href="styles.css">
<script>
	function elementOk(element) {
	    return document.getElementById(element).value != 0;
	}
	function firstNameOk() {
		return elementOk("firstname");
	}
	function lastNameOk() {
		return elementOk("lastname");
	}
	function editChk() {
		if (firstNameOk() && lastNameOk())
			document.getElementById("edit").disabled = false;
		else	
			document.getElementById("edit").disabled = true;	
	}
	function firstNameChk() {
		editChk();
	}
	function lastNameChk() {
		editChk();
	}
</script>
</head>
<body>
<h1>Edit Account Information</h1>
<hr/>
<form action="EditControler" method="post">
	<table>
		<tr>
			<td><label>First Name</label></td>
			<td><input type="text" name="firstname" id="firstname" oninput="firstNameChk()"></td>
		</tr>
		<tr>
			<td><label>Last Name</label></td>
			<td><input type="TEXT" name="lastname" id="lastname" oninput="lastNameChk()"></td>
		</tr>
	</table>
	<input type="submit" value="Edit" id="edit" disabled>
</form>
<hr/>
<a href="employeehome.jsp">Home</a>
<a href="index.jsp">Log Out</a>
</body>
</html>