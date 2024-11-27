package com.hexaware.amazecare1.entities;
/*
 * Author=Devansh
 */
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="MedicalRecord")
public class MedicalRecord {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int recordId;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointmentId")
   private Appointment appointment;
	@Size(min=0,max=20,message=" Diagnosis name must be within desired limits")
   private String diagnosis;
	@NotNull(message = "Prescription is required")
   private String prescription;
	@NotNull(message = "Notes is required")
   private String notes;
   public MedicalRecord()
   { super();}

public MedicalRecord(int recordId, Appointment appointment,
		@Size(min = 0, max = 20, message = " Diagnosis name must be within desired limits") String diagnosis,
		@NotNull(message = "Prescription is required") String prescription,
		@NotNull(message = "Notes is required") String notes) {
	super();
	this.recordId = recordId;
	this.appointment = appointment;
	this.diagnosis = diagnosis;
	this.prescription = prescription;
	this.notes = notes;
}

public int getRecordId() {
	return recordId;
}
public void setRecordId(int recordId) {
	this.recordId = recordId;
}
public Appointment getAppointment() {
	return appointment;
}
public void setAppointment(Appointment appointment) {
	this.appointment = appointment;
}
public String getDiagnosis() {
	return diagnosis;
}
public void setDiagnosis(String diagnosis) {
	this.diagnosis = diagnosis;
}
public String getPrescription() {
	return prescription;
}
public void setPrescription(String prescription) {
	this.prescription = prescription;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}
@Override
public String toString() {
	return "MedicalRecord [recordId=" + recordId + ", diagnosis=" + diagnosis + ", prescription=" + prescription
			+ ", notes=" + notes + "]";
}
   
}

