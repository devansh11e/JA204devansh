package com.hexaware.amazecare1.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="MedicalRecord")
public class MedicalRecord {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int recordId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointmentId")
   private Appointment appointment;
	@Size(min=0,max=20,message=" Diagnosis name must be within desired limits")
   private String diagnosis;
	@Size(min=0,max=20,message=" Prescription name must be within desired limits")
   private String prescription;
	@Size(min=0,max=100,message=" Notes must be within desired limits")
   private String notes;
   public MedicalRecord()
   { super();}
public MedicalRecord(int recordId, Appointment appointment, String diagnosis, String prescription, String notes) {
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

