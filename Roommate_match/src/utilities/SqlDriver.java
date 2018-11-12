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
	public static final String guestTable = "guestPref";
	public static final String matchesTable = "Matches";
	
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
			sqle.printStackTrace();
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
				sqle.printStackTrace();
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
			sqle.printStackTrace();
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
	
	
	public static int uploadPreferences(FilledPreferences fp, int UserID, String tableName) {
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		int returnNum = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			// Delete preferences if they already exist
			if(tableName.equals(SqlDriver.preferenceTable)) {
				String select = "SELECT * from " + tableName + " where UserID=(?);";
				st = conn.prepareStatement(select);
				st.setInt(1, UserID);
				rs = st.executeQuery();
				
				if(rs.next()) {
					String delete = "DELETE from " + tableName + " where UserID=(?);";
					st.close();
					st = conn.prepareStatement(delete);
					st.setInt(1, UserID);
					st.execute();
					st.close();
				}
			}
		
			String ps = "INSERT into " + tableName;
			
			if(tableName.equals(SqlDriver.preferenceTable)) {
				ps += " (userID, ";
			}else {
				ps += " (";
			}
			
			ps += " isStudent," + 
					"    studentMajor," + 
					"    studendGreek," + 
					"    weekdaySleep," + 
					"    weekdayWake," + 
					"    weekendSleep," + 
					"    weekendWake," + 
					"    genderPref," + 
					"    guestPref," + 
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
					"    borrowing, allergies, languages,"
					+ "currentTown, bio)";
			
			ps += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?";
			if(tableName.equals(SqlDriver.preferenceTable)) {
				ps += ", ?);";
			}else {
				ps += ");";
			}
			
			st = conn.prepareStatement(ps, Statement.RETURN_GENERATED_KEYS);
			int currentIndex = 1;
			
			if(tableName.equals(SqlDriver.preferenceTable)) {
				st.setInt(currentIndex++, UserID);
			}
			
			st.setInt(currentIndex++, fp.isStudent);
			st.setString(currentIndex++, fp.major);
			st.setInt(currentIndex++, fp.isGreek);
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			st.setTime(currentIndex++, new java.sql.Time(sdf.parse(fp.weekdaySleep).getTime()));
			st.setTime(currentIndex++, new java.sql.Time(sdf.parse(fp.weekdayWake).getTime()));
			st.setTime(currentIndex++, new java.sql.Time(sdf.parse(fp.weekendSleep).getTime()));
			st.setTime(currentIndex++, new java.sql.Time(sdf.parse(fp.weekendWake).getTime()));
			st.setInt(currentIndex++, fp.genderPref);
			st.setInt(currentIndex++, fp.gender);
			st.setInt(currentIndex++, fp.guestPref);
			st.setDouble(currentIndex++, fp.rentCostPref);
			st.setString(currentIndex++, fp.roomType);
			st.setInt(currentIndex++, fp.stayLength);
			st.setInt(currentIndex++, fp.age);
			st.setInt(currentIndex++, fp.pets);
			st.setInt(currentIndex++, fp.petsPref);
			st.setInt(currentIndex++, fp.smoking);
			st.setInt(currentIndex++, fp.smokingPref);
			st.setInt(currentIndex++, fp.drinkingFreq);
			st.setInt(currentIndex++, fp.drinkingPref);
			st.setInt(currentIndex++, fp.dishes);
			st.setInt(currentIndex++, fp.dishesPref);
			st.setInt(currentIndex++, fp.cleanliness);
			st.setInt(currentIndex++, fp.cleanlinessPref);
			st.setInt(currentIndex++, fp.sharingFood);
			st.setInt(currentIndex++, fp.sharing);
			st.setString(currentIndex++, fp.allergies);
			st.setString(currentIndex++, fp.languages);
			st.setString(currentIndex++, fp.currentTown);
			st.setString(currentIndex++, fp.bio);
			if(tableName.equals(SqlDriver.preferenceTable)) {
				st.execute();
			}else {
				st.execute();
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					returnNum = (int)rs.getLong(1);
				}
			}
		
		} catch (SQLException | ParseException sqle) {
			sqle.printStackTrace();
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
		return returnNum;
	}
	
	public static List<FilledPreferences> getOtherPreferences(int userId){
		
		List<FilledPreferences> otherUsers = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			String ps = "SELECT * from " + preferenceTable + " where UserID != (?)";
			st = conn.prepareStatement(ps);
			st.setInt(1, userId);
			
			rs = st.executeQuery();
			while(rs.next()) {
				FilledPreferences fp = new FilledPreferences();
				fp.populate(rs);
				otherUsers.add(fp);
			}

		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
		
		return otherUsers;
		
	}
	
	/**
	 * Returns FilledProfile if user has profile, otherwise null
	 * @param userId
	 * @return
	 */
	public static FilledPreferences getSelfPreferences(int userId){
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			String ps = "SELECT * from " + preferenceTable + " where UserID = (?)";
			st = conn.prepareStatement(ps);
			st.setInt(1, userId);
			
			rs = st.executeQuery();
			while(rs.next()) {
				FilledPreferences fp = new FilledPreferences();
				fp.populate(rs);
				return fp;
			}

		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
		
		return null;	
	}
	
	public static FilledPreferences getGuestPreferences(int guestId){
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			String ps = "SELECT * from " + guestTable + " where preferenceID = (?)";
			st = conn.prepareStatement(ps);
			st.setInt(1, guestId);
			
			rs = st.executeQuery();
			while(rs.next()) {
				FilledPreferences fp = new FilledPreferences();
				fp.populate(rs, true);
				return fp;
			}

		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
		
		return null;	
	}
	
	public static ProfileInfo getUserProfile(int userId){
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			ProfileInfo profile = new ProfileInfo();
			st = conn.prepareStatement("SELECT * from " + userTable + " where UserID = (?);");
			st.setInt(1, userId);
			rs = st.executeQuery();
			
			if (rs.next()) {
				profile.populate(rs);
				return profile;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} finally {
			try {
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
		System.out.println("Could not find user profile");
		return null;	
	}
	
	public static List<ComparisonHolder> getHistoricalMatches(int userId,
			FilledPreferences self){
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		List<ComparisonHolder> historical = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			String ps = "SELECT * from " + matchesTable + " where UserID = (?)";
			st = conn.prepareStatement(ps);
			st.setInt(1, userId);
			
			rs = st.executeQuery();
			while(rs.next()) {
				double match = rs.getDouble("percentage");
				FilledPreferences fp = new FilledPreferences();
				fp.setId(rs.getInt("UserID"));
				ComparisonHolder ch = new ComparisonHolder();
				ch.other = fp;
				ch.self = self;
				ch.percent = match;
				historical.add(ch);
			}

		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
		return historical;
	}
	
	public static void insertNewMatch(ComparisonHolder ch) {
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			// Get if the match exists
			String ps = "SELECT * from " + matchesTable + " where UserID = (?) and MatchedID = (?)";
			st = conn.prepareStatement(ps);
			st.setInt(1, ch.self.userId);
			st.setInt(2, ch.other.userId);
			
			// Remove it if it does
			rs = st.executeQuery();
			if(rs.next()) {
				st.close();
				ps = "DELETE from " + matchesTable + " where UserID = (?) and MatchedID = (?)";
				st = conn.prepareStatement(ps);
				st.setInt(1, ch.self.userId);
				st.setInt(2, ch.other.userId);
				st.execute();
				
				ps = "DELETE from " + matchesTable + " where UserID = (?) and MatchedID = (?)";
				st = conn.prepareStatement(ps);
				st.setInt(1, ch.other.userId);
				st.setInt(2, ch.self.userId);
				st.execute();
			}
			
			
			ps = "INSERT INTO " + matchesTable + " (UserID, MatchedID, percentage) VALUES (?, ?, ?)";
			st = conn.prepareStatement(ps);
			st.setInt(1, ch.self.userId);
			st.setInt(2, ch.other.userId);
			st.setDouble(3, ch.percent);
			
			st.execute();
			st.close();
			
			ps = "INSERT INTO " + matchesTable + " (UserID, MatchedID, percentage) VALUES (?, ?, ?)";
			st = conn.prepareStatement(ps);
			st.setInt(1, ch.other.userId);
			st.setInt(2, ch.self.userId);
			st.setDouble(3, ch.percent);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
	
	public static void insertMaps(int id, double lat, double lng, double radius, boolean user) {
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			String table = null;
			String idParam = "";
			if(user) {
				table = preferenceTable;
				idParam = "UserID";
			}else {
				table = guestTable;
				idParam = "preferenceID";
			}
			
			String query  = "UPDATE " + table + " set mapsLat = (?) , mapsLong = (?) , mapsRadius = (?) where " + idParam + " = (?)";
			st = conn.prepareStatement(query);
			
			// insert 
			st.setDouble(1, lat);
			st.setDouble(2, lng);
			st.setDouble(3, radius);
			st.setInt(4, id);
			st.execute();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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