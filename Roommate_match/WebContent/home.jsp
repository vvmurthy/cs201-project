<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<link rel="stylesheet" type="text/css" href="./CSS/home.css">
		<link href='https://fonts.googleapis.com/css?family=Antic Didone' rel='stylesheet'>
	</head>
	<body>
    
    <img src="logo.png" id="logopng" />
	<div id="logo">RM</div>
	<div id="content">
		<h1> Welcome to Roommate Match. </h1>
		<h3> Making the world a better place, one roommate at a time. </h3>
		<h3> Think local, act global. </h3>
		
		<button id="signInButton" onclick = "window.location.href='signin.jsp'"> SIGN IN </button>
		<button id="guestButton" onclick = "window.location.href= 'preferences.jsp'">CONTINUE AS GUEST</button>
	</div>
		<div id="copyright">© Copyright 2018 Roommate Match Corporation</div>
	</body>
</html>
