<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>

<!-- Include Bootstrap CSS and JavaScript -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Include FontAwesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<!-- Link to CSS -->
<link rel="stylesheet" href="./CSS/userProfile.css" type="text/css" />

</head>
<body class="bg-blue">
    <div class="container">
        
        <!-- Header -->
        <div class="header">
            <h2><i class="fa-regular fa-user"></i> &nbsp; User Profile</h2>
        </div>

        <!-- Profile Information -->
        <div class="info">
            <p><strong>First Name:</strong> ${user.firstName}</p>
            <p><strong>Last Name:</strong> ${user.lastName}</p>
            <p><strong>Username:</strong> ${user.username}</p>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Phone:</strong> ${user.phone}</p>	
            <p><a href="Chat.jsp" class="btn messageButton"><i class="fa-regular fa-comments"></i> &nbsp; Message</a></p>            
        </div>
        <div class="text-center mt-4">
            <a href="viewPosts"><strong>Back to Users List</strong></a>

        </div>
    </div>
</body>
</html>
