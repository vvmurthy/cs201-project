<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>RoommateMatch: Sign-in</title>
		<link rel="stylesheet" type="text/css" href="./CSS/signin.css">
		<link href='https://fonts.googleapis.com/css?family=Antic Didone' rel='stylesheet'>
	</head>
	<body>
		<h1> - Sign In -</h1>
		<form method="POST" action="SignInServlet">
		<div id="box">
			<table>
				<tr>
					<td><input id="email" name="email" type="text" placeholder="Enter Email" value="${param.email}"></td>
				</tr>
				<tr>
					<td><input id="password" name="password" type="password" placeholder="Enter Password" value="${param.password}"/>
				</tr>
				<tr>
					<td><input id="submit" type="submit" value="Log in"/></td>
				</tr>
				<tr>
					<td><button id="registerButton"><a href="register.jsp"> Not a user? Register </a></button></td>
				</tr>
			</table>
		</div>
		</form>
	</body>
</html>
