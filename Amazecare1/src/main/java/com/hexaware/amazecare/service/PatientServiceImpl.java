package com.hexaware.amazecare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.PatientRepository;

@Service
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    // Patient Registration
    @Override
    public int registerPatient(Patient patient) {
        Patient savedPatient = patientRepo.save(patient);
        return savedPatient != null ? 1 : 0; // Return 1 if patient is saved successfully, else 0
    }

    // Update Patient Information
    @Override
    public int updatePatientInfo(int patientId) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        if (patient != null) {
            patientRepo.save(patient); // Save updated patient info
            return 1;
        }
        return 0; // Return 0 if patient not found
    }

    // Schedule an Appointment
    @Override
    public Appointment scheduleAppointment(int patientId, Appointment appointment) {
        // Directly set patientId to the appointment instead of fetching Patient object
        appointment.setPatientId(patientId); // Set patientId (int) directly
        return appointmentRepo.save(appointment); // Save the appointment
    }

    // Get Appointments by Patient ID
    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        // Directly return the list of appointments for the given patientId
        return appointmentRepo.findByPatientId(patientId); // Fetch appointments by patientId directly
    }


    // Cancel an Appointment
    @Override
    public int cancelAppointment(int appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment != null) {
            appointmentRepo.delete(appointment); // Delete the appointment
            return 1; // Return 1 if the appointment is canceled successfully
        }
        return 0; // Return 0 if appointment is not found
    }

    // View Appointments by Date
    @Override
    public List<Appointment> viewAppointmentsByDate(String date) {
        return appointmentRepo.findByDate(date); // Fetch appointments based on the date
    }
}
