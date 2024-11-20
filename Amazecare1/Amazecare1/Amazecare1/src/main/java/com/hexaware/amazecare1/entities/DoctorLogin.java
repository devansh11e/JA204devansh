package com.hexaware.amazecare1.entities;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
/*
 * Author=DEVANSH
 */
@Entity
@Table(name="Doctor_Login")
public class DoctorLogin {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int Id;
   @Email
   private String Email;
   @NotEmpty
   @NotBlank
   @Pattern(regexp="[A-Z][a-z]{7,20}")
   private String password;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id", nullable = false)
   private Doctor doctor;
   public DoctorLogin()
   {super();}
public DoctorLogin(int id, @jakarta.validation.constraints.Email String email,
		@NotEmpty @NotBlank @Pattern(regexp = "[A-Z][a-z]{7,20}") String password, Doctor doctor) {
	super();
	Id = id;
	Email = email;
	this.password = password;
	this.doctor = doctor;
}
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Doctor getDoctor() {
	return doctor;
}
public void setDoctor(Doctor doctor) {
	this.doctor = doctor;
}
@Override
public String toString() {
	return "DoctorLogin [Id=" + Id + ", Email=" + Email + ", password=" + password + ", doctor=" + doctor + "]";
}

   
}