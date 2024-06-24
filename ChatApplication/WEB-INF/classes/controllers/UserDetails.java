package controllers;

// :)))

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AppDAO;
import models.User;

/**
 * Servlet implementation class FriendDetails
 */
@WebServlet("/userDetails")
public class UserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get user details via their userID and show them in the userProfile.jsp page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userID = Integer.parseInt(request.getParameter("userID"));
	    AppDAO dao = new AppDAO();
	    User user = dao.getUserByID(userID);
 
	    request.setAttribute("user", user);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("userProfile.jsp");
	    dispatcher.forward(request, response);
	}

}


