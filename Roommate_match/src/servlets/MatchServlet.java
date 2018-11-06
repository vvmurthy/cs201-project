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

import utilities.ComparisonHolder;
import utilities.FilledPreferences;
import utilities.ProfileInfo;
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
		if(request.getAttribute("guestId") != null && !request.getAttribute("guestId").equals("")) {
			self = SqlDriver.getGuestPreferences(Integer.parseInt(
					(String)request.getAttribute("guestId")));
			selfUserId = -1;
		}else {
			selfUserId = Integer.parseInt((String)request.getAttribute("userId"));
			self = SqlDriver.getSelfPreferences(selfUserId);
		}
		
		// Get all other user profiles from SQL
		List<FilledPreferences> others = SqlDriver.getOtherPreferences(selfUserId);
		
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
		
		List<ProfileInfo> profiles = new LinkedList<>();
		while(!sorted.isEmpty()) {
			ComparisonHolder ch = sorted.remove();
			ProfileInfo profile = SqlDriver.getUserProfile(ch.other.getUserId());
			profile.setComparisonHolder(ch);
			profiles.add(profile);
		}
		
		// Return the match info to JSP
		// TODO find a way to send ProfileInfo along with the sorted matches (match page has to display profile picture and bio)
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/match.jsp");
		request.setAttribute("userId", selfUserId);
		request.setAttribute("profiles", profiles);
		dispatch.forward(request, response);
	}
}