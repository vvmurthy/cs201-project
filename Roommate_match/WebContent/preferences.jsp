<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<link rel="stylesheet" type="text/css" href="./CSS/preferences.css">
		<script>
			function nightTimes(val) {
				switch(val) {
					case 0:
						return "8:00pm";
					case 1:
						return "9:00pm";
					case 2:
						return "10:00pm";
					case 3:
						return "11:00pm";
					case 4:
						return "12:00am";
					case 5:
						return "1:00am";
					case 6:
						return "2:00am";
				}
			}
			function morningTimes(val) {
				switch(val) {
					case 0:
						return "6:00am";
					case 1:
						return "7:00am";
					case 2:
						return "8:00am";
					case 3:
						return "9:00am";
					case 4:
						return "10:00am";
					case 5:
						return "11:00am";
					case 6:
						return "12:00pm";
				}
			}
			function comfortableFrq(val) {
				switch(val) {
					case 0:
						return "Rarely";
					case 1:
						return "Sometimes";
					case 2:
						return "Usually";
					case 3:
						return "Any time";
				}
			}
			function toggleStudentQ(student) {
				var studentQs = document.getElementsByClassName("studentQ");
				var display = student==true ? "" : "none";
				for (var i = 0; i < studentQs.length; i++) {
					studentQs[i].style.display = display;
				}
			}
			function frequency(val) {
				switch(val) {
					case 0:
						return "Never or rarely";
					case 1:
						return "Occasionally";
					case 2:
						return "Every few days";
					case 3:
						return "Almost every day";
				}
			}
		</script>
	</head>
	<body>
		<h1>Lifestyle Preferences</h1>
		<form action="./PreferencesServlet" method="POST">
			<table>
				<tr>
					<td>
						<h3 class="preferenceLabel">Are you a student?</h3>
						<input type="radio" name="isStudent" value="1" required onclick="toggleStudentQ(true);"/> Yes <br />
						<input type="radio" name="isStudent" value="0" onclick="toggleStudentQ(false);"/> No <br />

						<h3 class="studentQ preferenceLabel">What is your major?</h3>
						<input type="text" name="major" class="studentQ" />
						
						<h3 class="studentQ preferenceLabel">Are you in Greek life?</h3>
						<input type="radio" name="isGreek" class="studentQ" value="1" required /><span class="studentQ">Yes</span>
						<input type="radio" name="isGreek" class="studentQ" value="0" /><span class="studentQ">No</span>
					</td>
					<td>
						<h3 class="preferenceLabel"> What is your age? </h3>
						<input type="range" name="age" min="16" max="70" value="20" oninput="ageValue.value = age.value + ' years'"/>
						<output name="ageValue" for="age">20 years</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel">Weekday sleeping time</h3>
						<input type="range" name="weekdaySleep" min="0" max="6" value="3" oninput="weekdaySleepValue.value = nightTimes(weekdaySleep.valueAsNumber);"/>
						<output name="weekdaySleepValue" for="weekdaySleep">11:00pm</output>
					</td>
					<td>
						<h3 class="preferenceLabel">Weekday waking time</h3>
						<input type="range" name="weekdayWake" min="0" max="6" value="3" oninput="weekdayWakeValue.value = morningTimes(weekdayWake.valueAsNumber);"/>
						<output name="weekdayWakeValue" for="weekdayWake">9:00am</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel">Weekend sleeping time</h3>
						<input type="range" name="weekendSleep" min="0" max="6" value="3" oninput="weekendSleepValue.value = nightTimes(weekendSleep.valueAsNumber);"/>
						<output name="weekendSleepValue" for="weekendSleep">11:00pm</output>
					</td>
					<td>
						<h3 class="preferenceLabel">Weekend waking time</h3>
						<input type="range" name="weekendWake" min="0" max="6" value="3" oninput="weekendWakeValue.value = morningTimes(weekendWake.valueAsNumber);"/>
						<output name="weekendWakeValue" for="weekendWake">9:00am</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel">How often can guests come over?</h3>
						<input type="range" name="guestPref" min="0" max="3" value="1" oninput="guestPrefValue.value = comfortableFrq(guestPref.valueAsNumber);" />
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
							<option value="3"> Other </option>
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
						<input type="text" name="rentCostPref" placeholder="$" />
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Select any allergies you have (ctrl+click for multiple)</h3>
						<select name="allergies" multiple>
							<!-- TODO add allergies -->
						</select>
					</td>
					<td>
						<h3 class="preferenceLabel"> Select the languages you can speak (ctrl+click for multiple)</h3>
						<select name="languages" multiple required>
							<option value="english" selected> English </option>
							<!-- TODO add languages -->
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Where would you like to search for roommates? </h3>
						<!-- TODO add location selection -->
					</td>
					<td>
						<h3 class="preferenceLabel"> How far from this location should we search for roommates? (radius)</h3>
						<input type="range" name="radius" min="5" max="100" value="50" oninput="radiusValue.value = radius.value + ' miles'" />
						<output name="radiusValue" for="radius">50 miles</output>
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
						<input type="range" name="stayLength" min="1" max="24" value="6" oninput="stayLengthValue.value = stayLength.value + ' months'"/>
						<output name="stayLengthValue" for="stayLength">6 months</output>
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> Are pets allowed? </h3>
						<input type="radio" name="pets" value="1" required> Yes
						<input type="radio" name="pets" value="0"> No
					</td>
					<td>
						<h3 class="preferenceLabel"> Is smoking tobacco allowed? </h3>
						<input type="radio" name="smoking" value="1" required> Yes
						<input type="radio" name="smoking" value="0"> No
					</td>
				</tr>
				<tr>
					<td>
						<h3 class="preferenceLabel"> How often do you drink alcohol? </h3>
						<input type="range" name="drinkingFrq" min="0" max="3" value="0" oninput="drinkingFrqValue.value = frequency(drinkingFrq.valueAsNumber)"/>
						<output name="drinkingFrqValue" for="drinkingFrq"> Never or rarely</output>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>