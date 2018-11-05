package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.FilledPreferences;
import utilities.SqlDriver;

@WebServlet("/PreferencesServlet")
public class PreferencesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PreferencesServlet() {
        super();
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get user ID -> no id means guest login (so no INSERT into sql)
		int userId = -1;
		try {
			userId = Integer.parseInt((String) request.getParameter("userId"));
		}catch(NumberFormatException nfe){
		}
		
		// fill and validate
		FilledPreferences fp = new FilledPreferences();
		try {
			fp.populate(request, userId);
		}catch(IOException e) {
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/preferences.jsp");
			request.setAttribute("userId", userId);
			dispatch.include(request, response);
			dispatch.forward(request, response);
		}
		
		// Send to SQL
		if(userId != -1) {
			SqlDriver.uploadPreferences(fp, userId, SqlDriver.preferenceTable);
		}else {
			int id = SqlDriver.uploadPreferences(fp, -1, SqlDriver.guestTable);
			request.setAttribute("guestId", id);
			System.out.println("Uploaded guest");
		}
		
		// Redirect to Map
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/map.jsp");
		request.setAttribute("userId", userId);
		dispatch.include(request, response);
		dispatch.forward(request, response);
	}
}
