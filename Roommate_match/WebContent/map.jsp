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

	<h2> MAP </h2>
	
	<h3> Place a marker in the general area where you would like to live. You can also set a radius too (in miles) </h3>
	
	<input id = "numRad" type="number" step="1" value="1">
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

		
	
	</body>
</html>