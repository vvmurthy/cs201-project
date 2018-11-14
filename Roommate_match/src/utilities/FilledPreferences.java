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
	
	String currentTown;
	public String bio;
	
	
	private static final int MIN_TIME_WAKE = 6;
	private static final int MIN_TIME_SLEEP = 20;
	private static final int FIELDS = 22;
	private static final int DATE_RANGE = 6;
	
	String major;
	
	// Return if maps fields are filled (user completed filling forms)
	// or if they are empty
	public boolean mapsFilled() {
		return mapLat != 0 && mapLong != 0;
	}
	
	// Set id (for retrieving historical comparisons)
	public void setId(int id) {
		this.userId = id;
	}

	public void populate(HttpServletRequest request, int userId) throws IOException{
		// Get parameters
		String errors = "";
		
		this.userId = userId;
		
		isStudent = Integer.parseInt(request.getParameter("isStudent"));
		if(isStudent != 0) {
			isGreek = Integer.parseInt(request.getParameter("isGreek"));
			major = request.getParameter("major");
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
		
		
		allergies = "";
		if(request.getParameterValues("allergies") != null && request.getParameterValues("allergies").length > 0) {
			for(String allergy : request.getParameterValues("allergies")) {
				allergies += allergy + ", ";
			}
			allergies = allergies.substring(0, allergies.length() - 2);
		}
		
		languages = "";
		if(request.getParameterValues("languages") != null 
				&& request.getParameterValues("languages").length > 0) {
			for(String language : request.getParameterValues("languages")) {
				languages += language + ", ";
			}
			languages = languages.substring(0, languages.length() - 2);
		}
		
		languages = request.getParameter("languages");
		
		
		roomType = "";
		for(String room : request.getParameterValues("roomType")) {
			roomType += room + ", ";
		}
		roomType = roomType.substring(0, roomType.length() - 2);
		
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
		
		bio = request.getParameter("bio");
		currentTown = request.getParameter("currentTown");
		
		if(!errors.equals("")) {
			throw new IOException(errors);
		}
	}
	
	public void populate(ResultSet rs) throws SQLException{
		populate(rs, false);
	}
	
	public void populate(ResultSet rs, boolean isGuest) throws SQLException{
		
		if(!isGuest) {
			this.userId = rs.getInt("UserID");
		}
	
		isStudent = rs.getInt("isStudent");
		if(isStudent != 0) {
			isGreek = rs.getInt("studendGreek");
			major = rs.getString("studentMajor");
		}else {
			isGreek = 0;
			major = "";
		}
		
		weekendWake = Integer.toString(Integer.parseInt(
				rs.getTime("weekendWake").toString().split(":")[0]) - MIN_TIME_WAKE)
				+ ":00";
		
		weekdayWake = Integer.toString(Integer.parseInt(
				rs.getTime("weekdayWake").toString().split(":")[0]) - MIN_TIME_WAKE)
				+ ":00";
		
		weekdaySleep = Integer.toString(Integer.parseInt(
				rs.getTime("weekdaySleep").toString().split(":")[0]) - MIN_TIME_SLEEP)
				+ ":00";
		
		weekendSleep = Integer.toString(Integer.parseInt(
				rs.getTime("weekendSleep").toString().split(":")[0]) - MIN_TIME_SLEEP)
				+ ":00";
		
		age = rs.getInt("age");
		
		guestPref = rs.getInt("guestPref");
		
		gender = rs.getInt("gender");
		genderPref = rs.getInt("genderPref");
		
		rentCostPref = rs.getDouble("costPref");
		
		allergies = rs.getString("allergies");
		
		languages = rs.getString("languages");
		
		roomType = rs.getString("roomType");
		
		stayLength = rs.getInt("lengthStay");
		pets = rs.getInt("pets");
		petsPref = rs.getInt("petsPref");
		
		smoking = rs.getInt("smoking");
		smokingPref = rs.getInt("smokingPref");
		
		drinkingFreq = rs.getInt("drinking");
		drinkingPref = rs.getInt("drinkingPref");
		
		cleanliness = rs.getInt("cleanliness");
		cleanlinessPref = rs.getInt("cleanlinessPref");
		
		dishes = rs.getInt("dishes");
		dishesPref = rs.getInt("dishesPref");
		
		sharingFood = rs.getInt("sharingFood");
		sharing = rs.getInt("borrowing");
		
		mapLat = rs.getDouble("mapsLat");
		mapLong = rs.getDouble("mapsLong");
		radius = rs.getDouble("mapsRadius");
		
		currentTown = rs.getString("currentTown");
		bio = rs.getString("bio");
		
	}
	
	// 1 is they do not match, 0 is they match (coefficient for minus)
	public int booleanMatch(int field, int otherField, int fieldPref, int otherFieldPref) {
		if(field > otherFieldPref) {
			return 1;
		}else if(otherField > fieldPref) {
			return 1;
		}
		
		return 0;
	}
	
	// TODO: add guest pref
	public double compare(FilledPreferences other) {
		double percent = 1.0;
		
		// Location
		boolean locationMatch = true;
		if(Math.sqrt(Math.pow(mapLat - other.mapLat, 2) + 
				Math.pow(mapLong - other.mapLong, 2)) > 
		Math.min(radius, other.radius)) {
			locationMatch = false;
		}
		
		if(!locationMatch) {
			percent -= 0.9;
		}
		
		// Student status -> if both are students, and match on greek life
		// and are reasonably close in major
		double match = 100;
		if(isStudent == 0 && other.isStudent == 0) {
			match = 100;
		}else if(Math.abs(isStudent - other.isStudent) >= 0.1) {
			match = 0;
		}else if (major != null && other.major != null) { // both are students
			
			// check if each word of one is included in the other
			int total = major.split(" ").length + other.major.split(" ").length;
			
			int matches = 0;
			for(String word : major.split(" ")) {
				if(other.major.contains(word)) {
					matches++;
				}
			}
			for(String word : other.major.split(" ")) {
				if(major.contains(word)) {
					matches++;
				}
			}
			match -= (match/2 * (1 - (double)matches/total));
			
			
			if(Math.abs(isGreek - other.isGreek) != 0) {
				match -= 50;
			}
		}
		percent -= (1.0 / FIELDS) * (match / 100.0);
		
		
		// Gender
		if(Math.abs(gender - other.gender) != 0) {
			if(genderPref == 0 || other.genderPref == 0) {
				percent = -100;
			}
		}
		
		System.out.println("Gender assignment" + Math.abs(gender - other.gender) +
				" " + (genderPref == 0 || other.genderPref == 0));
		
		// Sleep time weekday TODO check whether this works with 1 time
		// after midnight and one before
		int mySleepHour = Integer.parseInt(weekdaySleep.split(":")[0]);
		int otherSleepHour = Integer.parseInt(other.weekdaySleep.split(":")[0]);
		percent -= (1.0 / FIELDS) * (Math.abs(mySleepHour- otherSleepHour));
		
		// Sleep time weekend
		mySleepHour = Integer.parseInt(weekendSleep.split(":")[0]);
		otherSleepHour = Integer.parseInt(other.weekendSleep.split(":")[0]);
		percent -= (1.0 / FIELDS) * (Math.abs(mySleepHour- otherSleepHour));
		
		// Wake time weekend
		mySleepHour = Integer.parseInt(weekendWake.split(":")[0]);
		otherSleepHour = Integer.parseInt(other.weekendWake.split(":")[0]);
		percent -= (1.0 / FIELDS) * (Math.abs(mySleepHour- otherSleepHour));
		
		// Wake time weekday
		mySleepHour = Integer.parseInt(weekdayWake.split(":")[0]);
		otherSleepHour = Integer.parseInt(other.weekdayWake.split(":")[0]);
		percent -= (1.0 / FIELDS) * (Math.abs(mySleepHour- otherSleepHour));
		
		// Dishes + dishes pref
		percent -= (1.0 / FIELDS) * booleanMatch(dishes, other.dishes, 
				dishesPref, other.dishesPref);
		
		// Cleanliness + cleanliness pref
		percent -= (1.0 / FIELDS) * booleanMatch(cleanliness, other.cleanliness, 
				cleanlinessPref, other.cleanlinessPref);
		
		// Drinking
		percent -= (1.0 / FIELDS) * Math.min(Math.min(drinkingFreq - other.drinkingFreq, 
				drinkingFreq - other.drinkingPref), Math.min(drinkingPref - other.drinkingFreq,
						drinkingPref - other.drinkingPref));
		
		// Smoking
		percent -= (1.0 / FIELDS) * booleanMatch(smoking, other.smoking, 
				smokingPref, other.smokingPref);
		
		// Pets
		percent -= (1.0 / FIELDS) * booleanMatch(pets, other.pets, 
				petsPref, other.petsPref);
		
		// Sharing food
		if(Math.abs(sharingFood - other.sharingFood) != 0) {
			percent -= (1.0 / FIELDS);
		}
		
		// Sharing stuff
		if(Math.abs(sharing - other.sharing) != 0) {
			percent -= (1.0 / FIELDS);
		}
		
		// Rent cost pref
		percent -= (1.0 / FIELDS) * Math.abs(Math.pow(other.rentCostPref - rentCostPref, 
				2)) / Math.pow(Math.max(other.rentCostPref, rentCostPref), 2);
		
		// Room type
		if(!roomType.equals(other.roomType)) {
			percent -= (1.0 / FIELDS);
		}
		
		// Languages
		if(!languages.equals(other.languages)) {
			percent -= (1.0 / FIELDS);
		}
		
		// Allergies
		if((allergies == null && other.allergies != null)
				&& (allergies != null && other.allergies == null)){
			percent -= (1.0 / FIELDS);
		}else if(allergies != null && other.allergies != null && 
				!allergies.equals(other.allergies)) {
			percent -= (1.0 / FIELDS);
		}
		
		// stay length
		percent -= (1.0 / FIELDS) * Math.abs(stayLength - other.stayLength
				) / (stayLength + other.stayLength);
		
		percent = Math.max(percent, 0);
		percent = (percent * 100);
		
		return Math.min(100, Math.max(percent, 0));
	}

	// Getters added to display matchProfile page
	public int getUserId() {
		return userId;
	}


	public int getIsStudent() {
		return isStudent;
	}

	public int getIsGreek() {
		return isGreek;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getLanguages() {
		return languages;
	}

	public String getAllergies() {
		return allergies;
	}

	public double getRentCostPref() {
		return rentCostPref;
	}

	public int getGenderPref() {
		return genderPref;
	}

	public int getGender() {
		return gender;
	}

	public int getGuestPref() {
		return guestPref;
	}

	public String getWeekendWake() {
		return weekendWake;
	}

	public String getWeekendSleep() {
		return weekendSleep;
	}

	public String getWeekdayWake() {
		return weekdayWake;
	}

	public String getWeekdaySleep() {
		return weekdaySleep;
	}

	public int getAge() {
		return age;
	}

	public int getStayLength() {
		return stayLength;
	}

	public int getPets() {
		return pets;
	}

	public int getPetsPref() {
		return petsPref;
	}

	public int getSmoking() {
		return smoking;
	}

	public int getSmokingPref() {
		return smokingPref;
	}

	public int getDrinkingFreq() {
		return drinkingFreq;
	}

	public int getDrinkingPref() {
		return drinkingPref;
	}

	public int getCleanliness() {
		return cleanliness;
	}

	public int getCleanlinessPref() {
		return cleanlinessPref;
	}

	public int getDishes() {
		return dishes;
	}

	public int getDishesPref() {
		return dishesPref;
	}

	public int getSharingFood() {
		return sharingFood;
	}

	public int getSharing() {
		return sharing;
	}

	public double getMapLat() {
		return mapLat;
	}

	public double getMapLong() {
		return mapLong;
	}

	public double getRadius() {
		return radius;
	}

	public String getMajor() {
		return major;
	}
	
	public String getBio() {
		return bio;
	}
	
	public String getCurrentTown() {
		return currentTown;
	}
}
