package com.hexaware.amazecare.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Prescription")
public class PrescribeMedication {
  @Id
  private int prescriptionId;
   private Patient patient;
   private Doctor doctor;
   private String medicationName;
   private String dosage;
   private String frequency;
   private String duration;
   private String instruction;
   private String startDate;
   private String endDate;
   private int quantity;
   public PrescribeMedication()
   { super();
   
   }
public PrescribeMedication(int prescriptionId, Patient patient, Doctor doctor, String medicationName, String dosage,
		String frequency, String duration, String instruction, String startDate, String endDate, int quantity) {
	super();
	this.prescriptionId = prescriptionId;
	this.patient = patient;
	this.doctor = doctor;
	this.medicationName = medicationName;
	this.dosage = dosage;
	this.frequency = frequency;
	this.duration = duration;
	this.instruction = instruction;
	this.startDate = startDate;
	this.endDate = endDate;
	this.quantity = quantity;
}
public int getPrescriptionId() {
	return prescriptionId;
}
public void setPrescriptionId(int prescriptionId) {
	this.prescriptionId = prescriptionId;
}
public Patient getPatient() {
	return patient;
}
public void setPatient(Patient patient) {
	this.patient = patient;
}
public Doctor getDoctor() {
	return doctor;
}
public void setDoctor(Doctor doctor) {
	this.doctor = doctor;
}
public String getMedicationName() {
	return medicationName;
}
public void setMedicationName(String medicationName) {
	this.medicationName = medicationName;
}
public String getDosage() {
	return dosage;
}
public void setDosage(String dosage) {
	this.dosage = dosage;
}
public String getFrequency() {
	return frequency;
}
public void setFrequency(String frequency) {
	this.frequency = frequency;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}
public String getInstruction() {
	return instruction;
}
public void setInstruction(String instruction) {
	this.instruction = instruction;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
@Override
public String toString() {
	return "Prescription [prescriptionId=" + prescriptionId + ", doctor=" + doctor + ", medicationName="
			+ medicationName + ", dosage=" + dosage + ", frequency=" + frequency + ", duration=" + duration
			+ ", instruction=" + instruction + ", startDate=" + startDate + ", endDate=" + endDate + ", quantity="
			+ quantity + "]";
}
   
}
