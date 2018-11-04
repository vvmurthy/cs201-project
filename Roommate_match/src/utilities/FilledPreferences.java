package utilities;

import java.io.IOException;
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

}
