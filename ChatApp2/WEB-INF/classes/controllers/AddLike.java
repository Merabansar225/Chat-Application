package controllers;

// :)))

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.AppDAO;
import models.Likes;
import models.User;

@WebServlet("/likePost")
public class AddLike extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	// get access to the current session without making a new one
    	HttpSession session = request.getSession(false);
    	
    	// if the session is null or user is not loggen in, reditrect to login page
    	if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp");
            return; 
        }
    	User loggedInUser = (User) session.getAttribute("user");

        int postId = Integer.parseInt(request.getParameter("postID"));
        AppDAO dao = new AppDAO();

        try {
        	// this checks if the logged in user has already liked a specific post
            boolean postAlreadyLiked = dao.userHasLikedPost(loggedInUser.getUserID(), postId);

            //if the user hasn't already liked a post, insert a new like in the database
            if (!postAlreadyLiked) {
                Likes newLike = new Likes(0, loggedInUser.getUserID(), postId, 0, true); 
                dao.insertLike(newLike);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // redirect to the main page once a like is inserted
        response.sendRedirect("viewPosts"); 

    }
}
