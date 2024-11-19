package com.hexaware.amazecare1.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.repositories.AppointmentRepository;
import com.hexaware.amazecare1.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService{
	
	
     @Autowired
	private PatientRepository patientRepo;
	 @Autowired
	    private AppointmentRepository appointmentRepo;
	 
	 Logger logger =LoggerFactory.getLogger(AppointmentServiceImpl.class);
	 
	 @Override
	    public Appointment scheduleAppointment(int patientId, Appointment appointment) {
	        Patient patient = patientRepo.findById(patientId)
	                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + patientId));
	        appointment.setPatient(patient);
	        return appointmentRepo.save(appointment);
	    }

	    @Override
	    public List<Appointment> findByAppointmentId(int appointmentId) {
	        return appointmentRepo.findByAppointmentId(appointmentId);
	    }

	    @Override
	    public int cancelAppointment(int appointmentId) {
	        // Fetch the appointment by ID
	        Appointment appointment = appointmentRepo.findById(appointmentId)
	                .orElseThrow(() -> new PatientNotFoundException("Appointment not found with id: " + appointmentId));

	        // Update the status to "Cancelled"
	        appointment.setStatus("Cancelled");

	        // Save the updated appointment
	        appointmentRepo.save(appointment);

	        return 1; // Indicate success
	    }

	    
}
