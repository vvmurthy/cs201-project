package servlets;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.FilledPreferences;
import utilities.ProfileInfo;
import utilities.RunUserMatch;
import utilities.SqlDriver;

@WebServlet("/RedirectMatchServlet")
public class RedirectMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RedirectMatchServlet() {
        super();
    }

	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		FilledPreferences self;
		int selfUserId = Integer.parseInt(request.getParameter("userId"));
		self = SqlDriver.getSelfPreferences(selfUserId);
		// Get the matches
		List<ProfileInfo> profiles = RunUserMatch.getMatches(self, selfUserId);
		
		// Return the match info to JSP
		// TODO find a way to send ProfileInfo along with the sorted matches (match page has to display profile picture and bio)
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/match.jsp");
		request.setAttribute("userId", selfUserId);
		request.setAttribute("profiles", profiles);
		dispatch.forward(request, response);
	}
}