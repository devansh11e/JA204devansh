package com.hexaware.amazecare1.restcontroller;
import java.util.HashMap;
/*
 * Author=Vinayak
 */
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentRestController {
	
    @Autowired
	IAppointmentService service;
    
	Logger logger =	   LoggerFactory.getLogger(AppointmentRestController.class);
	
	
	//Scheduling an appointment
	 @PostMapping(value="/schedule-appointment",consumes = "application/json",produces = "application/json")
	 @PreAuthorize("hasAuthority('patient')")
	 public ResponseEntity<?> scheduleAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) throws PatientNotFoundException, DoctorNotFoundException {
	        Appointment appointment = service.scheduleAppointment(appointmentDTO);
	        return ResponseEntity.ok(appointment);
	
	    }
	 
	 

	    // Get all appointments for a patient
	    @GetMapping("/get-appointment-by-id/{appointmentId}") 
	    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
	    public ResponseEntity<?> findAppointmentByPatientId(@PathVariable Integer appointmentId) throws AppointmentNotFoundException {
	        if (appointmentId == null) {
	            return ResponseEntity.badRequest().body("Appointment ID is required.");
	        }

	        AppointmentDTO appointment = service.findAppointmentById(appointmentId);

	        return ResponseEntity.ok(appointment);
	    }
			
	   

	    // Cancel an appointment
	    @PutMapping("/cancel-appointment/{appointmentId}")
	    @PreAuthorize("hasAuthority('admin')")
	    public int cancelAppointment(@PathVariable int appointmentId) throws AppointmentNotFoundException {
	        return service.cancelAppointment(appointmentId);
	    }
	    
	    @PutMapping("/update-appointment/{appointmentId}")
	    @PreAuthorize("hasAuthority('patient')")
	    public  ResponseEntity<Map<String, String>> updateAppointment(@PathVariable int appointmentId,@RequestBody AppointmentDTO appointmentDTO) throws PatientNotFoundException, DoctorNotFoundException, AppointmentNotFoundException {
	    	service.updateAppointment(appointmentId,appointmentDTO);
	    	 Map<String, String> response = new HashMap<>();
		        response.put("message", "Appointment Updated Successfully");

		        return ResponseEntity.ok(response);
	    }
	    
	    @GetMapping(value="/get-all-appointments",produces = "application/json")
	    @PreAuthorize("hasAuthority('admin')")
	    public ResponseEntity<List<AppointmentDTO>> viewAppointments() {
	        List<AppointmentDTO> appointments = service.viewAppointments();
	        return ResponseEntity.ok(appointments); // Return the list of AppointmentDTOs
	    }
	    
	    
	    @GetMapping("/byDoctor/{doctorId}")
	    @PreAuthorize("hasAuthority('patient')")
	    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctorId(@PathVariable Integer doctorId) throws DoctorNotFoundException {
	        List<AppointmentDTO> appointments = service.findAppointmentByDoctor_DoctorId(doctorId);
	        if (appointments.isEmpty()) {
	            return ResponseEntity.noContent().build(); // Return 204 No Content if no appointments found
	        }
	        return ResponseEntity.ok(appointments);
	    }
	    
	}

