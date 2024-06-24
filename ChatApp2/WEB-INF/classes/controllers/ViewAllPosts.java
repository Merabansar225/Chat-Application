package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AppDAO;
import models.Post;
import models.Reply;
import models.User;

//:)

/**
 * Servlet implementation class ViewAllPosts
 */
@WebServlet("/viewPosts")
public class ViewAllPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       
		// started a new session for the user making the request
		HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        
        // check if the user is logged in
        if (loggedInUser != null) {
            AppDAO dao = new AppDAO();
            ArrayList<Post> allPosts = dao.getAllPosts(); 
           
            // for each loop iterating over each post in the list of all posts to find which replies belongs to which postID + checks if the logged in user has liked a speciific post
            for (Post post : allPosts) {
                List<Reply> replies = dao.getRepliesByPostID(post.getPostID());
                post.setReplies(replies); 
                
                boolean isLikedByUser = dao.userHasLikedPost(loggedInUser.getUserID(), post.getPostID());
                post.setLikedByCurrentUser(isLikedByUser);
            }
            
            // all the posts are added to the request attribute which are made available to the main.jsp
            request.setAttribute("userPosts", allPosts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp"); 
			dispatcher.forward(request, response);
    
        } else {
        	// if no user is logged in, page is redirected to the Login page
            response.sendRedirect("Login.jsp");
        }
    }

}
