package com.hexaware.amazecare1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/* 
 * Author=Vinayak
 */
@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(name="username")
    private String username; // Single column for username

    @NotEmpty
    @NotBlank
    @Pattern(regexp="[A-Z][a-z]{7,20}")
    private String password; // Login password

    @Column(nullable = false, unique = true)
    @Email
    private String email; // Unique email for admin communication

    
    @Column(nullable = true)
    private String department; // Example: "IT", "HR", "Operations"

    @Size(min=10,max=10,message="Contact must be within desired limits")
    private String contactNumber; // Optional contact number for the admin

    public Admin() {
        super();
    }

    public Admin(int adminId, String username, String password, String email, String role, String department, String contactNumber) {
        super();
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.email = email;
       
        this.department = department;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", department=" + department + ", contactNumber=" + contactNumber + "]";
	}
    
}
   