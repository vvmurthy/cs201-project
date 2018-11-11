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
	
	<h2> - Map - </h2>
	
	<h3> Place a marker in the general area where you would like to live! </h3> 
	<h4> You can also set a radius (in miles). </h4>
	
	<input id = "numRad" type="number" step="1" value="1" class="inputButton" onchange="updateRadiusText();">
	<div align = "center"> 
	<div id="map"></div>

	
	
    <script>
      var map;
      var marker = null;
      var circle = null;
      
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 34.0522, lng: -118.2437},
          zoom: 12
        });
        map.addListener('click', function(e) {
            placeMarkerAndPanTo(e.latLng, map);
          });
      }
      
      function updateRadiusText(){s
    	  var radius =  document.getElementById('numRad').value*1600;
    	  document.getElementById("radius").value = document.getElementById('numRad').value;
    	  if(circle != null){
    		  circle.setRadius(radius);
    	  }
      }
      
      function placeMarkerAndPanTo(latLng, map) {
    	  
    	  if(marker != null){
    		  marker.setMap(null);
    	  }
    	  
    	  if(circle != null){
    		  circle.setMap(null);
    	  }
    	  
    	  document.getElementById("lat").value = latLng.lat();
    	  document.getElementById("long").value = latLng.lng();
    	  document.getElementById("radius").value = document.getElementById('numRad').value;
    	  
    	  marker = new google.maps.Marker({
    	    position: latLng,
    	    map: map
    	  });
    	  //map.panTo(latLng);
    		
          circle = new google.maps.Circle({
              strokeColor: '#FF0000',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              fillColor: '#FF0000',
              fillOpacity: 0.35,
              map: map,
              center: latLng,
              editable : true,
              radius: document.getElementById('numRad').value*1600
            });
          
          google.maps.event.addListener(circle, 'radius_changed', function (event) {
        	  
        	  var newRadius = circle.getRadius() / 1600;
        	  document.getElementById("radius").value = newRadius;
        	  document.getElementById("numRad").value = newRadius;        	  
              console.log('circle radius changed');
          });
          
    	}
      
    </script>

    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZqz7hAmRBQA1kStBOHif9DXVMl-JKII0&callback=initMap"
    async defer></script>
	</div>
	
	<div id="submitForm">
		<form method="POST" action="MapServlet">
			<input id="submit" type="submit" value="Submit Map"/>
			<input name="userId" type="hidden" value=<%=request.getAttribute("userId")%>/>
			<input name="guestId" type="hidden" value=<%=request.getAttribute("guestId")%>/>
			<input name="lat" id="lat" type="hidden" />
			<input name="long" id="long" type="hidden" />
			<input name="radius" id="radius" type="hidden" />
		</form>
	</div>
	
	
	<div id="copyright">© Copyright 2018 Roommate Match Corporation</div>

		
	
	</body>
</html>
