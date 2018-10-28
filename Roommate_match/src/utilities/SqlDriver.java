package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlDriver {
	
	public static final String DATABASE = "RoommateMatch";
	public static final String userTable = "UserInfo";
	
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
	
	public static void uploadToDatabase(String name, String password, String email, String fileName) {
		Connection conn = null;
		PreparedStatement st = null; 
		ResultSet rs = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE + "?user=root&password=root&useSSL=false");
			
			st = conn.prepareStatement("INSERT into "+userTable + "(fullname, email, profile_pic_link, user_password) values (?, ?, ?, ?)");
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, fileName);
			st.setString(4, password);
			
			st.execute();
		
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
	}
	
}
