<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.FilledPreferences" %>
<%@ page import="utilities.ProfileInfo" %>
<%@ page import="utilities.IntToStringUtils" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link href='https://fonts.googleapis.com/css?family=Antic Didone' rel='stylesheet'>

		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		
		
		<link rel="stylesheet" type="text/css" href="./CSS/matchProfile.css">
		<%
			ProfileInfo matchInfo = (ProfileInfo)request.getAttribute("matchInfo");
			FilledPreferences matchPrefs = (FilledPreferences)request.getAttribute("matchPreferences");
		%>
	</head>
	<body>
	
		<script>
			var map;
	    	var marker = null;
	    	var circle = null;
	      
	      function initMap() {
	        map = new google.maps.Map(document.getElementById("map"), {
	          center: {lat: <%=(double)matchPrefs.getMapLat()%>, lng: <%=(double)matchPrefs.getMapLong()%>},
	          zoom: 12
	        });
	        
	        circle = new google.maps.Circle({
	              strokeColor: '#FF0000',
	              strokeOpacity: 0.8,
	              strokeWeight: 2,
	              fillColor: '#FF0000',
	              fillOpacity: 0.35,
	              map: map,
	              center: {lat: <%=(double)matchPrefs.getMapLat()%> , lng: <%=(double)matchPrefs.getMapLong()%>},
	              editable : false,
	              radius: <%=(double)matchPrefs.getRadius() * 1600%>
	            });
	        
	        var width = document.getElementById("mainProfile").width;
	        debugger;
	        
	      }
		</script>
	
		<nav class="navbar navbar-dark bg-dark justify-content-between" style="background-color:#494949!important;">
  			<a class="navbar-brand" href=<%="./RedirectMatchServlet?userId=" + request.getAttribute("userId")%>>RM</a>
  			<button class="btnbtn-warning" type="button"><a href="home.jsp">LOGOUT</a></button>
		</nav>
		
		<div id="mainProfile">
			<h1><%=matchInfo.getFullname()%></h1>
			<img style="height:200px; width:200px;" id="image" src=<%="data:image/jpeg;base64," + matchInfo.getProfilePicLink()%> />
			<p id="currentTown"><strong>Current Town: </strong> <%= (matchPrefs.getCurrentTown() == null || matchPrefs.getCurrentTown().equals("")) ? "No Current town" : matchPrefs.getCurrentTown() %> </p>
			<p id="bio"><strong>Bio: </strong><%= (matchPrefs.getBio() == null || matchPrefs.getBio().equals("")) ? "No bio" : matchPrefs.getBio() %> </p>
			<p id="email"><strong>Email: </strong><%= matchInfo.getEmail()%> </p>
			
			
			<div id="map"></div>
			
			<div id="scrollableInfo">
				<ul class="list-group list-group-flush">
				  <li class="list-group-item"><strong>Is a student?</strong><%=IntToStringUtils.yesNo(matchPrefs.getIsStudent())%></li>
				  
				  <%
				  if(matchPrefs.getIsStudent() != 0){
				  %>
					  <li class="list-group-item"><strong>Major: </strong> <%=matchPrefs.getMajor() == null ? "No listed major" : matchPrefs.getMajor() %></li>
				  	  <li class="list-group-item"><strong>In Greek life?</strong><%=IntToStringUtils.yesNo(matchPrefs.getIsGreek())%></li>
				  <%}%>
				  
				  <li class="list-group-item"><strong>Gender: </strong><%=IntToStringUtils.gender(matchPrefs.getGender())%></li>
				  <li class="list-group-item"><strong>Preferred Roommate Gender: </strong><%=IntToStringUtils.genderPref(matchPrefs.getGenderPref())%></li>
				  
				  <li class="list-group-item"><strong>Cost Range: </strong><%="$" + matchPrefs.getRentCostPref() + " per month"%></li>
				  <li class="list-group-item"><strong>Room Type: </strong><%=matchPrefs.getRoomType()%></li>
				  <li class="list-group-item"><strong>Expected length of stay: </strong><%=matchPrefs.getStayLength() + " months"%></li>
				  
				  <li class="list-group-item"><strong>Weekday wake up time: </strong><%=IntToStringUtils.morningTimes(Integer.parseInt(matchPrefs.getWeekdayWake().split(":")[0]))%></li>
				  <li class="list-group-item"><strong>Weekday bedtime: </strong><%=IntToStringUtils.nightTimes(Integer.parseInt(matchPrefs.getWeekdaySleep().split(":")[0]))%></li>
				  
				  <li class="list-group-item"><strong>Weekend wake up time: </strong><%=IntToStringUtils.morningTimes(Integer.parseInt(matchPrefs.getWeekendWake().split(":")[0]))%></li>
				  <li class="list-group-item"><strong>Weekend bedtime: </strong><%=IntToStringUtils.nightTimes(Integer.parseInt(matchPrefs.getWeekendSleep().split(":")[0]))%></li>
				  
				  <li class="list-group-item"><strong>Owns pets? </strong><%=IntToStringUtils.yesNo(matchPrefs.getPets())%></li>
				  <li class="list-group-item"><strong>Ok with pets? </strong><%=IntToStringUtils.yesNo(matchPrefs.getPetsPref())%></li>
				  
				  <li class="list-group-item"><strong>Smokes? </strong><%=IntToStringUtils.yesNo(matchPrefs.getSmoking())%></li>
				  <li class="list-group-item"><strong>Ok with smoking? </strong><%=IntToStringUtils.yesNo(matchPrefs.getSmokingPref())%></li>
				  
				  <li class="list-group-item"><strong>Owns pets? </strong><%=IntToStringUtils.yesNo(matchPrefs.getPets())%></li>
				  <li class="list-group-item"><strong>Ok with pets? </strong><%=IntToStringUtils.yesNo(matchPrefs.getPetsPref())%></li>
				  
				  <li class="list-group-item"><strong>Frequency of Drinking: </strong><%=IntToStringUtils.frequency(matchPrefs.getDrinkingFreq())%></li>
				  <li class="list-group-item"><strong>Acceptable roommate drinking frequency: </strong><%=IntToStringUtils.frequency(matchPrefs.getDrinkingPref())%></li>
				  
				  <li class="list-group-item"><strong>Cleanliness: </strong><%=IntToStringUtils.howClean(matchPrefs.getCleanliness())%></li>
				  <li class="list-group-item"><strong>Cleanliness expectation of roommate: </strong><%=IntToStringUtils.howClean(matchPrefs.getCleanlinessPref())%></li>
				  
				  <li class="list-group-item"><strong>Dish cleanliness: </strong><%=IntToStringUtils.howClean(matchPrefs.getDishes())%></li>
				  <li class="list-group-item"><strong>Dish cleanliness expectation of roommate: </strong><%=IntToStringUtils.howClean(matchPrefs.getDishesPref())%></li>
				  
				  <li class="list-group-item"><strong>Ok with guests at this frequency: </strong><%=IntToStringUtils.frequency(matchPrefs.getGuestPref())%></li>
				  
				  <li class="list-group-item"><strong>Ok with sharing food? </strong><%=IntToStringUtils.yesNo(matchPrefs.getSharingFood())%></li>
				  <li class="list-group-item"><strong>Ok with sharing belongings? </strong><%=IntToStringUtils.yesNo(matchPrefs.getSharing())%></li>
				  
				  <li class="list-group-item"><strong>Age: </strong><%=matchPrefs.getAge()%></li>
				  <li class="list-group-item"><strong>Spoken Languages: </strong><%=matchPrefs.getLanguages()%></li>
				  <li class="list-group-item"><strong>Allergies: </strong><%=matchPrefs.getAllergies() == null ? "No listed allergies" : matchPrefs.getAllergies()%></li>
				  
				</ul>
			</div>
		</div>
		<div id="copyright">© Copyright 2018 Roommate Match Corporation</div>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZqz7hAmRBQA1kStBOHif9DXVMl-JKII0&callback=initMap"
    async defer></script>
	</body>
</html>
