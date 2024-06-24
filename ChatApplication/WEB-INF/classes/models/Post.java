package models;

import java.util.List;

//:)))

public class Post {

	private int postID;
	private int userID;
	private String postMessage;
	private User user; // this object represents the author of the post
	private List<Reply> replies; // this list represents all replies to a post
	private boolean likedByCurrentUser; // this is used to indicates whether the loggen in user has liked a post
	

	// Constructor for initializing the post object with provided values
	public Post(int postID, int userID, String postMessage) {
		this.postID = postID;
		this.userID = userID;
		this.postMessage = postMessage;

		
	}
	
	// Constructor for initializing the post object with provided values without user id
	public Post( int userID, String postMessage) {
		this.userID = userID;
		this.postMessage = postMessage;

	}
	
	// Constructor
	public Post() {
		
	}

	// Getters and setters for the post properties
	
	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}
	
	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	
    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
    
    public boolean isLikedByCurrentUser() {
        return likedByCurrentUser;
    }

    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        this.likedByCurrentUser = likedByCurrentUser;
    }
    
    // Method to return a string representation of the post object, which can be useful for debugging purposes
	@Override
	public String toString() {
		return "Post [ID = " + postID + ", userID = " + userID + ", postMessage = " + postMessage + "]";
	}
}
