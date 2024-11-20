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
 * Author=VINAYAK
 */
@Entity
@Table(name="Patient_Login")
public class PatientLogin {
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
   @JoinColumn(name = "patient_id",referencedColumnName = "patient_id", nullable = false)
   private Patient patient; // Reference to the Patient entity
   
   public PatientLogin()
   {super();}

public PatientLogin(int id, @jakarta.validation.constraints.Email String email,
		@NotEmpty @NotBlank @Pattern(regexp = "[A-Z][a-z]{7,20}") String password, Patient patient) {
	super();
	Id = id;
	Email = email;
	this.password = password;
	this.patient = patient;
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

public Patient getPatient() {
	return patient;
}

public void setPatient(Patient patient) {
	this.patient = patient;
}

@Override
public String toString() {
	return "PatientLogin [Id=" + Id + ", Email=" + Email + ", password=" + password + ", patient=" + patient + "]";
}
   
   
   
}
