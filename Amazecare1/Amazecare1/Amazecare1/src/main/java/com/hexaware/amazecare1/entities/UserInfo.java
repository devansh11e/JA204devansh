package com.hexaware.amazecare1.entities;
/*
 * Author= Vinayak & Devansh
 */
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="User_Info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @Column(nullable = false)
    @Pattern(regexp = "[A-Z][a-z0-9]+",message = "Name must begin with Uppercase")
    @Size(min=0,max=20,message=" Name must be within desired limits")
    private String name; 
    
    @Email
    private String email; 
    
    @NotEmpty
    @NotBlank
    private String password;
    @NotBlank(message="role can be either admin,patient or doctor")
    private String role;  //Admin, Patient, Doctor
    
    
    


	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

public UserInfo(int id,@Pattern(regexp = "[A-Z][a-z]+", message = "Name must begin with Uppercase") @Size(min = 0, max = 20, message = " Name must be within desired limits") String name,
			@Email String email,
			@NotEmpty @NotBlank String password,
			@NotBlank(message = "role can be either admin,patient or doctor") String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}









	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}
	
   

}
