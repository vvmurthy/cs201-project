<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.FilledPreferences" %>
<%@ page import="utilities.ProfileInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="./CSS/match.css">
	
	</head>
	<body>
		<h1 id="RM">RM</h1>
		<h2 id="matchesTitle">Matches</h2>
		<%
		int userId = (int)request.getAttribute("userId");
		
		if(userId != -1){
		%>
			<button id="logout"><a href="home.jsp">Logout</a></button>
		<%}%>
		
		<div class="container">
		    
		    
		    <% 
		    List<ProfileInfo> profiles = (LinkedList<ProfileInfo>)(request.getAttribute("profiles"));
		    for(ProfileInfo p : profiles){
		    %>
		    <div class="row">
		        <div class="col-md-6">
		            <div class="media">
		              <div class="media-left">
		              <%String url = "MatchProfileServlet?userId=" + (int)request.getAttribute("userId") + "&matchId=" + p.getComparison().other.getUserId(); %>
		               <a href=<%=url%>>
		                <img class="media-object" style="height:100px; width:100px;" src=<%="data:image/jpeg;base64," + p.getProfilePicLink()%>>
		         	</a>
		              </div>
		              <div class="media-body">
		                <h4 class="media-heading"><%=p.getFullname()%></h4>
		                <%=p.getComparison().other.getBio()%>
		              </div>
		            </div>
		        </div>
		        </div>
		     <%}%>
		</div>
	</body>
</html>