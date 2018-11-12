<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<link rel="stylesheet" type="text/css" href="./CSS/preferences.css">
		<link href='https://fonts.googleapis.com/css?family=Antic Didone' rel='stylesheet'>
		<script src="JS/rangeSliderValues.js"></script>
		<script>
			function toggleStudentQ(student) {
				var studentQs = document.getElementsByClassName("studentQ");
				var display = student==true ? "" : "none";
				var req = student==true ? true : false;
				for (var i = 0; i < studentQs.length; i++) {
					studentQs[i].style.display = display;
					studentQs[i].required = req;
				}
			}
			
			function initMap(){
				var autocomplete = new google.maps.places.Autocomplete(
						document.getElementById("currentTownMaps"));

		        // Set the data fields to return when the user selects a place.
		        autocomplete.setFields(
		            ['address_components', 'formatted_address']);

		        autocomplete.addListener('place_changed', function() {
		          var place = autocomplete.getPlace();
		          document.getElementById("currentTown").value = autocomplete.getPlace().formatted_address;
		        });
			}
			
		</script>
	</head>
	<body>
		<div id="logo">RM</div>
		<h1>- Lifestyle Preferences -</h1>
		<form action="./PreferencesServlet" method="POST">
			<table>
				<tr>
					<td>
						<h3 class="preferenceLabel">Are you a student?</h3>
						<input type="radio" name="isStudent" value="1" required onclick="toggleStudentQ(true);"/> Yes <br />
						<input type="radio" name="isStudent" value="0" onclick="toggleStudentQ(false);"/> No <br />

						<h3 class="studentQ preferenceLabel">What is your major?</h3>
						<input type="text" name="major" id="major" class="studentQ" />
						
						<h3 class="studentQ preferenceLabel">Are you in Greek life?</h3>
						<input type="radio" name="isGreek" id="isGreek" class="studentQ" value="1" /><span class="studentQ">Yes</span>
						<input type="radio" name="isGreek" id="isGreek" class="studentQ" value="0" /><span class="studentQ">No</span>
					</td>
					<td>
						<h3 class="preferenceLabel"> What is your age? </h3>
						<input type="range" class="slider" name="age" min="16" max="70" value="20" oninput="ageValue.value = age.value + ' years'"/>
						<output name="ageValue" for="age">20 years</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel">Weekday sleeping time</h3>
						<input type="range" class="slider" name="weekdaySleep" min="0" max="6" value="3" oninput="weekdaySleepValue.value = nightTimes(weekdaySleep.valueAsNumber);"/>
						<output name="weekdaySleepValue" for="weekdaySleep">11:00pm</output>
					</td>
					<td>
						<h3 class="preferenceLabel">Weekday waking time</h3>
						<input type="range" class="slider" name="weekdayWake" min="0" max="6" value="3" oninput="weekdayWakeValue.value = morningTimes(weekdayWake.valueAsNumber);"/>
						<output name="weekdayWakeValue" for="weekdayWake">9:00am</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel">Weekend sleeping time</h3>
						<input type="range" class="slider" name="weekendSleep" min="0" max="6" value="3" oninput="weekendSleepValue.value = nightTimes(weekendSleep.valueAsNumber);"/>
						<output name="weekendSleepValue" for="weekendSleep">11:00pm</output>
					</td>
					<td>
						<h3 class="preferenceLabel">Weekend waking time</h3>
						<input type="range" class="slider" name="weekendWake" min="0" max="6" value="3" oninput="weekendWakeValue.value = morningTimes(weekendWake.valueAsNumber);"/>
						<output name="weekendWakeValue" for="weekendWake">9:00am</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel">How often can guests come over?</h3>
						<input type="range" class="slider" name="guestPref" min="0" max="3" value="1" oninput="guestPrefValue.value = comfortableInvite(guestPref.valueAsNumber);" />
						<output name="guestPrefValue" for="guestPref">Sometimes</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel">Select your gender</h3>
						<select name="gender" required>
							<option value="" disabled selected> Choose one </option>
							<option value="0"> Male </option>
							<option value="1"> Female </option>
							<option value="2"> Other </option>
						</select>
					</td>
					<td>
						<h3 class="preferenceLabel"> Roommate gender preference </h3>
						<select name="genderPref" required>
							<option value="0"> Same gender as me </option>
							<option value="1"> Any gender </option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Preferred monthly cost of rent </h3>
						<input type="text" name="rentCostPref" id="rentCostPref" placeholder="$" />
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Select any allergies you have (ctrl+click for multiple)</h3>
						<select name="allergies" multiple>
							<option value="shellfish"> Shellfish </option>
							<option value="dairy"> Dairy </option>
							<option value="peanuts"> Peanuts </option>
							<option value="gluten"> Gluten </option>
							<option value="treenuts"> Tree Nuts </option>
						</select>
					</td>
					<td>
						<h3 class="preferenceLabel"> Select the languages you can speak (ctrl+click for multiple)</h3>
						<select name="languages" multiple required>
							<option value="english" selected> English </option>
							<option value="spanish"> Spanish </option>
							<option value="french"> French </option>
							<option value="mandarin"> Mandarin </option>
							<option value="korean"> Korean </option>
							<option value="german"> German </option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> What types of rooms are you looking for? (ctrl+click for multiple)</h3>
						<select name="roomType" multiple required>
							<option value="studio"> Studio </option>
							<option value="single"> Single </option>
							<option value="double"> Double </option>
							<option value="loft"> Loft </option>
						</select>
					</td>
					<td>
						<h3 class="preferenceLabel"> How long do you plan to live in this room? </h3>
						<input type="range" class="slider" name="stayLength" min="1" max="24" value="6" oninput="stayLengthValue.value = stayLength.value + ' months'"/>
						<output name="stayLengthValue" for="stayLength">6 months</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Do you own a pet? </h3>
						<input type="radio" name="pets" value="1" required> Yes
						<input type="radio" name="pets" value="0"> No
					</td>
					<td>
						<h3 class="preferenceLabel"> Would you be fine with a roommate with a pet? </h3>
						<input type="radio" name="petsPref" value="1" required> Yes
						<input type="radio" name="petsPref" value="0"> No
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Do you smoke tobacco? </h3>
						<input type="radio" name="smoking" value="1" required> Yes
						<input type="radio" name="smoking" value="0"> No
					</td>
					<td>
						<h3 class="preferenceLabel"> Would you be fine with a roommate who smokes? </h3>
						<input type="radio" name="smokingPref" value="1" required> Yes
						<input type="radio" name="smokingPref" value="0"> No
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> How often do you drink alcohol? </h3>
						<input type="range" class="slider" name="drinkingFrq" min="0" max="3" value="0" oninput="drinkingFrqValue.value = frequency(drinkingFrq.valueAsNumber)"/>
						<output name="drinkingFrqValue" for="drinkingFrq"> Never or rarely</output>
					</td>
					<td>
						<h3 class="preferenceLabel"> How often would you be fine with a roommate drinking? </h3>
						<input type="range" class="slider" name="drinkingPref" min="0" max="3" value="0" oninput="drinkingPrefValue.value = frequency(drinkingPref.valueAsNumber)"/>
						<output name="drinkingPrefValue" for="drinkingPref"> Never or rarely</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> How clean do you keep your living space? </h3>
						<input type="range" class="slider" name="cleanliness" min="0" max="3" value="2" oninput="cleanlinessValue.value = howClean(cleanliness.valueAsNumber);"/>
						<output name="cleanlinessValue" for="cleanliness"> Usually clean </output>
					</td>
					<td>
						<h3 class="preferenceLabel"> How clean do you expect your roommate to keep your living space? </h3>
						<input type="range" class="slider" name="cleanlinessPref" min="0" max="3" value="2" oninput="cleanlinessPrefValue.value = howClean(cleanlinessPref.valueAsNumber);"/>
						<output name="cleanlinessPrefValue" for="cleanlinessPref"> Usually clean </output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> How clean do you keep your dishes? </h3>
						<input type="range" class="slider" name="dishes" min="0" max="3" value="2" oninput="dishesValue.value = howClean(dishes.valueAsNumber);"/>
						<output name="dishesValue" for="dishes"> Usually clean </output>
					</td>
					<td>
						<h3 class="preferenceLabel"> How clean do you expect your roommate to keep your dishes? </h3>
						<input type="range" class="slider" name="dishesPref" min="0" max="3" value="2" oninput="dishesPrefValue.value = howClean(dishesPref.valueAsNumber);"/>
						<output name="dishesPrefValue" for="dishesPref"> Usually clean </output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Are you comfortable with sharing your food? </h3>
						<input type="radio" name="sharingFood" value="1" required> Yes
						<input type="radio" name="sharingFood" value="0"> No
					</td>
					<td>
						<h3 class="preferenceLabel"> Are you comfortable with sharing your belongings? </h3>
						<input type="radio" name="sharing" value="1" required> Yes
						<input type="radio" name="sharing" value="0"> No
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> What's your current town? </h3>
						<input type="hidden" name="currentTown" id="currentTown" value=""/>
						<input type="text" name="currentTownMaps" id="currentTownMaps" value=""/>
					</td>
					<td>
						<h3 class="preferenceLabel"> Share a little about yourself :) </h3>
						<textarea rows="5" cols="20" name="bio" id="bio"></textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" id="userId" name="userId" value=<%=request.getAttribute("userId")%>>
			<input type="submit" id="submit" value="SAVE" />
		</form>
		<div id="copyright">© Copyright 2018 Roommate Match Corporation</div>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZqz7hAmRBQA1kStBOHif9DXVMl-JKII0&libraries=places&callback=initMap"
        async defer></script>
	</body>
</html>
