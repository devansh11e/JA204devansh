package com.hexaware.amazecare.service;

import java.util.List;
import com.hexaware.amazecare.entities.Appointment;

import com.hexaware.amazecare.entities.Patient;

public interface IPatientService {

    // Patient Registration and Login
   
    // Patient Profile Management
    int registerPatient(Patient patient);
    int updatePatientInfo(int patientId);


    // Appointment Management
    Appointment scheduleAppointment(int patientId, Appointment appointment);
    List<Appointment> getAppointmentsByPatientId(int patientId);
    int cancelAppointment(int appointmentId);
    List<Appointment> viewAppointmentsByDate(String date);  // General view for appointments by date

}
