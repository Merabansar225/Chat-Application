package models;

// :)))

public class Likes {

	private int likeID;
	private int userID;
	private int postID;
	private int replyID;
	private boolean likeNotification;
	

	// Constructor for initializing the likes object with provided values
	public Likes(int likeID, int userID, int postID, int replyID, boolean likeNotification) {
		this.likeID = likeID;
		this.userID = userID;
		this.postID = postID;
		this.replyID = replyID;
		this.likeNotification = likeNotification;
		
	}
	
	// Constructor
	public Likes() {
		
	}

	// Getters and setters for the likes properties
	
	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
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

	public int getReplyID() {
		return replyID;
	}
	
	public void setReplyID(int replyID) {
		this.replyID = replyID;
	}

	public boolean getLikeNotification() {
		return likeNotification;
	}

	public void setLikeNotification(boolean likeNotification) {
		this.likeNotification = likeNotification;
	}
	
    // Method to return a string representation of the likes object, which can be useful for debugging purposes
	@Override
	public String toString() {
		return "Likes [ID = " + likeID + ", userID = " + userID + ", postID = " + postID + ", replyID = " + replyID + ", likeNotification = " + likeNotification + "]";
	}
}
