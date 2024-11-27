package com.hexaware.amazecare1.dto;
/*
 * Author = Devansh
 */
//Describing Prescribe medications
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PrescribeMedicationDTO {
	
	private int prescriptionId;
	@NotNull(message = "Patient ID is required")
    private int patientId;

    @NotNull(message = "Doctor ID is required")
    private int doctorId;

    @Size(max = 20, message = "Medication name must be within 20 characters")
    private String medicationName;

    @Size(max = 20, message = "Dosage must be within 20 characters")
    private String dosage;

    @Size(max = 20, message = "Frequency must be within 20 characters")
    private String frequency;

    @Size(max = 20, message = "Duration must be within 20 characters")
    private String duration;

    @Size(max = 20, message = "Instruction must be within 20 characters")
    private String instruction;

    @NotNull(message = "Start date is required")
    private String startDate;  // Consider using LocalDate if the format is standardized

    @NotNull(message = "End date is required")
    private String endDate;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 15, message = "Quantity cannot exceed 15")
    private int quantity;

	public PrescribeMedicationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrescribeMedicationDTO(int prescriptionId,  @NotNull(message = "Patient ID is required") int patientId,
			 @NotNull(message = "Doctor ID is required") int doctorId,
			@Size(max = 20, message = "Medication name must be within 20 characters") String medicationName,
			@Size(max = 20, message = "Dosage must be within 20 characters") String dosage,
			@Size(max = 20, message = "Frequency must be within 20 characters") String frequency,
			@Size(max = 20, message = "Duration must be within 20 characters") String duration,
			@Size(max = 20, message = "Instruction must be within 20 characters") String instruction,
			@NotNull(message = "Start date is required") String startDate,
			@NotNull(message = "End date is required") String endDate,
			@Min(value = 0, message = "Quantity must be at least 0") @Max(value = 15, message = "Quantity cannot exceed 15") int quantity) {
		super();
		this.prescriptionId = prescriptionId;
		this.patientId = patientId;
		this.doctorId = doctorId;
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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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
		return "PrescribeMedicationDTO [prescriptionId=" + prescriptionId + ", patientId=" + patientId + ", doctorId="
				+ doctorId + ", medicationName=" + medicationName + ", dosage=" + dosage + ", frequency=" + frequency
				+ ", duration=" + duration + ", instruction=" + instruction + ", startDate=" + startDate + ", endDate="
				+ endDate + ", quantity=" + quantity + "]";
	}
    
    
}
