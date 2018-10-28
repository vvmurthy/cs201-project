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

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html"); 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Connection conn = null;
		Statement st = null; 
		ResultSet rs = null; 
		PreparedStatement ps = null; 
		String obtainedUser = null; 
		String obtainedPass = null; 
		try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
				conn = DriverManager.getConnection("jdbc:mysql://localhost/UserInfo?user=root&password=root&useSSL=false");
				String statement = "SELECT * FROM users WHERE username LIKE " + "\"" + username + "\"" + 
						"AND password LIKE " + "\"" + password + "\""; 
				ps = conn.prepareStatement(statement);
				rs = ps.executeQuery();
				while (rs.next()) {
					obtainedUser = rs.getString("username"); 
					obtainedPass = rs.getString("password");
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
		if (obtainedUser == null || obtainedPass == null)
			nextpage = "error.jsp";
		else
			nextpage = "home.jsp";
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(nextpage); 
		dispatch.forward(request, response);
	}
}
