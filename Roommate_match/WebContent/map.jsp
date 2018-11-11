<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<link href='https://fonts.googleapis.com/css?family=Antic Didone' rel='stylesheet'>
		<link rel="stylesheet" type="text/css" href="./CSS/map.css">
	</head>
	<body>
	<div id="logo">RM</div>
	<form method="POST" action="MapServlet">
		<input id="submit" type="submit" value="Submit Map"/>
		<input name="userId" type="hidden" value=<%=request.getAttribute("userId")%>/>
		<input name="guestId" type="hidden" value=<%=request.getAttribute("guestId")%>/>
	</form>

	<h2> - Map - </h2>
	
	<h3> Place a marker in the general area where you would like to live! </h3> 
	<h4> You can also set a radius (in miles). </h4>
	
	<input id = "numRad" type="number" step="1" value="1" class="inputButton">
	<div align = "center"> 
	<div id="map"></div>
    <script>
      var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 34.0522, lng: -118.2437},
          zoom: 12
        });
        map.addListener('click', function(e) {
            placeMarkerAndPanTo(e.latLng, map);
          });
      }
      
      function placeMarkerAndPanTo(latLng, map) {
    	  var marker = new google.maps.Marker({
    	    position: latLng,
    	    map: map
    	  });
    	  //map.panTo(latLng);
    		
          var radius = new google.maps.Circle({
              strokeColor: '#FF0000',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              fillColor: '#FF0000',
              fillOpacity: 0.35,
              map: map,
              center: latLng,
              radius: document.getElementById('numRad').value*1600
            });
          
    	}
      
    </script>

    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBVrjjpVNHWp0-vvV4ScgqwMl2_arBUwg&callback=initMap"
    async defer></script>
	</div>
	<div id="copyright">Â© Copyright 2018 Roommate Match Corporation</div>

		
	
	</body>
</html>
