package servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.validator.routines.EmailValidator;

import utilities.SqlDriver;

@MultipartConfig
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }
    
    public boolean isEmpty(String str) {
    	return str == null || str.equalsIgnoreCase("");
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors = "";
		
		// Get the fields
		String fullName = request.getParameter("name");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		
		// Perform the validation
		if(isEmpty(fullName)) {
			errors += "fill full name,";
		}
		if(isEmpty(password1)) {
			errors += "fill password,";
		}
		if(isEmpty(password2)) {
			errors += "retype password,";
		}
		if(!password1.equals(password2)) {
			errors += "passwords do not match,";
		}
		if(password1.toLowerCase().equals(password1)) {
			errors += "password must contain capital letter,";
		}
		
		boolean valid = EmailValidator.getInstance().isValid(email);
		if(!valid) {
			errors += "email invalid,";
		}
		
		// Check against SQL to ensure no duplicate email 
		if(SqlDriver.existsEmail(email)) {
			errors += "email matches one in database,";
		}
		
		Part filePart = request.getPart("photo"); // Retrieves <input type="file" name="file">
	    InputStream fileContent = filePart.getInputStream();
		BufferedImage bimg = ImageIO.read(fileContent);
		final File fl = new File(System.getProperty("user.home") + File.separator + email + ".png");
		System.out.println(fl.getAbsolutePath());
		if(bimg == null) {
			errors += "please upload image,";
		}else {
			ImageIO.write(bimg, "png", fl);
		}
		
		// Return the response + upload to server if all fields validate
		if(!errors.equals("")) {
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/register.jsp");
			request.setAttribute("errors", errors);
			dispatch.forward(request, response);
		}else {
			// Insert data into users
			int id = SqlDriver.uploadToDatabase(fullName, password1, email, fl.getAbsolutePath());
			
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/preferences.jsp");
			request.setAttribute("userId", id);
			dispatch.forward(request, response);
		}
	}
}