<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- :))) -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Social App</title>

<!-- Include Bootstrap CSS and JavaScript + jQuery (Bootstrap version 3 is used here as it's more compatible with the design)-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- CSS styling -->
<link rel="stylesheet" href="./CSS/main.css" type="text/css" />

<!-- Include JavaScript -->
<script src="./Scripts/main.js" type="text/javascript"></script> 

<!-- Include FontAwesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>
<div class="container">
  
  <!-- Dropdown button  -->
  <div class="header">
	<div class="dropdown">
	  <button class="dropbtn"><strong>☰</strong></button>
	  <div id="myDropdown" class="dropdown-content-user">
	    <a href="updateUser.jsp"><i class="fa-solid fa-pen-to-square"></i> &nbsp; Update Profile</a>
        <a href="#" data-toggle="modal" data-target="#confirmDeleteModal"><i class="fa-solid fa-trash-can"></i> &nbsp; Delete Account</a>
	  </div>
	</div>
	
<%-- 	<!-- Bootstrap modal for delete confirmation -->
	<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-body">
	        Are you sure you want to delete your account? This action cannot be undone.
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <a href="delete?userID=${user.userID}" class="btn btn-danger">Delete Account</a>
	      </div>
	    </div>
	  </div>
	</div> --%>
  
  <!-- Main header with title and logout button -->
    <div class="title"><i class="fa-regular fa-comments"></i> &nbsp; My Social App</div>
    <div>
	    <a href="helpDoc.jsp" class="logout" style="margin-right: 10px"><i class="fa-solid fa-circle-info"></i> &nbsp;Help</a>
	    <a href="#" data-toggle="modal" data-target="#confirmLogoutModal" class="logout"><i class="fa-solid fa-right-from-bracket"></i>&nbsp;Logout</a>	
    </div>	
    
  <!-- Bootstrap modal for logout confirmation -->
	<div class="modal fade" id="confirmLogoutModal" tabindex="-1" role="dialog" aria-labelledby="confirmLogoutModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-body">
	        Are you sure you want to log out?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <a href="Login.jsp" class="btn btn-danger">Logout</a>
	      </div>
	    </div>
	  </div>
	</div>
  
  </div>
 
  <div class="main">
  
  <!-- Left column for users list -->
    <div class="left">
      <p class="allUsers">All Users</p>
	    <div class="usersList">
	        <ul>
	          <c:forEach var="user" items="${everyUser}">
	            <li>
	              <a href="userDetails?userID=${user.userID}" class="btn userButton" role="button">
	                  <i class="fa-solid fa-user"></i> &nbsp; ${user.firstName} ${user.lastName}
	              </a>
	            </li>
	          </c:forEach>
	        </ul>
	    </div>
	</div>

  <!-- Center feed column -->
	<div class="center pre-scrollable">
	
	<!-- Display user posts in the center feed column -->
	<c:if test="${not empty userPosts}">
    <div class="posts-container">
      
      <c:forEach items="${userPosts}" var="post">
        <div class="post">
          <div class="post-header">
			  <strong>${post.user.firstName} ${post.user.lastName}</strong>
			  <div class="dropdown-post">
			    <button class="dropbtn-post"><strong>...</strong></button>
			    <div class="dropdown-content">
				  <a href="#" data-toggle="modal" data-target="#updatePostModal${post.postID}"><i class="fa-solid fa-pen-to-square"></i> &nbsp; Update Post</a>
			      <a href="#" data-toggle="modal" data-target="#confirmDeletePostModal${post.postID}"><i class="fa-solid fa-trash-can"></i> &nbsp; Delete Post</a>
			    </div>
			  </div>
	      </div>
	      
	    <!-- Pop up modal for when user tries deleting a post with replies  -->
	    <c:if test="${not empty errorMessage}">
		  <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		     <div class="modal-dialog" role="document">
		         <div class="modal-content">
		             <div class="modal-header">
                         <h5 class="modal-title" id="modalLabel">Post can't be deleted</h5>
		                 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                     <span aria-hidden="true">&times;</span>
		                 </button>
		             </div>
		             <div class="modal-body">${errorMessage}</div>
		             <div class="modal-footer">
		                 <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		             </div>
		         </div>
		     </div>
		  </div>
		</c:if>
		
	    <!-- Update Post Modal -->
		<div class="modal fade" id="updatePostModal${post.postID}" tabindex="-1" role="dialog" aria-labelledby="updatePostModalLabel${post.postID}">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="updatePostModalLabel${post.postID}">Update Post</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <form action="updatePost" method="POST">
		        <div class="modal-body">
		          <input type="hidden" name="postID" value="${post.postID}">
		          <div class="form-group">
		            <label for="postMessage">Message</label>
		            <textarea class="form-control" id="postMessage" name="postMessage">${post.postMessage}</textarea>
		          </div>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
		          <button type="submit" class="btn btn-primary">Save changes</button>
		        </div>
		      </form>
		    </div>
		  </div>
		</div>
	      
		  
		<!-- Bootstrap modal for delete confirmation of each post -->
		<div class="modal fade" id="confirmDeletePostModal${post.postID}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-body">
		        Are you sure you want to delete this post? This action cannot be undone.
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
		        <a href="deletePost?postID=${post.postID}" class="btn btn-danger">Delete Post</a>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Post Message content -->
          <div class="post-content">
		    <div class="textbox">
            	<p>${post.postMessage}</p>
            </div>
          </div>

          <!-- Like + Reply buttons -->
          <div class="post-footer">
			<form class="likeButton" action="likePost" method="POST">
			    <input type="hidden" name="postID" value="${post.postID}" />
			    <button type="submit" class="${post.likedByCurrentUser ? 'btn btn-success' : 'btn btn-default'} like-button">
			        <i class="far fa-heart"></i> Like
			    </button>
			</form>
			<button type="button" class="btn btn-primary replyBtn" data-toggle="modal" data-target="#replyModal${post.postID}"><i class="fa-solid fa-reply"></i> | Reply </button>
          </div><br>
          
          <!-- Reply section under each post -->
           <div class="replies">
            <h5 style="color:#2e6c85"><strong>REPLIES:</strong></h5> 
            <c:forEach items="${post.replies}" var="reply">
            <strong>${reply.user.firstName} ${reply.user.lastName}</strong>
            <div class="reply-content">
                <p class="reply-textbox">${reply.replyMessage}</p>
            </div>
            </c:forEach>
        	</div>
        
          <!-- Add reply modal  -->
          <div class="modal fade" id="replyModal${post.postID}" tabindex="-1" aria-labelledby="replyModalLabel${post.postID}" aria-hidden="true">
	      <div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <h5 class="modal-title" id="replyModalLabel${post.postID}">Reply to Post</h5>
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	              <span aria-hidden="true">&times;</span>
	            </button>
	          </div>
	          <form action="addReply" method="POST">
	            <div class="modal-body">
	              <input type="hidden" name="userID" value="${user.userID}" />
	              <input type="hidden" name="postID" value="${post.postID}" />
	              <div class="form-group">
	                <label for="replyMessage${post.postID}">Your Reply</label>
	                <textarea class="form-control" id="replyMessage${post.postID}" name="replyMessage" required></textarea>
	              </div>
	            </div>
	            <div class="modal-footer">
	              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	              <button type="submit" class="btn btn-primary">Post Reply</button>
	            </div>
	          </form>
	        </div>
	      </div>
	    </div>
	    
          
        </div>
      </c:forEach>
    </div>
    </c:if>

	</div>
   
   
  <!-- Right Notifications column -->
    <div class="right">
      <div class="welcome">Welcome Back ${user.firstName}!</div>
      <!-- Post something button -->
	    <button type="button" class="btn btn-primary postBtn" data-toggle="modal" data-target="#postModal">
		  <i class="fa-solid fa-feather-pointed"></i>&nbsp;&nbsp;Create a Post ...
		</button>
      <div class="notifications">Notifications</div>
    </div>
    
    <!--Bootstrap Modal pop up form to allow user to post-->
	<div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="modalLabel">Post Something</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	        <form action="CreatePost" method="POST">
			  <div class="mb-3">
			    <label for="postText" class="form-label">Write your thoughts...</label>
			    <textarea class="form-control" id="postText" name="postMessage" required></textarea>
			  </div>
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button type="submit" class="btn btn-primary">Post</button>
			</div>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
	
  </div>
  
  <div class="footer" style="text-align: center; padding: 20px 0;">
  	<footer>&copy; Copyright 2024 by Merab Ansar. All Rights Reserved.</footer>
  </div>
</div>


</body>

</html>
