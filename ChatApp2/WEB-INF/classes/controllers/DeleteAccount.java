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

// :))))

/**
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/delete")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get access to the current session without making a new one
		HttpSession session = request.getSession(false);
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		AppDAO dao = new AppDAO();
		
		// delete the user and invalidate the session 
		try {
			if (dao.deleteUser(userID)) {
                session.invalidate(); 
            }
			
			// redirect the user to the delete confirmation page
            response.sendRedirect("Login.jsp");
    		System.out.println("Account deleted " + userID);
        
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
