package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SqlDriver {
	
	public static final String DATABASE = "RoommateMatch";
	public static final String userTable = "UserInfo";
	public static final String preferenceTable = "Preferences";
	
	public static boolean existsEmail(String email) {
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		boolean existsEmail = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+  DATABASE +"?user=root&password=root&useSSL=false");
			
			
			st = conn.prepareStatement("Select * from " + userTable + " where email=(?)");
			st.setString(1, email);
			
			rs = st.executeQuery();
			if(rs.next()) {
				existsEmail = true;
			}
		
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) 
					rs.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close(); 
			}
			catch (SQLException sqle)
			{
				System.out.println("sqle closing streams: " +  sqle.getMessage());
			}
		}
		return existsEmail;
	}
	
	public static int uploadToDatabase(String name, String password, String email, String fileName) {
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		int id = -1;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			st = conn.prepareStatement("INSERT into "+userTable + "(fullname, email, profile_pic_link, user_password) values (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, fileName);
			st.setString(4, password);
			
			st.execute();
			rs = st.getGeneratedKeys();
			
			if(rs.next()) {
				return (int)rs.getLong(1);
			}
		
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) 
					rs.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close(); 
			}
			catch (SQLException sqle)
			{
				System.out.println("sqle closing streams: " +  sqle.getMessage());
			}
		}
		return id;
	}
	
	
	public static void uploadPreferences(FilledPreferences fp, int UserID) {
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			String ps = "INSERT into " + preferenceTable + " (userID," + 
					"    isStudent," + 
					"    studentMajor," + 
					"    studendGreek," + 
					"    weekdaySleep," + 
					"    weekdayWake," + 
					"    weekendSleep," + 
					"    weekendWake," + 
					"    genderPref," + 
					"    gender," + 
					"    costPref, " + 
					"    roomType," + 
					"    lengthStay," + 
					"    age," + 
					"    pets," + 
					"    petsPref," + 
					"    smoking," + 
					"    smokingPref," + 
					"    drinking," + 
					"    drinkingPref," + 
					"    dishes," + 
					"    dishesPref," + 
					"    cleanliness," + 
					"    cleanlinessPref," + 
					"    sharingFood," + 
					"    borrowing)";
			
			ps += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?);";
			
			st = conn.prepareStatement(ps);
			st.setInt(1, UserID);
			st.setInt(2, fp.isStudent);
			st.setString(3, fp.major);
			st.setInt(4, fp.isGreek);
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			st.setTime(5, new java.sql.Time(sdf.parse(fp.weekdaySleep).getTime()));
			st.setTime(6, new java.sql.Time(sdf.parse(fp.weekdayWake).getTime()));
			st.setTime(7, new java.sql.Time(sdf.parse(fp.weekendSleep).getTime()));
			st.setTime(8, new java.sql.Time(sdf.parse(fp.weekendWake).getTime()));
			st.setInt(9, fp.genderPref);
			st.setInt(10, fp.gender);
			st.setDouble(11, fp.rentCostPref);
			st.setString(12, fp.roomType);
			st.setInt(13, fp.stayLength);
			st.setInt(14, fp.age);
			st.setInt(15, fp.pets);
			st.setInt(16, fp.petsPref);
			st.setInt(17, fp.smoking);
			st.setInt(18, fp.smokingPref);
			st.setInt(19, fp.drinkingFreq);
			st.setInt(20, fp.drinkingPref);
			st.setInt(21, fp.dishes);
			st.setInt(22, fp.dishesPref);
			st.setInt(23, fp.cleanliness);
			st.setInt(24, fp.cleanlinessPref);
			st.setInt(25, fp.sharingFood);
			st.setInt(26, fp.sharing);
			st.execute();
		
		} catch (SQLException | ParseException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) 
					rs.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close(); 
			}
			catch (SQLException sqle)
			{
				System.out.println("sqle closing streams: " +  sqle.getMessage());
			}
		}
	}
	
}
