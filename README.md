This README file contains the instructions for how to run the ChatApplication (good usability app) and the ChatApp2 (poor usability app).

# ChatApplication - My Social App

This is the good usability application that was created for this MSc Project.

## PACKAGES AND CLASSES OVERVIEW
- controllers: This package contains all controller classes which handle incoming HTTP requests, and correspond to specific operations:

	- AddLike: adds likes to the database
    	- AddReply: adds replies to the database
    	- CreatePost: insert a text post to the database
    	- DeleteAccount: deletes a user from the database 
    	- DeletePost: deletes a post from the database
    	- DeleteReply: deletes a reply from the database
    	- RemoveLike: deletes a like from the database
    	- UpdateAccount: updates a user from the database
    	- UpdatePost: updates a post from the database
    	- UpdateReply: updates a reply from the database 
    	- UserDetails: extracts all users data from the database 
    	- UserLogin: allows user to login
    	- UserLogout: allows user to logout
    	- UserRegistration: allows a user to be inserted in the database 
    	- ViewAllPosts: extracts all posts from the database
    	- ViewAllReplies: extracts all relies from the database 


- database: This package includes classes related to database connectivity and CRUD operations:
	- AppDAO.java: This class communicates with my mySQL database, and provides all CRUD operations.


- models: This package contains a model class which defines the structure of the book object:
	
	- Likes class
    	- Post class
    	- Reply class
    	- User class

- web app: This directory contains the view component (JSP files) and CSS stylesheets:
	
	- deleteConfirmation: delete confirmation page
    	- firstPage: welcome page 
    	- Login: login page
    	- Main: main page of the website 
    	- Registration: registration page
    	- updateUser: page to update user personal details
    	- userProfile: page that displays other users contact details
	- css directory: Contains stylesheets corresponding to each JSP page.
	- javascript directory: Contains scripts corresponding to each JSP page.

## RUNNING THE APPLICATION
To run the application, simply navigate to the web app folder and run the firstPage.jsp file.





# ChatApp2 - My Social App

This is the poor usability application that was created for this MSc Project.

## PACKAGES AND CLASSES OVERVIEW
- controllers: This package contains all controller classes which handle incoming HTTP requests, and correspond to specific operations:

	- AddLike: adds likes to the database
    	- AddReply: adds replies to the database
    	- CreatePost: insert a text post to the database
    	- DeleteAccount: deletes a user from the database 
    	- DeletePost: deletes a post from the database
    	- DeleteReply: deletes a reply from the database
    	- RemoveLike: deletes a like from the database
    	- UpdateAccount: updates a user from the database
    	- UpdatePost: updates a post from the database
    	- UpdateReply: updates a reply from the database 
    	- UserDetails: extracts all users data from the database 
    	- UserLogin: allows user to login
    	- UserLogout: allows user to logout
    	- UserRegistration: allows a user to be inserted in the database 
    	- ViewAllPosts: extracts all posts from the database
    	- ViewAllReplies: extracts all relies from the database 


- database: This package includes classes related to database connectivity and CRUD operations:
	- AppDAO.java: This class communicates with the mySQL database, and provides all CRUD operations.


- models: This package contains a model class which defines the structure of the book object:
	
	- Likes class
    	- Post class
    	- Reply class
    	- User class

	- Book.java: This class represents the structure of a book object.

- web app: This directory contains the view component (JSP files) and CSS stylesheets:
	
	- deleteConfirmation: delete confirmation page
    	- firstPage: welcome page 
    	- Login: login page
    	- Main: main page of the website 
    	- Registration: registration page
    	- updateUser: page to update user personal details
    	- userProfile: page that displays other users contact details
	- css directory: Contains stylesheets corresponding to each JSP page.
	- javascript directory: Contains scripts corresponding to each JSP page.

## RUNNING THE APPLICATION
To run the application, simply navigate to the web app folder and run the registration.jsp file.



Last updated: April 30, 2024
