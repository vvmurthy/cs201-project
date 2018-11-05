package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileInfo {
	int userId;
	String email;
	String fullname;
	String profilePicLink;
	
	String homeTown;
	String currentTown;
	String bio;
	
	public void populate(ResultSet rs) throws SQLException {
		userId = rs.getInt("UserID");
		email = rs.getString("email");
		fullname = rs.getString("fullname");
		profilePicLink = rs.getString("profilePicLink");
		
		homeTown = rs.getString("hometown");
		currentTown = rs.getString("currentTown");
		bio = rs.getString("bio");
	}

	public int getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getFullname() {
		return fullname;
	}

	public String getProfilePicLink() {
		return profilePicLink;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public String getCurrentTown() {
		return currentTown;
	}

	public String getBio() {
		return bio;
	}
}