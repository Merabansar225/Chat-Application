/**
 *  Redirect to registration page when you click on the button
 */

document.addEventListener('DOMContentLoaded', function() {
    
    var registerBtn = document.getElementById('registerBtn');
    
    registerBtn.addEventListener('click', function() {

        window.location.href = 'Registration.jsp';
    });
});