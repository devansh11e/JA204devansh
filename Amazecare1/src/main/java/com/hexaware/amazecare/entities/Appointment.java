package com.hexaware.amazecare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    private int appointmentId;

    @Column(name = "patientName")
    private String patientName;

    private int patientId; // Store patient ID as an integer instead of a Patient object
    private String date;
    private String timeSlot;
    private String status;
    private String reason;

    public Appointment() {
        super();
    }

    public Appointment(int appointmentId, String patientName, int patientId, String date, String timeSlot, String status, String reason) {
        super();
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.patientId = patientId;
        this.date = date;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "Appointment [appointmentId=" + appointmentId + ", patientName=" + patientName + ", patientId=" + patientId
                + ", date=" + date + ", timeSlot=" + timeSlot + ", status=" + status + ", reason=" + reason + "]";
    }
}
