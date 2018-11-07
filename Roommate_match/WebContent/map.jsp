<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<link rel="stylesheet" type="text/css" href="./CSS/map.css">
	</head>
	<body>
	<form method="POST" action="MapServlet">
		<input id="submit" type="submit" value="Submit map"/>
		<input name="userId" type="hidden" value=<%=request.getAttribute("userId")%>/>
		<input name="guestId" type="hidden" value=<%=request.getAttribute("guestId")%>/>
	</form>

	</body>
</html>