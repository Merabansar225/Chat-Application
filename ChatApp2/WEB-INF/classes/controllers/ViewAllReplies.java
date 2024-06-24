package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AppDAO;
import models.Reply;
import models.User;

// :))))

/**
 * Servlet implementation class ViewAllReplies
 */
@WebServlet("/viewReplies")
public class ViewAllReplies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// started a new session for the user making the request
		HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        
        // check if the user is logged in, if yes view all replies under posts
        if (loggedInUser != null) {
			AppDAO dao = new AppDAO();
			ArrayList<Reply> allReplies = dao.getAllReplies();
			
			request.setAttribute("reply", allReplies);
			RequestDispatcher rd = request.getRequestDispatcher("./viewPosts");
			rd.include(request, response);
			
        } else {
        	// if no user is logged in, page is redirected to the Login page
            response.sendRedirect("Login.jsp");
        }

	}

}
