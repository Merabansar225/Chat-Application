package models;

// :)))

public class Reply {

	private int replyID;
	private int userID;
	private int postID;
	private boolean replyNotification;
	private String replyMessage;
	private User user; // this object represents the author of the reply
	

	// Constructor for initializing the reply object with provided values
	public Reply(int replyID, int userID, int postID, boolean replyNotification, String replyMessage) {
		this.replyID = replyID;
		this.userID = userID;
		this.postID = postID;
		this.replyNotification = replyNotification;
		this.replyMessage = replyMessage;
		
	}
	
	// Constructor for initializing the reply object without the reply id
	public Reply(int userID, int postID, boolean replyNotification, String replyMessage) {
		this.userID = userID;
		this.postID = postID;
		this.replyNotification = replyNotification;
		this.replyMessage = replyMessage;
		
	}
	
	// Constructor
	public Reply() {
		
	}

	// Getters and setters for the reply properties
	
	public int getReplyID() {
		return replyID;
	}

	public void setReplyID(int replyID) {
		this.replyID = replyID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public boolean getReplyNotification() {
		return replyNotification;
	}

	public void setReplyNotification(boolean replyNotification) {
		this.replyNotification = replyNotification;
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	
	public User getUser() {
	        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	
    // Method to return a string representation of the reply object, which can be useful for debugging purposes
	@Override
	public String toString() {
		return "Reply [ID = " + replyID + ", userID = " + userID + ", postID = " + postID + ", replyNotification = " + replyNotification + ", replyMessage = " + replyMessage + "]";
	}
}
