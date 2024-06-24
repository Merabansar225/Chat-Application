package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AppDAO;

// :)))

/**
 * Servlet implementation class RemoveLike
 */
@WebServlet("/removeLike")
public class RemoveLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		// get the likeID from the requesnt parameter
        int likeID = Integer.parseInt(request.getParameter("likeID"));
        
        AppDAO dao = new AppDAO();
        
        // remove the like from the database
        try {
            dao.deleteLike(likeID);
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        // once a post is unliked, the page is redirected to main page
        response.sendRedirect("./viewPosts");
    }


}
