<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>

<!-- Include Bootstrap CSS and JavaScript -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Include FontAwesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<!-- Link CSS and Javascript -->
<script src="./Scripts/Login.js" type="text/javascript"></script> 
<link rel="stylesheet" href="./CSS/Login.css" type="text/css" />

</head>

<body class="bg-blue">
	
	<!-- Header -->
	<div class="header">
		<h2><i class="fa-regular fa-comments"></i> &nbsp; Access Account</h2>
	</div>

	<div class="container">
	
	<!-- Login form with Bootstap validation added -->
    
    <c:if test="${not empty errorLogin}">
        <div class="alert alert-danger" role="alert">
            ${errorLogin}
        </div>
    </c:if> 
	
	<form id="loginForm" action="./login" method="POST">
	    
	    <div class="mb-3">
	        <label for="loginFormat">Username or Email:</label> 
	        <input type="text" id="loginFormat" name="loginFormat" class="form-control" placeholder="Username/Email" required>
	    </div>
	    
	    <div class="mb-3">
	        <label for="password">Password:</label>
	        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
	    </div>
	    
	    <button type="submit" class="btn btn-primary">View Account</button>
	</form>

		 <p class="text-center mt-4">Don't have an account? <a href="Registration.jsp"><strong>Become a Member Here</strong></a></p>
	</div>


</body>
</html>