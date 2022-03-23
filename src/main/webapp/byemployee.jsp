<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Pending Requests of Employee</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>View Pending Requests of Employee</h1>
<hr>
<form action="ByEmployeeController" method="post">
<label>Employee Id</label>
<input type="number" name="userid">
<br/>
<input type="submit" value="Submit">
</form>
<hr/>
<a href="managerhome.jsp">Home</a>
<a href="index.jsp">Log Out</a>
</body>
</html>