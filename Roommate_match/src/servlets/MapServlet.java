package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.SqlDriver;

@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MapServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get lat long and radius and save
		int id =0;
		boolean user = true;
		
		// insert guest
		String guestId = request.getParameter("guestId").replaceAll("[^0-9]", "");
		if(!guestId.equals("null/") && !guestId.equals("")) {
			id = Integer.parseInt(guestId);
			user = false;
		// insert user
		}else {
			id = Integer.parseInt(request.getParameter("userId").replaceAll("[^0-9]", ""));
		}
		
		String gottenLat = request.getParameter("lat");
		String gottenLong = request.getParameter("long");
		String gottenRad = request.getParameter("radius");
		if (gottenLat == null || gottenLong == null || gottenRad == null
				|| gottenLat.equals("") || gottenLong.equals("") || gottenRad.equals(""))
		{
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/map.jsp");
			request.setAttribute("err", "Please add to the map where you want to live.");
			request.setAttribute("userId", request.getParameter("userId").replaceAll("[^0-9]", ""));
			request.setAttribute("guestId", guestId);
			
			dispatch.forward(request, response);
			
		}
		
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("long"));
		double radius = Double.parseDouble(request.getParameter("radius"));
		SqlDriver.insertMaps(id, lat, lng, radius, user);
		
		// Forward to match servlet
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/MatchServlet");
		request.setAttribute("userId", request.getParameter("userId").replaceAll("[^0-9]", ""));
		request.setAttribute("guestId", guestId);
		dispatch.forward(request, response);
	}
}