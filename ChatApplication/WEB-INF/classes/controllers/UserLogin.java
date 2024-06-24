package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AppDAO;
import models.User;
import util.PasswordHashing;

// :)))

/**
 * Servlet implementation class UserLogin
 */

@WebServlet("/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		String loginFormat = request.getParameter("loginFormat"); 
        String password = request.getParameter("password");
        
        AppDAO dao = new AppDAO();
        
        User user = dao.getUserByUsernameOrEmail(loginFormat);
        
        // here we check if the user exists in the database, and if they do it double checks if the password matches
        if (user != null && PasswordHashing.verifyPassword(password, user.getPassword())) {
            
        	// a new session is created
            HttpSession session = request.getSession();
            session.setAttribute("user", user); 
            
            // this allows all users information to be stored in the session (this is to access user details when the loggen in users clicks on a users name)
            ArrayList<User> allUsers = dao.getAllUsers();
            session.setAttribute("everyUser", allUsers);
            
            // once user is logged in they are redirected to the main page
            response.sendRedirect("./viewPosts");
        } else {
        	// if the user inputs incorrect logging in details the page refreshes and provides error 
            request.setAttribute("errorLogin", "Invalid username/email or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        
        
	}

}

