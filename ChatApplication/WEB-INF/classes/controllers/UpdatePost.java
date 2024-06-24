package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AppDAO;
import models.Post;

// :)))

/**
 * Servlet implementation class UpdatePost
 */
@WebServlet("/updatePost")
public class UpdatePost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // this doGet method allows the current message info to be seen in textbox whilst updating
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postID = Integer.parseInt(request.getParameter("postID"));
		AppDAO dao = new AppDAO();
		
		Post post = dao.getPostByID(postID);
		request.setAttribute("currPost", post);
	    
		RequestDispatcher rd = request.getRequestDispatcher("./viewPosts");
		rd.include(request, response);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    			
    	// Retrieve updated post details from the request parameters
    	int postId = Integer.parseInt(request.getParameter("postID"));
        String postMessage = request.getParameter("postMessage");
        
        AppDAO dao = new AppDAO();
        Post post = dao.getPostByID(postId);
        
        // set the post message
        post.setPostMessage(postMessage);

        // update the message in the database
        try {
            dao.updatePost(post);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // once update is susccessful, the user is redirected to the main page
        response.sendRedirect("./viewPosts");
    } 
}

