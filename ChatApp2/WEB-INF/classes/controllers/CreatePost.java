package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AppDAO;
import models.Post;
import models.User;

// :)))

/**
 * Servlet implementation class CreatePost
 */
@WebServlet("/CreatePost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	      
			// get access to the current session without making a new one
		 	HttpSession session = request.getSession(false);
	        User loggedInUser = (User) session.getAttribute("user");

	        // Check if the user is logged in, if not they get redirected to Login page
	        if (loggedInUser == null) {
	            response.sendRedirect("Login.jsp");
	            return;
	        }

	        String postMessage = request.getParameter("postMessage");
	        Post post = new Post(loggedInUser.getUserID(), postMessage);
	        AppDAO dao = new AppDAO();
	        
	        try {
	        	// insert new post in the database
	            dao.insertPost(post);

	        } catch (SQLException e) {
	        	System.out.println(e.getMessage());
	        }
	        // redirect user to the main page
            response.sendRedirect("./viewPosts");

	    }
}
