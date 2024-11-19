package com.hexaware.amazecare1.entities;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
/*
 * Auhtor =Vinayak
 */
@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id",referencedColumnName = "patient_id", nullable = false)
    private Patient patient; // Reference to the Patient entity

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id", nullable = false)
    private Doctor doctor; // Reference to the Doctor entity

    
    @Column(nullable = false)
    @Future
    private Date appointmentDate; // Use Date for proper temporal handling

    @Column(nullable = false)
    private String timeSlot;

    @Column(nullable = false)
    private String status;

    private String reason;

    public Appointment() {
        super();
    }

    public Appointment(int appointmentId, Patient patient, Doctor doctor, Date appointmentDate, String timeSlot, String status, String reason) {
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
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
        return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient.getPatientName() 
                + ", doctor=" + doctor.getDoctorName() + ", appointmentDate=" + appointmentDate 
                + ", timeSlot=" + timeSlot + ", status=" + status + ", reason=" + reason + "]";
    }
}
