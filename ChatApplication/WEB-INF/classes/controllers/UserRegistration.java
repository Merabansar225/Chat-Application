package controllers;

// :)))

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AppDAO;
import models.User;
import util.PasswordHashing;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/register")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		LocalDate DOB = LocalDate.parse(request.getParameter("DOB")); 
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		AppDAO dao = new AppDAO();
		String hashedPassword = PasswordHashing.hashPassword(password);
		User user = new User(firstName, lastName, age, DOB, username, email, phone, hashedPassword);

		
	    boolean usernameExists = dao.uNameExists(username);
	    boolean emailExists = dao.emailExists(email);
	    boolean phoneExists = dao.phoneExists(phone);

	    // If any of the details exist, set error messages
	    if (usernameExists || emailExists || phoneExists) {
	        if (usernameExists) {
	            request.setAttribute("usernameError", "User with this Username already exists.");
	        }
	        if (emailExists) {
	            request.setAttribute("emailError", "User with this Email already exists.");
	        }
	        if (phoneExists) {
	            request.setAttribute("phoneError", "User with this Phone already exists.");
	        }
	    
	        // Set attributes to retain the form data when page refreshes
	        request.setAttribute("firstName", firstName);
	        request.setAttribute("lastName", lastName);
	        request.setAttribute("age", age);
	        request.setAttribute("DOB", DOB);
	        request.setAttribute("username", username);
	        request.setAttribute("email", email);
	        request.setAttribute("phone", phone);

	        // Forward to the registration page with errors and form data if incorrect details are submitted
	        request.getRequestDispatcher("Registration.jsp").forward(request, response);
	        return;
	    }
		
		// Insert the new user into the database
				try {
					dao.insertUser(user);

				} catch (SQLException se) {
					System.out.println(se.getMessage());
				}
				// Redirect the user to the login page
				response.sendRedirect("registerConfirmation.jsp");

	}

}
//All Passwords are FirstName + 123! eg. Sara123!

