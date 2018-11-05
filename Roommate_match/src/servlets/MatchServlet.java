package servlets;

import java.util.List;
import java.util.PriorityQueue;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.ComparisonHolder;
import utilities.FilledPreferences;
import utilities.SqlDriver;

@WebServlet("/MatchServlet")
public class MatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchServlet() {
        super();
    }

	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int selfUserId;
		FilledPreferences self;
		if(request.getAttribute("guestId") != null) {
			self = SqlDriver.getGuestProfile(Integer.parseInt(
					(String)request.getAttribute("guestId")));
			selfUserId = -1;
		}else {
			selfUserId = Integer.parseInt((String)request.getAttribute("userId"));
			self = SqlDriver.getSelfProfile(selfUserId);
		}
		
		
		
		// Get all other user profiles from SQL
		List<FilledPreferences> others = SqlDriver.getOtherProfiles(selfUserId);
		
		
		// Get the historical matches
		List<ComparisonHolder> historical = SqlDriver.getHistoricalMatches(selfUserId, self);
		
		// compare the matches
		PriorityQueue<ComparisonHolder> sorted = new PriorityQueue<>();
		for(FilledPreferences other : others) {
			double percent = self.compare(other);
			ComparisonHolder ch = new ComparisonHolder();
			ch.self = self;
			ch.other = other;
			ch.percent = percent;
			sorted.add(ch);
			
			// Remove the old match if applicable and add the new one in 
			SqlDriver.insertNewMatch(ch);
		}
		
		// Return the match info to JSP
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/matchProfile.jsp");
		dispatch.include(request, response);
		request.setAttribute("userId", selfUserId);
		request.setAttribute("matches", sorted);
		dispatch.forward(request, response);
	}
}