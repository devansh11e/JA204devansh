package com.hexaware.amazecare1.service;
/*
 * Author=Vinayak
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.dto.AppointmentDTO;
import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.repositories.AppointmentRepository;
import com.hexaware.amazecare1.repositories.DoctorRepository;
import com.hexaware.amazecare1.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService{
	
	
     @Autowired
	private PatientRepository patientRepo;
     @Autowired
     private DoctorRepository doctorRepo;
	 @Autowired
	    private AppointmentRepository appointmentRepo;
	 
	 Logger logger =LoggerFactory.getLogger(AppointmentServiceImpl.class);
	 
	 //Scheduling Appointment
	 @Override
	 public Appointment scheduleAppointment(AppointmentDTO appointmentDTO) throws PatientNotFoundException,DoctorNotFoundException {
		 Doctor doctor = doctorRepo.findById(appointmentDTO.getDoctorId())
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

	        Patient patient = patientRepo.findById(appointmentDTO.getPatientId())
	                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

	        // Map DTO to Appointment entity
	        Appointment appointment = new Appointment();
	        appointment.setDoctor(doctor);
	        appointment.setPatient(patient);
	        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
	        appointment.setTimeSlot(appointmentDTO.getTimeSlot());
	        appointment.setStatus(appointmentDTO.getStatus());
	        appointment.setReason(appointmentDTO.getReason());

	        // Save the appointment
	        return appointmentRepo.save(appointment);
	    }
		

     //Find Appointment by Patient ID
	 @Override
	 public List<Appointment> findAppointmentByPatientId(Integer patientId) throws PatientNotFoundException {
	     List<Appointment> appointments = appointmentRepo.findAppointmentByPatient_PatientId(patientId);
	     try {
	     if (appointments.isEmpty()) {
	         throw new PatientNotFoundException("No patient found with ID: " + patientId);
	     }
	     return appointments;
	     }
	     catch (PatientNotFoundException ex) {
	         // Log the exception message and rethrow the exception
	         System.err.println(ex.getMessage());
	         throw ex;
	     }
	 }

	 
	 //Cancelling the Appointment
	    @Override
	    public int cancelAppointment(int appointmentId) throws AppointmentNotFoundException {
	        // Fetch the appointment by ID
	        Appointment appointment = appointmentRepo.findById(appointmentId)
	                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + appointmentId));

	        // Update the status to "Cancelled"
	        appointment.setStatus("Cancelled");

	        // Save the updated appointment
	        appointmentRepo.save(appointment);

	        return 1; // Indicate success
	    }

	    
	    //Finding Appointment by Doctor ID
		@Override
		public List<Appointment> findAppointmentByDoctorId(Integer doctorId) throws DoctorNotFoundException {
			 List<Appointment> appointments = appointmentRepo.findAppointmentByDoctor_DoctorId(doctorId);
		     try {
		     if (appointments.isEmpty()) {
		         throw new DoctorNotFoundException("No Doctor found with ID: " + doctorId);
		     }
		     return appointments;
		     }
		     catch (DoctorNotFoundException ex) {
		         // Log the exception message and rethrow the exception
		         System.err.println(ex.getMessage());
		         throw ex;
		     }
		}

	    
}
