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
function comfortableInvite(val) {
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
function howClean(val) {
	switch(val) {
		case 0:
			return "Messy or unorganized";
		case 1:
			return "Occasionally clean";
		case 2:
			return "Usually clean";
		case 3:
			return "Always clean";
	}
}