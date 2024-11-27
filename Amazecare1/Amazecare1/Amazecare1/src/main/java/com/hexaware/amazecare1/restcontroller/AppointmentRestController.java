package com.hexaware.amazecare1.restcontroller;
/*
 * Author=Vinayak
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.dto.AppointmentDTO;
import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.service.IAppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentRestController {
	
    @Autowired
	IAppointmentService service;
    
	Logger logger =	   LoggerFactory.getLogger(AppointmentRestController.class);
	
	
	//Scheduling an appointment
	 @PostMapping(value="/scheduleappointment",consumes = "application/json",produces = "application/json")
	 @PreAuthorize("hasAuthority('patient')")
	 public ResponseEntity<?> scheduleAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) throws PatientNotFoundException, DoctorNotFoundException {
	        Appointment appointment = service.scheduleAppointment(appointmentDTO);
	        return ResponseEntity.ok(appointment);
	    }
	 
	 

	    // Get all appointments for a patient
	    @GetMapping("/getappointmentbypatientid/{patientId}") 
	    @PreAuthorize("hasAuthority('patient')")
	    public ResponseEntity<?> findAppointmentByPatientId(@PathVariable Integer patientId) throws PatientNotFoundException {
	        if (patientId == null) {
	            return ResponseEntity.badRequest().body("Patient ID is required.");
	        }

	        List<Appointment> appointment = service.findAppointmentByPatientId(patientId);

	        if (appointment.isEmpty()) {
	            throw new PatientNotFoundException("No Patient found for patient ID: " + patientId);
	        }

	        return ResponseEntity.ok(appointment);
	    }
			
	    
	    
	    //Get all appointments for a Doctor
	    @GetMapping("/getappointmentbydoctorid/{doctorId}") 
	    @PreAuthorize("hasAuthority('doctor')")
	    public ResponseEntity<?> findAppointmentByDoctorId(@PathVariable Integer doctorId) throws DoctorNotFoundException {
	        if (doctorId == null) {
	            return ResponseEntity.badRequest().body("Doctor ID is required.");
	        }

	        List<Appointment> appointment = service.findAppointmentByDoctorId(doctorId);

	        if (appointment.isEmpty()) {
	            throw new DoctorNotFoundException("No Doctor found for Doctor ID: " + doctorId);
	        }

	        return ResponseEntity.ok(appointment);
	    }

	    // Cancel an appointment
	    @DeleteMapping("/cancelappointment/{appointmentId}")
	    @PreAuthorize("hasAuthority('patient')")
	    public int cancelAppointment(@PathVariable int appointmentId) throws AppointmentNotFoundException {
	        return service.cancelAppointment(appointmentId);
	    }
	}

