package com.hexaware.amazecare1.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min=0,max=20,message=" user name must be within desired limits")
    private String name; 
    
    @Email
    private String email; 
    
    @NotEmpty
    @NotBlank
    @Pattern(regexp="[A-Z][a-z]{7,20}")
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    
    public enum Role {
        DOCTOR,
        PATIENT,
        ADMIN
    }
    
    


	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}




	public UserInfo(int id, @Size(min = 0, max = 20, message = " user name must be within desired limits") String name,
			@Email String email, @NotEmpty @NotBlank @Pattern(regexp = "[A-Z][a-z]{7,20}") String password, Role role) {
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


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}
	
   

}
