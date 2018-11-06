package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.FilledPreferences;
import utilities.ProfileInfo;
import utilities.SqlDriver;

@WebServlet("/MatchProfileServlet")
public class MatchProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchProfileServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Guests should be prevented from accessing this page
		int selfUserId = Integer.parseInt((String)request.getParameter("userId"));
		
		// Match id should be sent to this servlet depending on which match is clicked
		int matchId = Integer.parseInt((String)request.getParameter("matchId"));
		ProfileInfo matchInfo = SqlDriver.getUserProfile(matchId);
		FilledPreferences matchPreferences = SqlDriver.getSelfPreferences(matchId);
		
		// Send match profile and preferences to JSP
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/matchProfile.jsp");
		request.setAttribute("userId", selfUserId);
		request.setAttribute("matchId", matchId);
		request.setAttribute("matchInfo", matchInfo);
		request.setAttribute("matchPreferences", matchPreferences);
		dispatch.forward(request, response);
	}
}
