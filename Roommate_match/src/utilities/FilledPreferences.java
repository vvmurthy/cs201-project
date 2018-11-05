package utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

public class FilledPreferences {
	
	int userId;
	int isStudent;
	int isGreek;
	String roomType;
	String languages;
	String allergies;
	double rentCostPref;
	int genderPref;
	int gender;
	int guestPref;
	String weekendWake;
	String weekendSleep;
	String weekdayWake;
	String weekdaySleep;
	int age;
	int stayLength;
	int pets;
	int petsPref;
	int smoking;
	int smokingPref;
	int drinkingFreq;
	int drinkingPref;
	int cleanliness;
	int cleanlinessPref;
	int dishes;
	int dishesPref;
	int sharingFood;
	int sharing;
	
	double mapLat;
	double mapLong;
	double radius;
	
	
	private static final int MIN_TIME_WAKE = 6;
	private static final int MIN_TIME_SLEEP = 20;
	
	String major;

	public void populate(HttpServletRequest request, int userId) throws IOException{
		// Get parameters
		String errors = "";
		
		this.userId = userId;
		
		isStudent = Integer.parseInt(request.getParameter("isStudent"));
		if(isStudent != 0) {
			isGreek = Integer.parseInt(request.getParameter("isGreek"));
			major = request.getParameter("studentMajor");
		}else {
			isGreek = 0;
			major = "";
		}
		
		age = Integer.parseInt(request.getParameter("age"));
		
		
		weekdaySleep = 
				Integer.toString((MIN_TIME_SLEEP + Integer.parseInt(request.getParameter("weekdaySleep"))) % 24)
				+ ":00";
		
		weekdayWake = 
				Integer.toString((MIN_TIME_WAKE + Integer.parseInt(request.getParameter("weekdayWake"))) % 24)
				+ ":00";
		
		weekendSleep = 
				Integer.toString((MIN_TIME_SLEEP + Integer.parseInt(request.getParameter("weekendSleep"))) % 24)
				+ ":00";
		
		weekendWake = 
				Integer.toString((MIN_TIME_WAKE + Integer.parseInt(request.getParameter("weekendWake"))) % 24)
				+ ":00";
	
		guestPref = Integer.parseInt(request.getParameter("guestPref"));
		
		gender = Integer.parseInt(request.getParameter("gender"));
		genderPref = Integer.parseInt(request.getParameter("genderPref"));
		
		try {
			rentCostPref = Double.parseDouble(request.getParameter("rentCostPref"));
		}catch(NumberFormatException e) {
			errors += "could not parse rent,";
		}
		
		allergies = request.getParameter("allergies");
		
		languages = request.getParameter("languages");
		
		roomType = request.getParameter("roomType");
		
		stayLength = Integer.parseInt(request.getParameter("stayLength"));
		pets = Integer.parseInt(request.getParameter("pets"));
		petsPref = Integer.parseInt(request.getParameter("petsPref"));
		
		smoking = Integer.parseInt(request.getParameter("smoking"));
		smokingPref = Integer.parseInt(request.getParameter("smokingPref"));
		
		drinkingFreq = Integer.parseInt(request.getParameter("drinkingFrq"));
		drinkingPref = Integer.parseInt(request.getParameter("drinkingPref"));
		
		cleanliness = Integer.parseInt(request.getParameter("cleanliness"));
		cleanlinessPref = Integer.parseInt(request.getParameter("cleanlinessPref"));
		
		dishes = Integer.parseInt(request.getParameter("dishes"));
		dishesPref = Integer.parseInt(request.getParameter("dishesPref"));
		
		sharingFood = Integer.parseInt(request.getParameter("sharingFood"));
		sharing = Integer.parseInt(request.getParameter("sharing"));	
		
		if(!errors.equals("")) {
			throw new IOException(errors);
		}
	}
	
	public void populate(ResultSet rs) throws SQLException{
		this.userId = rs.getInt("UserID");
		
		isStudent = rs.getInt("isStudent");
		if(isStudent != 0) {
			isGreek = rs.getInt("isGreek");
			major = rs.getString("studentMajor");
		}else {
			isGreek = 0;
			major = "";
		}
		
		weekendWake = Integer.toString(Integer.parseInt(
				rs.getTime("weekendWake").toString().split(":")[0]) - MIN_TIME_WAKE);
		
		weekdayWake = Integer.toString(Integer.parseInt(
				rs.getTime("weekdayWake").toString().split(":")[0]) - MIN_TIME_WAKE);
		
		weekdaySleep = Integer.toString(Integer.parseInt(
				rs.getTime("weekdaySleep").toString().split(":")[0]) - MIN_TIME_SLEEP);
		
		weekendSleep = Integer.toString(Integer.parseInt(
				rs.getTime("weekendSleep").toString().split(":")[0]) - MIN_TIME_SLEEP);
		
		age = rs.getInt("age");
		
		guestPref = rs.getInt("guestPref");
		
		gender = rs.getInt("gender");
		genderPref = rs.getInt("genderPref");
		
		rentCostPref = rs.getDouble("rentCostPref");
		
		allergies = rs.getString("allergies");
		
		languages = rs.getString("languages");
		
		roomType = rs.getString("roomType");
		
		stayLength = rs.getInt("stayLength");
		pets = rs.getInt("pets");
		petsPref = rs.getInt("petsPref");
		
		smoking = rs.getInt("smoking");
		smokingPref = rs.getInt("smokingPref");
		
		drinkingFreq = rs.getInt("drinkingFrq");
		drinkingPref = rs.getInt("drinkingPref");
		
		cleanliness = rs.getInt("cleanliness");
		cleanlinessPref = rs.getInt("cleanlinessPref");
		
		dishes = rs.getInt("dishes");
		dishesPref = rs.getInt("dishesPref");
		
		sharingFood = rs.getInt("sharingFood");
		sharing = rs.getInt("sharing");
		
		mapLat = rs.getDouble("mapsLat");
		mapLong = rs.getDouble("mapsLong");
		radius = rs.getDouble("mapsRadius");
	}
	
	public double compare(FilledPreferences other) {
		double percent = 100;
		
		// Location
		
		// Student status
		
		// Greek status 
		
		// Major status
		
		// Gender
		
		// Sleep time weekday
		
		// Sleep time weekend
		
		// Wake time weekend
		
		// Wake time weekday
		
		// Dishes
		
		// Cleanliness
		
		// Drinking
		
		// Smoking
		
		// Pets
		
		// Sharing food
		
		// Sharing stuff
		
		// Rent cost pref
		
		// Room type
		
		// Languages
		
		// Allergies
		
		// stay length
		
		// allergies
		
		return percent;
	}
	
}
