package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MapServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Forward to match servlet
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/MatchServlet");
		request.setAttribute("userId", request.getParameter("userId").replaceAll("[^0-9]", ""));
		request.setAttribute("guestId", request.getParameter("guestId").replaceAll("[^0-9]", ""));
		dispatch.forward(request, response);
	}
}