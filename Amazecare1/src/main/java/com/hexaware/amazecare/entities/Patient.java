package com.hexaware.amazecare.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



// author vinayak
@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    private int patientId;

    @Column(name = "patientName")
    private String patientName;

    private String dob;
    private String gender;
    private double contact;
    private String symptoms;
    private String natureOfVisit;
    private String preferredDate;

   
    //for now doctor foreign jey relationship removed//
    
    public Patient() {
        super();
    }

    public Patient(int patientId, String patientName, String dob, String gender, double contact, String symptoms, String natureOfVisit, String preferredDate) {
        super();
        this.patientId = patientId;
        this.patientName = patientName;
        this.dob = dob;
        this.gender = gender;
        this.contact = contact;
        this.symptoms = symptoms;
        this.natureOfVisit = natureOfVisit;
        this.preferredDate = preferredDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getContact() {
        return contact;
    }

    public void setContact(double contact) {
        this.contact = contact;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getNatureOfVisit() {
        return natureOfVisit;
    }

    public void setNatureOfVisit(String natureOfVisit) {
        this.natureOfVisit = natureOfVisit;
    }

    public String getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(String preferredDate) {
        this.preferredDate = preferredDate;
    }

   //getter setter for doctor object currently removed //

    @Override
    public String toString() {
        return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", dob=" + dob + ", gender=" + gender 
                + ", contact=" + contact + ", symptoms=" + symptoms + ", natureOfVisit=" + natureOfVisit 
                + ", preferredDate=" + preferredDate + "]";
    }
}
