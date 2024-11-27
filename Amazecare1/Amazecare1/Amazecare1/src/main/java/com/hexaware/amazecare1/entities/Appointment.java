package com.hexaware.amazecare1.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
/*
 * Author =Vinayak
 */
@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id",referencedColumnName = "patient_id")
    private Patient patient; // Reference to the Patient entity

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor; // Reference to the Doctor entity

    
    @Column(nullable = false)
    @Future
    private LocalDate appointmentDate; // Use Date for proper temporal handling

    @NotNull(message = "TimeSlot is required")
    private String timeSlot;

    @NotNull(message = "Status is required")
    private String status;
    
    @NotNull(message = "Reason is required")
    private String reason;

    public Appointment() {
        super();
    }
    
   

    public Appointment(int appointmentId, Patient patient, Doctor doctor, @Future LocalDate appointmentDate,
			@NotNull(message = "TimeSlot is required") String timeSlot,
			@NotNull(message = "Status is required") String status,
			@NotNull(message = "Reason is required") String reason) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.timeSlot = timeSlot;
		this.status = status;
		this.reason = reason;
	}



	// Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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

    public @Future LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(@Future LocalDate appointmentDate) {
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
        return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient 
                + ", doctor=" + doctor + ", appointmentDate=" + appointmentDate 
                + ", timeSlot=" + timeSlot + ", status=" + status + ", reason=" + reason + "]";
    }
}
