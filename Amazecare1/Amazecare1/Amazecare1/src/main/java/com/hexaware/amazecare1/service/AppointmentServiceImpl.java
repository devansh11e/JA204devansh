package com.hexaware.amazecare1.service;
/*
 * Author=Vinayak
 */
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	 @Autowired
	    private RestTemplate restTemplate;
	 
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
	 public AppointmentDTO findAppointmentById(Integer appointmentId) throws AppointmentNotFoundException {
		        Appointment appointment = appointmentRepo.findById(appointmentId)
		                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + appointmentId));

		        return convertToDTO(appointment);
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

	   


		@Override
		public List<AppointmentDTO> viewAppointments() {
		    List<Appointment> appointments = appointmentRepo.findAll();
		    return appointments.stream()
		            .map(this::convertToDTO)
		            .collect(Collectors.toList());
		}

		// Helper method to map Appointment to AppointmentDTO
		private AppointmentDTO convertToDTO(Appointment appointment) {
		    AppointmentDTO dto = new AppointmentDTO();
		    dto.setAppointmentId(appointment.getAppointmentId());
		    dto.setPatientId(appointment.getPatient().getPatientId());
		    dto.setDoctorId(appointment.getDoctor().getDoctorId());
		    dto.setAppointmentDate(appointment.getAppointmentDate());
		    dto.setTimeSlot(appointment.getTimeSlot());
		    dto.setStatus(appointment.getStatus());
		    dto.setReason(appointment.getReason());
		    return dto;
		}



		@Override
		public String updateAppointment(int appointmentId,AppointmentDTO appointmentDTO) throws PatientNotFoundException, DoctorNotFoundException, AppointmentNotFoundException {
		    // Fetch the existing appointment by ID
		    Appointment appointment = appointmentRepo.findById(appointmentDTO.getAppointmentId())
		            .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));

		    // Check if the appointment has been cancelled
		    if ("Cancelled".equalsIgnoreCase(appointment.getStatus())) {
		        throw new AppointmentNotFoundException("Appointment has been cancelled, cannot update");
		    }

		    // Fetch the patient and doctor by their IDs to ensure they exist
		    Patient patient = patientRepo.findById(appointmentDTO.getPatientId())
		            .orElseThrow(() -> new PatientNotFoundException("Patient not found"));
		    Doctor doctor = doctorRepo.findById(appointmentDTO.getDoctorId())
		            .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

		    // Set the Doctor and Patient objects in the Appointment entity
		    appointment.setDoctor(doctor);  // Set the Doctor object
		    appointment.setPatient(patient);  // Set the Patient object
		    appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
		    appointment.setTimeSlot(appointmentDTO.getTimeSlot());
		    appointment.setStatus(appointmentDTO.getStatus());
		    appointment.setReason(appointmentDTO.getReason());

		    // Save the updated appointment
		     appointmentRepo.save(appointment);
		     return "Appointment updated successfully";
		}


		@Override
		public List<AppointmentDTO> findAppointmentByDoctor_DoctorId(Integer doctorId) throws DoctorNotFoundException {
		   List<Appointment> app= appointmentRepo.findAppointmentByDoctor_DoctorId(doctorId);
		   return app.stream()
		            .map(this::convertToDTO)
		            .collect(Collectors.toList());
		   
		}

		}
  
		
	    

