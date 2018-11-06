<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.FilledPreferences" %>
<%@ page import="utilities.ProfileInfo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<link rel="stylesheet" type="text/css" href="./CSS/matchProfile.css">
		<%
			ProfileInfo matchInfo = (ProfileInfo)request.getAttribute("matchInfo");
			FilledPreferences matchPrefs = (FilledPreferences)request.getAttribute("matchPreferences");
		%>
	</head>
	<body>
		<h1 id="RM">RM</h1>
		<h2 id="matchName"><%= matchInfo.getFullname() %></h2>
		<div id="notificationBell"><!-- TODO --></div>
		<button id="logout"><!-- TODO --></button>
		
		<div id="mainProfile">
			<img style="height:100px; width:100px;" src=<%="data:image/jpeg;base64," + matchInfo.getProfilePicLink()%> />
			<p id="currentTown"> <%= matchPrefs.getCurrentTown() == null ? "No Current town" : matchPrefs.getCurrentTown() %> </p>
			<p id="bio"> <%= matchPrefs.getBio() == null ? "No bio" : matchPrefs.getBio() %> </p>
			<p id="bio"> <%= matchInfo.getEmail()%> </p>
			
			<div id="scrollableInfo">
				<!-- TODO add preference output from matchPrefs variable -->
			</div>
		</div>
	</body>
</html>