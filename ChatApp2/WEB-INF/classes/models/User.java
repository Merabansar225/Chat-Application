package models;

import java.time.LocalDate;

// :)))

public class User {

	private int userID;
	private String firstName;
	private String lastName;
	private int age;
	private LocalDate DOB;
	private String username;
	private String email;
	private String phone;
	private String password;
	

	// Constructor for initializing the user object with provided values
	public User(int userID, String firstName, String lastName, int age, LocalDate DOB, String username, String email, String phone, String password) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.DOB = DOB;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		
	}
	
	// Constructor for initializing the user object without the userID 
	public User(String firstName, String lastName, int age, LocalDate DOB, String username, String email, String phone, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.DOB = DOB;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		
	}
	
	// Constructor
	public User() {
		
	}

	// Getters and setters for the user properties
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate DOB) {
		this.DOB = DOB;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
    // Method to return a string representation of the user object, which can be useful for debugging purposes
	@Override
	public String toString() {
		return "User [ID = " + userID + ", FirstName = " + firstName + ", LastName = " + lastName + ", Age = " + age + ", DOB = " + DOB
				+ ", Username = " + username + ", Email = " + email + ", Phone = " + phone + ", Password = " + password + "]";
	}

}
