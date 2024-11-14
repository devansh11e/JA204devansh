package com.hexaware.amazecare.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.entities.Appointment;
import com.hexaware.amazecare.entities.Patient;
import com.hexaware.amazecare.service.IPatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    @Autowired
    IPatientService service;

    // Register a new patient
    @PostMapping("/register")
    public int registerPatient(@RequestBody Patient patient) {
        return service.registerPatient(patient);
    }

    // Update patient information
    @PutMapping("/update/{patientId}")
    public int updatePatientInfo(@PathVariable int patientId) {
        return service.updatePatientInfo(patientId);
    }

    // Schedule an appointment for a patient
    @PostMapping("/schedule/{patientId}")
    public Appointment scheduleAppointment(@PathVariable int patientId, @RequestBody Appointment appointment) {
        return service.scheduleAppointment(patientId, appointment);
    }

    // Get all appointments for a patient
    @GetMapping("/appointments/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable int patientId) {
        return service.getAppointmentsByPatientId(patientId);
    }

    // Cancel an appointment
    @DeleteMapping("/cancel/{appointmentId}")
    public int cancelAppointment(@PathVariable int appointmentId) {
        return service.cancelAppointment(appointmentId);
    }

    // View all appointments by date
    @GetMapping("/appointments/date/{date}")
    public List<Appointment> viewAppointmentsByDate(@PathVariable String date) {
        return service.viewAppointmentsByDate(date);
    }
}
