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
import models.Reply;
import models.User;

// :)))

/**
 * Servlet implementation class AddReply
 */
@WebServlet("/addReply")
public class AddReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get access to the current session without making a new one
	 	HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("user");

        // Check if the user is logged in, if not they get redirected to Login page
        if (loggedInUser == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
		int userID = Integer.parseInt(request.getParameter("userID"));
        int postID = Integer.parseInt(request.getParameter("postID"));
        boolean replyNotification = Boolean.parseBoolean(request.getParameter("replyNotification"));
        String replyMessage = request.getParameter("replyMessage");
		
        AppDAO dao = new AppDAO();
        Reply reply = new Reply(userID, postID, replyNotification, replyMessage); 
        
			try {
				// add the reply to the database
				dao.insertReply(reply);
	
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			// once reply is added, redirect user to the main page
			response.sendRedirect("viewPosts");

	}

}
