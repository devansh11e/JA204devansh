package com.hexaware.amazecare1.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.service.IAppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentRestController {
	
    @Autowired
	IAppointmentService service;
    
	Logger logger =	   LoggerFactory.getLogger(AppointmentRestController.class);
	 @PostMapping("/scheduleappointment/{patientId}")
	    public Appointment scheduleAppointment(@PathVariable int patientId,@Valid @RequestBody Appointment appointment) {
	        return service.scheduleAppointment(patientId, appointment);
	    }

	    // Get all appointments for a patient
	    @GetMapping("/getappointment/{appointmentId}") 
	    public List<Appointment> findByAppointmentId(@PathVariable int appointmentId) throws AppointmentNotFoundException {
	        try {
				return service.findByAppointmentId(appointmentId);
			} catch (AppointmentNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	    }

	    // Cancel an appointment
	    @DeleteMapping("/cancelappointment/{appointmentId}")
	    public int cancelAppointment(@PathVariable int appointmentId) throws AppointmentNotFoundException {
	        return service.cancelAppointment(appointmentId);
	    }

	    // View all appointments by date
	    //@GetMapping("/viewappointmentsbydate/{date}")
	    //public List<Appointment> viewAppointmentsByDate(@PathVariable String date) {
	      //  return service.viewAppointmentsByDate(date);
	    //}
	}

