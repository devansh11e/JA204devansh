package com.hexaware.amazecare.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="MedicalRecord")
public class MedicalRecord {
	@Id
   private int recordId;
   private Appointment appointment;
   private String diagnosis;
   private String prescription;
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

