<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>RoommateMatch: Sign-in</title>
		<link rel="stylesheet" type="text/css" href="./CSS/signin.css">
	</head>
	<body>
		<h1>Sign In</h1>
		<form method="POST" action="SignInServlet">
			<table>
				<tr>
					<td><input id="email" type="text" name="email" placeholder="Enter Email" value="${param.email}"/>
				</tr>
				<tr>
					<td><input id="password" type="password" name="password" placeholder="Enter Password" value="${param.password}"/>
				</tr>
				<tr>
					<td><input id="submit" type="submit" value="Sign in"/></td>
				</tr>
				<tr>
					<td><button><a href="register.jsp"> Not a user? Register </a></button></td>
				</tr>
			</table>
		</form>
		

	</body>
</html>