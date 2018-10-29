<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Roommate Match</title>
		<link rel="stylesheet" type="text/css" href="./CSS/preferences.css">
	</head>
	<body>
		<h1>Lifestyle Preferences</h1>
		<form action="./PreferencesServlet" method="POST">
			<table>
				<tr>
					<td>
						<input type="range" name="weekend_sleep" min="0" max="4" value="2">
						<h3 class="preference_label">Weekend sleeping time</h3>
					</td>
					<td>
						<input type="range" name="weekend_wake" min="0" max="4" value="2">
						<h3 class="preference_label">Weekend waking time</h3>
					</td>
				</tr>
				<tr>
					<td>
						<input type="range" name="weekday_sleep" min="0" max="4" value="2">
						<h3 class="preference_label">Weekday sleeping time</h3>
					</td>
					<td>
						<input type="range" name="weekday_wake" min="0" max="4" value="2">
						<h3 class="preference_label">Weekday waking time</h3>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>