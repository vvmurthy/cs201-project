package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.FilledPreferences;
import utilities.SqlDriver;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		Connection conn = null;
		Statement st = null; 
		ResultSet rs = null; 
		PreparedStatement ps = null; 
		String obtainedUser = null; 
		String obtainedPass = null; 
		int id = -1;
		try {
				Class.forName("com.mysql.jdbc.Driver"); 
				conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ SqlDriver.DATABASE + "?user=root&password=root&useSSL=false");
				String statement = "SELECT * FROM " + SqlDriver.userTable + " WHERE email = (?)" + 
						" AND user_password = (?);";  
				ps = conn.prepareStatement(statement);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				while (rs.next()) {
					obtainedUser = rs.getString("email"); 
					obtainedPass = rs.getString("user_password");
					id = rs.getInt("UserID");
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
		String nextpage; 
		if (obtainedUser == null || obtainedPass == null) {
			nextpage = "/signin.jsp";
		}else {
			FilledPreferences fp = SqlDriver.getSelfPreferences(id);
			if(fp != null && fp.mapsFilled()) {
				nextpage = "/MatchServlet";
			}else if(fp != null) {
				nextpage = "/map.jsp";
			}else {
				nextpage = "/preferences.jsp";	
			}
		}
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(nextpage); 
		request.setAttribute("userId", id);
		dispatch.forward(request, response);
	}
}
