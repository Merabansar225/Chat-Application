package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Likes;
import models.Post;
import models.Reply;
import models.User;


public class AppDAO {
	

	Connection conn = null;
	Statement stmt = null;
	String user = "ansarmer";
	String password = "Vrigwale8";
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;
	

	public AppDAO() {
		openConnection();
	}


    // Open database connection
	private void openConnection(){
		try{
		    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch(Exception e) { System.out.println(e); }

		try{
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	

    // Close database connection
	private void closeConnection(){
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


// USER CLASS OPERATIONS 
	
	// Creates and returns a User object from the current row of the ResultSet.
		User oneUser = null;
		private User getNextUser(ResultSet rs){
		User thisUser=null;
		try {

			thisUser = new User(
					rs.getInt("userID"),
					rs.getString("firstName"),
					rs.getString("lastName"),
					rs.getInt("age"),
					rs.getDate("DOB").toLocalDate(),
					rs.getString("username"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getString("password"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thisUser;		
	}
	
    // Retrieve all users from the database
	public ArrayList<User> getAllUsers(){
		   
		ArrayList<User> allUsers = new ArrayList<User>();
		openConnection();
		
		try{
		    String selectSQL = "SELECT * FROM User";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
		    while(rs1.next()){
		    	oneUser = getNextUser(rs1);
		    	allUsers.add(oneUser);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allUsers;
    }


	// Retrieve a user by its ID 
	public User getUserByID(int userID) {
	    
		openConnection();
		oneUser = null;
	    try {
	        String selectSQL = "SELECT * FROM User WHERE userID = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setInt(1, userID);
	
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	oneUser = getNextUser(rs);
	        }
	
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	    }
	
	    return oneUser;
	 }
	
	
	// Retrieve a user by its username or email 
	public User getUserByUsernameOrEmail(String loginFormat) {
	    
		openConnection();
	    User oneUser = null;
	    try {
	        String selectSQL = "SELECT * FROM User WHERE username = ? OR email = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setString(1, loginFormat);
	        pstmt.setString(2, loginFormat);

	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            oneUser = getNextUser(rs);
	        }

	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	    }

	    return oneUser;
	}

	
	// Check if user username exists already
	public boolean uNameExists(String username) {
		boolean bool = false;
	    openConnection();
	    try {
	        String selectSQL = "SELECT COUNT(*) FROM User WHERE username = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setString(1, username);
	        
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            bool = rs.getInt(1) > 0;
	        }
	        
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	  
	    }
	    return bool;
	 }
	
	// Check if user email exists already
	public boolean emailExists(String email) {
		boolean bool = false;
	    openConnection();
	    try {
	        String selectSQL = "SELECT COUNT(*) FROM User WHERE email = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setString(1, email);
	        
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            bool = rs.getInt(1) > 0;
	        }
	        
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	  
	    }
	    return bool;
	 }
	
	// Check if user phone number exists already
	public boolean phoneExists(String phone) {
		boolean bool = false;
	    openConnection();
	    try {
	        String selectSQL = "SELECT COUNT(*) FROM User WHERE phone = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setString(1, phone);
	        
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            bool = rs.getInt(1) > 0;
	        }
	        
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	  
	    }
	    return bool;
	 }
	
	// Insert a new user into the database
	public boolean insertUser(User u) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
			String insertSQL = "INSERT INTO User (firstName, lastName, age, DOB, username, email, phone, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, u.getFirstName());
			pstmt.setString(2, u.getLastName());
			pstmt.setInt(3, u.getAge());
			pstmt.setDate(4, java.sql.Date.valueOf(u.getDOB()));
			pstmt.setString(5, u.getUsername());
			pstmt.setString(6, u.getEmail());
			pstmt.setString(7, u.getPhone());
			pstmt.setString(8, u.getPassword());
			
			int rowAdded = pstmt.executeUpdate();
			if (rowAdded > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("User Not Added" + s.getMessage());
		}
		return bool;
	}



	// Update an existing user in the database
	public boolean updateUser(User u) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
	        String updateSQL = "UPDATE User SET firstName = ?, lastName = ?, age = ?, DOB = ?, username = ?, email = ?, phone = ?, password = ? WHERE userID = ?";

			PreparedStatement pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, u.getFirstName());
			pstmt.setString(2, u.getLastName());
			pstmt.setInt(3, u.getAge());
			pstmt.setDate(4, java.sql.Date.valueOf(u.getDOB()));
			pstmt.setString(5, u.getUsername());
			pstmt.setString(6, u.getEmail());
			pstmt.setString(7, u.getPhone());
			pstmt.setString(8, u.getPassword());
			pstmt.setInt(9, u.getUserID());
			
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("User details Not Updated");
		}
		return bool;
	}


	// Delete a user from the database
	public boolean deleteUser(int userID) throws SQLException {
		boolean bool = false;
		openConnection();	
	    try {
	        conn.setAutoCommit(false); 

	        String deleteLikesSQL = "DELETE FROM Likes WHERE userID = ?";
	        String deleteReplySQL = "DELETE FROM Reply WHERE userID = ?";
	        String deletePostSQL = "DELETE FROM Post WHERE userID = ?";
	        String deleteUserSQL = "DELETE FROM User WHERE userID = ?";
	        
	        // Delete all specific users likes 
	        try (PreparedStatement pstmtL = conn.prepareStatement(deleteLikesSQL)) {
	        	pstmtL.setInt(1, userID);
	        	pstmtL.executeUpdate();
	        }

	        // Delete all specific user replies
	        try (PreparedStatement pstmtR = conn.prepareStatement(deleteReplySQL)) {
	        	pstmtR.setInt(1, userID);
	            pstmtR.executeUpdate();
	        }

	        // Delete all specific users posts 
	        try (PreparedStatement pstmtP = conn.prepareStatement(deletePostSQL)) {
	        	pstmtP.setInt(1, userID);
	        	pstmtP.executeUpdate();
	        }

	        // Delete the user
	        try (PreparedStatement pstmtU = conn.prepareStatement(deleteUserSQL)) {
	        	pstmtU.setInt(1, userID);
	            int rowsDeleted = pstmtU.executeUpdate();
	            if (rowsDeleted > 0) {
	                bool = true;
	            }
	        }

	        conn.commit(); 
	        closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
		return bool;
	}
	
// POST CLASS OPERATIONS
	
	// Creates and returns a Post object from the current row of the ResultSet.
	Post onePost = null;
	private Post getNextPost(ResultSet rs){
	Post thisPost=null;
	try {

		thisPost = new Post(
				rs.getInt("postID"),
				rs.getInt("userID"),
				rs.getString("postMessage"));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return thisPost;		
	}
	
	// Retrieve all posts from the database
	public ArrayList<Post> getAllPosts() {
	    ArrayList<Post> allPosts = new ArrayList<>();
	    openConnection();

	    try {
	        String selectSQL = "SELECT Post.*, User.firstName, User.lastName FROM Post JOIN User ON Post.userID = User.userID";
	        ResultSet rs = stmt.executeQuery(selectSQL);

	        while (rs.next()) {
	            Post onePost = getNextPost(rs);

	            User user = new User();
	            user.setFirstName(rs.getString("firstName"));
	            user.setLastName(rs.getString("lastName"));

	            onePost.setUser(user); 

	            allPosts.add(onePost);
	        }

	        stmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	    }

	    return allPosts;
	}


	// Retrieve a post by its ID 
	public Post getPostByID(int postID) {
	    
		openConnection();
		onePost = null;
	    try {
	        String selectSQL = "SELECT * FROM Post WHERE postID = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setInt(1, postID);
	
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	onePost = getNextPost(rs);
	        }
	
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	    }
	
	    return onePost;
	 }
	
	// Insert a new post into the database
	public boolean insertPost(Post p) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
			String insertSQL = "INSERT INTO Post (userID, postMessage) VALUES (?, ?)";
	
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, p.getUserID());
			pstmt.setString(2, p.getPostMessage());
			
			int rowAdded = pstmt.executeUpdate();
			if (rowAdded > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Post Not Added");
		}
		return bool;
	}
	
	
	
	// Update an existing post in the database
	public boolean updatePost(Post p) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
	        String updateSQL = "UPDATE Post SET userID = ?, postMessage = ? WHERE postID = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(updateSQL);
			pstmt.setInt(1, p.getUserID());
			pstmt.setString(2, p.getPostMessage());
			pstmt.setInt(3, p.getPostID());
			
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Post details Not Updated");
		}
		return bool;
	}
	
	
	// Delete a post from the database
	public boolean deletePost(int post) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
		    String deleteSQL = "DELETE FROM Post WHERE postID = ?";
			PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, post);
			
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Post Not Deleted");
		}		
		return bool;
	}

// REPLY CLASS OPERATIONS
	
	// Creates and returns a Reply object from the current row of the ResultSet.
	Reply oneReply = null;
	private Reply getNextReply(ResultSet rs){
	Reply thisReply=null;
	try {

		thisReply = new Reply(
				rs.getInt("replyID"),
				rs.getInt("userID"),
				rs.getInt("postID"),
				rs.getBoolean("replyNotification"),
				rs.getString("replyMessage"));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return thisReply;		
	}
	
	// Retrieve all replies from the database
	public ArrayList<Reply> getAllReplies(){
		   
		ArrayList<Reply> allReplies = new ArrayList<Reply>();
		openConnection();
		
		try{
		    String selectSQL = "SELECT * FROM Reply";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
		    while(rs1.next()){
		    	oneReply = getNextReply(rs1);
		    	allReplies.add(oneReply);
		   }
	
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	
	   return allReplies;
	}
	
	// Retrieve a Reply by its ID 
	public Reply getReplyByID(int replyID) {
	    
		openConnection();
		oneReply = null;
	    try {
	        String selectSQL = "SELECT * FROM Reply WHERE replyID = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setInt(1, replyID);
	
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	oneReply = getNextReply(rs);
	        }
	
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	    }
	
	    return oneReply;
	 }
	
	// Retrive all replies by their corresponding postID
	public ArrayList<Reply> getRepliesByPostID(int postID) {
	    ArrayList<Reply> replies = new ArrayList<>();
	    openConnection();
	    try {
	        String selectSQL = "SELECT Reply.*, User.firstName, User.lastName FROM Reply JOIN User ON Reply.userID = User.userID WHERE postID = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setInt(1, postID);

	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            Reply reply = getNextReply(rs);
	            
	            User user = new User();
	            user.setUserID(rs.getInt("userID")); 
	            user.setFirstName(rs.getString("firstName")); 
	            user.setLastName(rs.getString("lastName"));
	            reply.setUser(user);
	            
	            replies.add(reply);
	        }

	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	    }

	    return replies;
	}

	
	// Insert a new Reply into the database
	public boolean insertReply(Reply r) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
			String insertSQL = "INSERT INTO Reply (userID, postID, replyNotification, replyMessage) VALUES (?, ?, ?, ?)";
	
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, r.getUserID());
			pstmt.setInt(2, r.getPostID());
			pstmt.setBoolean(3, r.getReplyNotification());
			pstmt.setString(4, r.getReplyMessage());
			
			int rowAdded = pstmt.executeUpdate();
			if (rowAdded > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Reply Not Added");
		}
		return bool;
	}
	
	
	
	// Update an existing reply in the database
	public boolean updateReply(Reply r) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
	        String updateSQL = "UPDATE Reply SET userID = ?, postID = ?, replyNotification = ?, replyMessage = ? WHERE replyID = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(updateSQL);
			pstmt.setInt(1, r.getUserID());
			pstmt.setInt(2, r.getPostID());
			pstmt.setBoolean(3, r.getReplyNotification());
			pstmt.setString(4, r.getReplyMessage());
			pstmt.setInt(5, r.getReplyID());
			
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Reply details Not Updated");
		}
		return bool;
	}
	
	
	// Delete a Reply from the database
	public boolean deleteReply(int reply) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
		    String deleteSQL = "DELETE FROM Reply WHERE replyID = ?";
			PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, reply);
			
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Reply Not Deleted");
		}		
		return bool;
	}
	
// LIKES CLASS OPERATIONS 
	
	// Creates and returns a likes object from the current row of the ResultSet.
	Likes oneLike = null;
	private Likes getNextLike(ResultSet rs){
	Likes thisLike=null;
	try {

		thisLike = new Likes(
				rs.getInt("likeID"),
				rs.getInt("userID"),
				rs.getInt("postID"),
				rs.getInt("replyID"),
				rs.getBoolean("likeNotification"));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return thisLike;		
	}
	
	// Retrieve all likes from the database (could use in the future to allow users to be able to see their liked posts)
	public ArrayList<Likes> getAllLikes(){
		   
		ArrayList<Likes> allLikes = new ArrayList<Likes>();
		openConnection();
		
		try{
		    String selectSQL = "SELECT * FROM Likes";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
		    while(rs1.next()){
		    	oneLike = getNextLike(rs1);
		    	allLikes.add(oneLike);
		   }
	
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	
	   return allLikes;
	}
	
	// Retrieve a Like by its ID 
	public Likes getLikeByID(int likeID) {
	    
		openConnection();
		oneLike = null;
	    try {
	        String selectSQL = "SELECT * FROM Likes WHERE likeID = ?";
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setInt(1, likeID);
	
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	oneLike = getNextLike(rs);
	        }
	
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException se) {
	        System.out.println(se);
	    }
	
	    return oneLike;
	 }
	
	// Insert a new Like into the database
	public boolean insertLike(Likes lk) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
			String insertSQL = "INSERT INTO Likes (userID, postID, replyID, likeNotification) VALUES (?, ?, ?, ?)";
	
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, lk.getUserID());
			pstmt.setInt(2, lk.getPostID());
			if (lk.getReplyID() == 0) { 
	            pstmt.setObject(3, null);
	        } else {
	            pstmt.setInt(3, lk.getReplyID());
	        }
			pstmt.setBoolean(4, lk.getLikeNotification());
			System.out.println("likeNotification value being inserted: " + lk.getLikeNotification());

			int rowAdded = pstmt.executeUpdate();
			if (rowAdded > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Like Not Added");
		}
		return bool;
	}
	
	// Check if a specific user has liked a specific post
	public boolean userHasLikedPost(int userId, int postId) {
	    openConnection();
	    try {
	        String selectSQL = "SELECT COUNT(*) FROM Likes WHERE userID = ? AND postID = ?";
	        
	        PreparedStatement pstmt = conn.prepareStatement(selectSQL);
	        pstmt.setInt(1, userId);
	        pstmt.setInt(2, postId);
	        
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	        
	        pstmt.close();
	        closeConnection();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } 
	    return false;
	}
	
	
	// Delete a like from the database
	public boolean deleteLike(int like) throws SQLException {
		boolean bool = false;
		openConnection();
		try {
		    String deleteSQL = "DELETE FROM Likes WHERE likeID = ?";
			PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, like);
			
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				bool = true;
			}
			
			pstmt.close();
			closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Like Not Deleted");
		}		
		return bool;
	}

}
