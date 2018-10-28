<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registration Page</title>
		<link rel="stylesheet" type="text/css" href="./CSS/register.css">
	</head>
	<body>
	<div id="registerForm">
			<form id = "rForm" method= "post" enctype="multipart/form-data" action="RegistrationServlet">
			<table border="0">
				<tr>
					<td>Name: </td>
					<td><input type ="text" name = "name" placeholder="Enter Name" id=username value="${param.name}"></td>
				</tr>
				<tr>
					<td>Enter Password: </td>
					<td><input type="password" name= "password1" placeholder="Enter Password" onClick="$(this).removeClass('placeholderclass')"
  						class="dateclass placeholderclass" id="passwordOne"></td>
  				</tr>
  				<tr>
  					<td>Re-Enter Password: </td>
					<td><input type="password"  name="password2" placeholder="Re-Enter Password" onClick="$(this).removeClass('placeholderclass')"
  						class="dateclass placeholderclass" id="passwordTwo"></td>
  				</tr>
  				<tr>
  					<td>Enter Email: </td>
					<td><input type="text" name="email" placeholder="Enter Email" onClick="$(this).removeClass('placeholderclass')"
  						class="dateclass placeholderclass" id="email" value="${param.email}"></td>
  				</tr>
  				<tr>
  				<td>Upload Profile Picture: </td>
  					<td><input type="file" name="photo" id="profilePicture" size="100" /></td>
  				</tr>
  				
  				<tr>
  					<td><input type="submit" value="Register"></td>
  				</tr>
  			</table>
			</form>	
	</div>
	
	<%if(request.getAttribute("errors") != null){%>
			<h1><%out.println(request.getAttribute("errors"));%></h1>
	<%}%>

	</body>
	
</html>