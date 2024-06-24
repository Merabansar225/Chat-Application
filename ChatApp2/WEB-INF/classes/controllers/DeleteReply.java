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
 * Servlet implementation class DeleteReply
 */
@WebServlet("/deleteReply")
public class DeleteReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// try to get the postID from the requesnt and get access to the current session without making a new one
	    HttpSession session = request.getSession(false);
		int replyID = Integer.parseInt(request.getParameter("replyID"));

		// get the current logged in user details (user object) 
		User user = (User) session.getAttribute("user");
		AppDAO dao = new AppDAO();
		
		// if a user is logged in check the replyID and which users the replies belong to. If replyID corresponds to the logged in user, delete the reply 
		try {
			Reply reply = dao.getReplyByID(replyID);
            
            if (reply != null && reply.getUserID() == user.getUserID()) {
                 dao.deleteReply(replyID);
         		 System.out.println("Reply deleted " + reply );

	        } 
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		// Redirect the user to the main page
		response.sendRedirect("./viewPosts");		
		
	}

}
