package controllers;

import java.io.IOException;
import java.util.List;

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

// :)))

/**
 * Servlet implementation class DeletePost
 */
@WebServlet("/deletePost")
public class DeletePost extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// try to get the postID from the requesnt and get access to the current session without making a new one
	    HttpSession session = request.getSession(false);
	    int postID = Integer.parseInt(request.getParameter("postID"));
        
        // get the current logged in user details (user object) 
        User user = (User) session.getAttribute("user");
        AppDAO dao = new AppDAO();
        
        // if a user is logged in check the postID and which users the posts belong to. If postID corresponts to the logged in user, delete the post if post doesnt have any replies, otherwise give erro message 
        if (user != null) {
            try {
                Post post = dao.getPostByID(postID);
                List<Reply> replies = dao.getRepliesByPostID(postID);
                
                if (post != null && post.getUserID() == user.getUserID()) {
                     dao.deletePost(postID);
             		 System.out.println("Post deleted " + postID );
                } 
                
                if (replies != null && !replies.isEmpty()) {
                    session.setAttribute("errorMessage", "This post cannot be deleted since it has replies.");
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        response.sendRedirect("./viewPosts"); 

    }
}
