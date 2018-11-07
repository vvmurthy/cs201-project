package utilities;

public class IntToStringUtils {
	public static String yesNo(int val) {
		if(val == 0) {
			return "No";
		}else {
			return "Yes";
		}
	}
	public static String nightTimes(int val){
		switch(val){
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
		return "";
	}
	public static String morningTimes(int val){
		switch(val){
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
		return "";
	}
	public static String comfortableInvite(int val) {
		switch(val){
			case 0:
				return "Rarely";
			case 1:
				return "Sometimes";
			case 2:
				return "Usually";
			case 3:
				return "Any time";
		}
		return "";
	}
	public static String frequency(int val){
		switch(val){
			case 0:
				return "Never or rarely";
			case 1:
				return "Occasionally";
			case 2:
				return "Every few days";
			case 3:
				return "Almost every day";
		}
		return "";
	}
	public static String howClean(int val){
		switch(val){
			case 0:
				return "Messy or unorganized";
			case 1:
				return "Occasionally clean";
			case 2:
				return "Usually clean";
			case 3:
				return "Always clean";
		}
		return "";
	}
	public static String gender(int val){
		switch(val){
			case 0:
				return "Male";
			case 1:
				return "Female";
			case 2:
				return "Other";
		}
		return "";
	}
	public static String genderPref(int val){
		switch(val){
			case 0:
				return "Same gender";
			case 1:
				return "Any gender";
		}
		return "";
	}

}
