<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Registration</title>

<!-- Include Bootstrap CSS and JavaScript -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Include FontAwesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<!-- Include Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville&display=swap" rel="stylesheet">

<!-- Link CSS and Javascript -->
<script src="./Scripts/Registration.js" type="text/javascript"></script> 
<link rel="stylesheet" href="./CSS/Registration.css" type="text/css" />

</head>

<body class="bg-blue">
	
	<!-- Header -->
	<div class="bg-blue">
	<div class="header">
		<h2><i class="fa-regular fa-comments"></i> &nbsp; Create New Account</h2>
	</div>


	<div class="container">
	
	<c:if test="${not empty usernameError}">
        <div class="alert alert-danger" role="alert">
            ${usernameError}
        </div>
    </c:if>
    
    <c:if test="${not empty emailError}">
        <div class="alert alert-danger" role="alert">
            ${emailError}
        </div>
    </c:if>
    
    <c:if test="${not empty phoneError}">
        <div class="alert alert-danger" role="alert">
            ${phoneError}
        </div>
    </c:if>
    
	<!-- Registration form with Bootstap validation added -->
	<form id="registrationForm" action="./register" method="POST" class="needs-validation" novalidate>
	
	    <h4><strong>REGISTER HERE:</strong></h4>
	    <br>
	    
	    <div class="mb-3">
	        <label for="firstName">First Name:</label> 
	        <input type="text" id="firstName" name="firstName" class="form-control" value="${firstName}" placeholder="First Name" required>
	        <div class="invalid-feedback">Please provide a first name. </div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="lastName">Last Name:</label> 
	        <input type="text" id="lastName" name="lastName" class="form-control" value="${lastName}" placeholder="Last Name" required>
	        <div class="invalid-feedback"> Please provide a last name. </div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="age">Age:</label>
	        <input type="number" id="age" name="age" class="form-control" value="${age}" placeholder="How old are you?" required>
	        <div class="invalid-feedback"> Please provide your age. </div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="DOB">Date of Birth:</label>
	        <input type="date" id="DOB" name="DOB" class="form-control" value="${DOB}" required>
	        <div class="invalid-feedback">Please provide your date of birth. </div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="username">Username:</label> 
	        <div class="input-group">
	            <div class="input-group-prepend">
	                <span class="input-group-text" id="atSign">@</span>
	            </div>
	            <input type="text" id="username" name="username" class="form-control ${not empty usernameError ? 'is-invalid' : ''}" 
	                value="${username}" placeholder="Username" aria-describedby="atSign" required>
	            <div class="invalid-feedback">${usernameError}</div>
	        </div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="email">Email:</label> 
	        <input type="email" id="email" name="email" class="form-control ${not empty emailError ? 'is-invalid' : ''}" 
	            value="${email}" placeholder="example@gmail.com" required>
	        <div class="invalid-feedback">${emailError}</div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="phone">Phone:</label> 
	        <input type="number" min="10" id="phone" name="phone" class="form-control ${not empty phoneError ? 'is-invalid' : ''}" 
	            value="${phone}" placeholder="Phone" required>
	        <div class="invalid-feedback">${phoneError}</div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="password">Password:</label>
	        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
	        <div class="invalid-feedback"> Please provide a password. </div>
	    </div>
	    
	    <div class="mb-3">
	        <label for="confirmPassword">Confirm Password:</label>
	        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Confirm Password" required>
	        <div class="invalid-feedback"> Please confirm your password. </div>
	    </div>
		
	    <div class="form-group">
	     <div class="form-check">
	      <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
	      <label class="form-check-label" for="invalidCheck">
	        Agree to <a href="termsConditions.jsp"><strong> Terms and Conditions </strong></a>
	      </label>
	      <div class="invalid-feedback"> You must agree before submitting. </div>
	     </div>
	    </div>
	    
	    <button type="submit" class="btn btn-primary">Create Account</button>
	
	</form>
		    <p class="text-center mt-4">Already have an account? <a href="Login.jsp"><strong>Login here</strong></a></p>
	</div>
	</div>

</body>

</html>