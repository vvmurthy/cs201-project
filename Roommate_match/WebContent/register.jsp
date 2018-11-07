<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>- Registration -</title>
		<link href='https://fonts.googleapis.com/css?family=Antic Didone' rel='stylesheet'>
		<link rel="stylesheet" type="text/css" href="./CSS/register.css">
	</head>
	<body>
	<h1> - Registration -</h1>
	<div id="logo">RM</div>
	<div id="registerForm">
			<form id = "rForm" method= "post" enctype="multipart/form-data" action="RegistrationServlet">
			<table style="border:0;">
				<tr>
					<td>Name:<input type ="text" name = "name" id=username value="${param.name}" style="float: right;"></td>
				</tr>
				<tr>
					<td>Enter Password:<input type="password" name= "password1" id="passwordOne" style="float: right;"></td>
  				</tr>
  				<tr>
					<td>Re-Enter Password:<input type="password"  name="password2" id="passwordTwo" style="float: right;"></td>
  				</tr>
  				<tr>
					<td>Enter Email:<input type="text" name="email" id="email" value="${param.email}" style="float: right;"></td>
  				</tr>
  				<tr>
  					<td>Upload Profile Picture: <input type="file" name="photo" id="profilePicture" size="100" style="float: right;"></td>
  				</tr>
  				
  				<tr>
  					<td><input id="submit" type="submit" value="REGISTER"></td>
  				</tr>
  			</table>
			</form>	
	</div>
	<div id="copyright">© Copyright 2018 Roommate Match Corporation</div>
	
	<%if(request.getAttribute("errors") != null){%>
			<h1><%out.println(request.getAttribute("errors"));%></h1>
	<%}%>

	</body>
	
</html>
