package com.hexaware.amazecare1.dto;
/*
 * Author=Devansh
 */
//Describing MedicalRecord DTO
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedicalRecordDTO {
	private int recordId;
	@NotNull(message = "Appointment ID is required")
	private int appointmentId;  // Only reference the ID, not the whole Appointment object

    @Size(max = 20, message = "Diagnosis name must be within 20 characters")
    private String diagnosis;

    @Size(max = 20, message = "Prescription name must be within 20 characters")
    private String prescription;

    @Size(max = 100, message = "Notes must be within 100 characters")
    private String notes;

	public MedicalRecordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalRecordDTO(int recordId, int appointmentId,
			@Size(max = 20, message = "Diagnosis name must be within 20 characters") String diagnosis,
			@Size(max = 20, message = "Prescription name must be within 20 characters") String prescription,
			@Size(max = 100, message = "Notes must be within 100 characters") String notes) {
		super();
		this.recordId = recordId;
		this.appointmentId = appointmentId;
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

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
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
		return "MedicalRecordDTO [recordId=" + recordId + ", appointmentId=" + appointmentId + ", diagnosis="
				+ diagnosis + ", prescription=" + prescription + ", notes=" + notes + "]";
	}
    
    
}
