package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AppDAO;
import models.User;

// :)))

/**
 * Servlet implementation class UpdateAcount
 */
@WebServlet("/update")
public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// this method allows user to see their current details when updating 
		int userID = Integer.parseInt(request.getParameter("userID"));
		AppDAO dao = new AppDAO();
		
		User user = dao.getUserByID(userID);
		request.setAttribute("currUser", user);
	    
		System.out.println(userID + user.getFirstName() + user.getLastName() +	user.getAge() + user.getDOB() + user.getEmail() + user.getPhone() + user.getPassword());

		RequestDispatcher rd = request.getRequestDispatcher("updateUser.jsp");
		rd.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// this method allows the update to take place
		AppDAO dao = new AppDAO();
		User usr = new User();

		// updated user details are retrieved from the request parameters
		int userID = Integer.parseInt(request.getParameter("userID"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		String DOBStr = request.getParameter("DOB");
	    LocalDate DOB = null;
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		

	    try {
	    	DOB = LocalDate.parse(DOBStr);
	    } catch (Exception e) {
	        e.printStackTrace();
	     
	    }
	    
		// Set the updated user details
	    usr.setUserID(userID);
	    usr.setFirstName(firstName);
		usr.setLastName(lastName);
		usr.setAge(age);
		usr.setDOB(DOB);
		usr.setUsername(username);
		usr.setEmail(email);
		usr.setPhone(phone);
		usr.setPassword(password);
		
	
		// Update the user in the database
		try {
			
			dao.updateUser(usr);
			request.getSession().setAttribute("user", usr);
		    request.getSession().setAttribute("success", "Account Updated Successfully");
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());

		}
		response.sendRedirect("updateUser.jsp");
	}

}
