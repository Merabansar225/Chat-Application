<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User Account</title>

<!-- Include Bootstrap CSS and JavaScript -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Include FontAwesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<!-- Link to CSS -->
<link rel="stylesheet" href="./CSS/updateUser.css" type="text/css" />

</head>

<body class="bg-blue">
   
    <!-- Header -->
    <div class="header">
        <h2><i class="fas fa-user-edit"></i> &nbsp; Change User Details</h2>
    </div>
    
    <!-- Form container -->
    <div class="container">
    
<%--     <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
            ${success}
        </div>
    </c:if> --%>
		<!-- Form to update details -->
        <form action="./update" method="POST">

			<input type="hidden" name="userID" value="${user.userID}">

            <label for="firstName">First Name</label> 
            <input type="text" id="firstName" name="firstName" value="${user.firstName}" class="form-control"> <br>
            
            <label for="lastName">Last Name</label> 
            <input type="text" id="lastName" name="lastName" value="${user.lastName}" class="form-control"> <br>
            
            <label for="age">Age</label>
            <input type="number" id="age" name="age" value="${user.age}" min="0" max="120" class="form-control"> <br>
            
            <label for="DOB">DOB</label>
            <input type="date" id="DOB" name="DOB" value="${user.DOB}" class="form-control"> <br>
            
            <label for="username">Username</label> 
            <input type="text" id="username" name="username" value="${user.username}" class="form-control" readonly> <br>
            
            <label for="email">Email</label> 
            <input type="email" id="email" name="email" value="${user.email}" class="form-control"> <br>
            
            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" value="${user.phone}" class="form-control"> <br>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" value="${user.password}" class="form-control" readonly> <br>
            
            <input type="submit" value="Submit" class="btn btn-primary">
        </form>
        
<!--         <p class="text-center mt-4"><a href="viewPosts"><strong>Return to Home page</strong></a></p>
 -->
    </div>

</body>
</html>
