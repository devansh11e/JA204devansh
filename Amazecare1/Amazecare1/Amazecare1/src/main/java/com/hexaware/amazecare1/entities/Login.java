package com.hexaware.amazecare1.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
/*
 * Author=DEVANSH
 */
@Entity
@Table(name="Login")
public class Login {
   @Id
   @Email
   private String userId;
   @NotEmpty
   @NotBlank
   @Pattern(regexp="[A-Z][a-z]{7,20}")
   private String password;
   public Login()
   {super();}
public Login(String userId, String password) {
	super();
	this.userId = userId;
	this.password = password;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Login [userId=" + userId + ", password=" + password + "]";
}
   
	
}
