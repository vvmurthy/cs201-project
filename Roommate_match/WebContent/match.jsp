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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link href='https://fonts.googleapis.com/css?family=Antic Didone' rel='stylesheet'>

		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		<link rel="stylesheet" type="text/css" href="./CSS/match.css">
		
		<script>
			var socket;
			var data;
			function connectToServer() {
				socket = new WebSocket("ws://104.248.221.16:8080/Roommate_match-0.0.1-SNAPSHOT/ws");
				socket.onopen = function(event) {
					console.log("connected");
					sendMessage();
				}
				socket.onmessage = function(event) {
					data = JSON.parse(event.data);
					document.getElementById("matchContainer").innerHTML = "";
					for(var i = 0 ; i < data.length ; ++i){
						var p = data[i];
						<%int userId = (int)request.getAttribute("userId");%>
						var url = "MatchProfileServlet?userId=" + parseInt(<%=userId%>) + "&matchId=" + parseInt(p.ch.other.userId);
						var fullName = p.fullname;
						var bio = p.ch.other.bio;
						var percent = p.ch.percent;
						var src = "data:image/jpeg;base64," + p.profilePicLink;
						
						
						var rowDiv = document.createElement("div");
						rowDiv.className = "row";
						var colMd = document.createElement("div");
						colMd.className = "col-md-6";
						var media = document.createElement("div");
						media.className = "media";
						rowDiv.appendChild(colMd);
						colMd.appendChild(media);
						
						// Set the stuff inside a column
						var mediaLeft = document.createElement("div");
						mediaLeft.className = "media-left";
						var href = document.createElement("a")
						href.href = url;
						var image = document.createElement("img");
						image.src = src;
						image.style = "height: 100px; width: 100px;"
						image.className = "media-object";
						href.appendChild(image);
						mediaLeft.appendChild(href);
						media.appendChild(mediaLeft);
						
						// Set the words
						var mediaBody = document.createElement("div");
						mediaBody.className = "media-body";
						mediaBody.innerHTML = "<h4 class=\"media-heading\">"+ fullName + "</h4>" +  bio;
						media.appendChild(mediaBody);
						
						// Set the percent
						var mediaRight = document.createElement("div");
						mediaRight.className = "media-right";
						mediaRight.innerHTML = percent + "% match";
						media.appendChild(mediaRight);
						document.getElementById("matchContainer").appendChild(rowDiv);
					}
				}
				socket.onclose = function(event) {
					console.log("disconnected!");
				}
			}
			function sendMessage() {
				socket.send(<%=request.getAttribute("userId")%>);
			}
		</script>
		
	
	</head>
	
	<%
	if(userId != -1){
	%>
		<body onload="connectToServer();">
	<%}else{%>
		<body>
	<%}%>
		<h1> - Matches -</h1>
		<nav class="navbar navbar-dark bg-dark justify-content-between" style="background-color:grey!important;">
  			<a class="navbar-brand"></a>
  			<button class="btnbtn-warning" id="logout" type="button"><a href="home.jsp">LOGOUT</a></button>
  			
		</nav>
		<div id="logo">RM</div>
		<div class="container" id="matchContainer">
		  	    
		    <% 
		    List<ProfileInfo> profiles = (LinkedList<ProfileInfo>)(request.getAttribute("profiles"));
		    for(ProfileInfo p : profiles){
		    %>
		    <div class="row">
		        <div class="col-md-6">
		            <div class="media">
		              <div class="media-left">
		              <%String url = "MatchProfileServlet?userId=" + (int)request.getAttribute("userId") + "&matchId=" + p.getComparison().other.getUserId(); %>
		               	
		               <%	if(userId != -1){%>
		               		<a href=<%=url%>>
		               	<%}%>
		                <img class="media-object" style="height:100px; width:100px;" src=<%="data:image/jpeg;base64," + p.getProfilePicLink()%>>
		         		<%if(userId != -1){%>
		         			</a>
		              	<%}%>
		              </div>
		              <div class="media-body">
		                <h4 class="media-heading"><%=p.getFullname()%></h4>
		                <%=p.getComparison().other.getBio()%>
		              </div>
		              <div class="media-right">
		                <%=(int)p.getComparison().percent + "% match"%>
		              </div>
		            </div>
		        </div>
		        </div>
		     <%}%>
		</div>
		<div id="copyright">© Copyright 2018 Roommate Match Corporation</div>
	</body>
</html>
