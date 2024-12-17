package com.hexaware.amazecare1.dto;
/*
 * Author=Vinayak
 */
//Describing Appointment DTO folder
import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class AppointmentDTO {
	private int appointmentId;
	     @NotNull(message = "Doctor ID is required")
	    private int doctorId;
	    @NotNull(message = "Patient ID is required")
	    private int patientId;
	    @NotNull
	    @Future
	    private LocalDate appointmentDate;
	    @NotNull(message = "Time Slot is required")
	    private String timeSlot;
	    @NotNull(message = "Status is required")
	    private String status;
	    @NotNull(message = "Reason is required")
	    private String reason;
		public AppointmentDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public AppointmentDTO(int appointmentId, @NotNull(message = "Doctor ID is required") int doctorId,
				@NotNull(message = "Patient ID is required") int patientId, @NotNull @Future LocalDate appointmentDate,
				@NotNull(message = "Time Slot is required") String timeSlot,
				@NotNull(message = "Status is required") String status,
				@NotNull(message = "Status is required") String reason) {
			super();
			this.appointmentId = appointmentId;
			this.doctorId = doctorId;
			this.patientId = patientId;
			this.appointmentDate = appointmentDate;
			this.timeSlot = timeSlot;
			this.status = status;
			this.reason = reason;
		}

		public int getAppointmentId() {
			return appointmentId;
		}
		public void setAppointmentId(int appointmentId) {
			this.appointmentId = appointmentId;
		}
		public int getDoctorId() {
			return doctorId;
		}
		public void setDoctorId(int doctorId) {
			this.doctorId = doctorId;
		}
		public int getPatientId() {
			return patientId;
		}
		public void setPatientId(int patientId) {
			this.patientId = patientId;
		}
		public LocalDate getAppointmentDate() {
			return appointmentDate;
		}
		public void setAppointmentDate(LocalDate appointmentDate) {
			this.appointmentDate = appointmentDate;
		}
		public String getTimeSlot() {
			return timeSlot;
		}
		public void setTimeSlot(String timeSlot) {
			this.timeSlot = timeSlot;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		@Override
		public String toString() {
			return "AppointmentDTO [appointmentId=" + appointmentId + ", doctorId=" + doctorId + ", patientId="
					+ patientId + ", appointmentDate=" + appointmentDate + ", timeSlot=" + timeSlot + ", status="
					+ status + ", reason=" + reason + "]";
		}
		
}
