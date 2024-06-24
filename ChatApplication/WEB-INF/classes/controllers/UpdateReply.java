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
import models.Reply;

/**
 * Servlet implementation class UpdateReply
 */
@WebServlet("/updateReply")
public class UpdateReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int replyID = Integer.parseInt(request.getParameter("replyID"));
		AppDAO dao = new AppDAO();
		
		Reply reply = dao.getReplyByID(replyID);
		request.setAttribute("currReply", reply);
	    
		RequestDispatcher rd = request.getRequestDispatcher("./viewPosts");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AppDAO dao = new AppDAO();
		
		// retrieve updated reply details from the request parameters
		int replyID = Integer.parseInt(request.getParameter("replyID"));
        String replyMessage = request.getParameter("replyMessage");

        Reply reply = dao.getReplyByID(replyID);
        
        // set the updated reply
        reply.setReplyMessage(replyMessage);
        
        // update the reply in the database
        try {
            dao.updateReply(reply);
              
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        response.sendRedirect("./viewPosts");
	}

}
