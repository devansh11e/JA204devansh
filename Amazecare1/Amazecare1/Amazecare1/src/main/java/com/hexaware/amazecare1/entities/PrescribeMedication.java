package com.hexaware.amazecare1.entities;
/*
 * Author = Devansh
 */
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="Prescription")
public class PrescribeMedication {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int prescriptionId;
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "patient_id", referencedColumnName = "patient_Id")
   private Patient patient;
  
 
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_Id")
   private Doctor doctor;
  
  @NotNull(message = "Medication Name is required")
   private String medicationName;
  @Size(min=0,max=20,message=" Dosage must be within desired limits")
   private String dosage;
  @Size(min=0,max=20,message=" Frequency name must be within desired limits")
   private String frequency;
  @Size(min=0,max=20,message=" Duration name must be within desired limits")
   private String duration;
  @NotNull(message = "Instruction is required")
   private String instruction;
  @NotNull(message = "StartDate is required")
   private String startDate;
  @NotNull(message = "EndDate is required")
   private String endDate;
  @Min(0)
  @Max(15)
   private int quantity;
   public PrescribeMedication()
   { super();
   
   }

public PrescribeMedication(int prescriptionId, Patient patient, Doctor doctor,
		@NotNull(message = "Medication Name is required") String medicationName,
		@Size(min = 0, max = 20, message = " Dosage must be within desired limits") String dosage,
		@Size(min = 0, max = 20, message = " Frequency name must be within desired limits") String frequency,
		@Size(min = 0, max = 20, message = " Duration name must be within desired limits") String duration,
		@NotNull(message = "Instruction is required") String instruction,
		@NotNull(message = "StartDate is required") String startDate,
		@NotNull(message = "EndDate is required") String endDate, @Min(0) @Max(15) int quantity) {
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
