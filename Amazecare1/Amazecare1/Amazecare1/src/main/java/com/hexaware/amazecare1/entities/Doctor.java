package com.hexaware.amazecare1.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/*
 * Author=DEVANSH
 */
@Entity
@Table(name="Doctor")
public class Doctor {
  @Id
  @Column(name="doctor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int doctorId;
  @Column(nullable = false)
  @Pattern(regexp = "^Dr\\.\\s?[A-Z][a-z]+(\\s[A-Z][a-z]+)*$", message = "Doctor Name must begin with 'Dr.' followed by capitalized names")
  private String doctorName;
  @Size(min=0,max=20,message=" Specialtity must be within desired limits")
  private String speciality;
  @Min(1)
  @Max(15)
  private int experience;
  @Size(min=0,max=20,message=" Qualification must be within desired limits")
  private String qualification;
  @Size(min=0,max=20,message=" Designation must be within desired limits")
  private String designation;
  @NotNull(message = "Available/Not Available is required")
  private String availability;
  
  public Doctor()
  {super();}



public Doctor(int doctorId,
		@Pattern(regexp = "[A-Z][a-z]+", message = "Doctor Name must begin with Uppercase") String doctorName,
		@Size(min = 0, max = 20, message = " Specialtity must be within desired limits") String speciality,
		@Min(1) @Max(15) int experience,
		@Size(min = 0, max = 20, message = " Qualification must be within desired limits") String qualification,
		@Size(min = 0, max = 20, message = " Designation must be within desired limits") String designation,
		@NotNull(message = "Available/Not Available is required") String availability) {
	super();
	this.doctorId = doctorId;
	this.doctorName = doctorName;
	this.speciality = speciality;
	this.experience = experience;
	this.qualification = qualification;
	this.designation = designation;
	this.availability = availability;
}



public int getDoctorId() {
	return doctorId;
}

public void setDoctorId(int doctorId) {
	this.doctorId = doctorId;
}

public String getDoctorName() {
	return doctorName;
}

public void setDoctorName(String doctorName) {
	this.doctorName = doctorName;
}

public String getSpeciality() {
	return speciality;
}

public void setSpeciality(String speciality) {
	this.speciality = speciality;
}

public int getExperience() {
	return experience;
}

public void setExperience(int experience) {
	this.experience = experience;
}

public String getQualification() {
	return qualification;
}

public void setQualification(String qualification) {
	this.qualification = qualification;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public String getAvailability() {
	return availability;
}

public void setAvailability(String availability) {
	this.availability = availability;
}

@Override
public String toString() {
	return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", speciality=" + speciality
			+ ", experience=" + experience + ", qualification=" + qualification + ", designation=" + designation
			+ ", availability=" + availability + "]";
}



}

