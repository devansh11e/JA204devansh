package com.hexaware.amazecare1.restcontroller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.Patient;

@SpringBootTest
class AppointmentRestControllerTest {

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(AppointmentRestControllerTest.class);

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Perform any setup if required
    }

    @Test
    void testScheduleAppointment() {
        // Set up Patient and Doctor objects (these should already exist in the database)
        Patient patient = new Patient();
        patient.setPatientId(101); // Replace with a valid patientId from your database

        Doctor doctor = new Doctor();
        doctor.setDoctorId(201); // Replace with a valid doctorId from your database

        // Create an appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(LocalDate.of(2024, 12, 1));
        appointment.setTimeSlot("10:00 AM - 11:00 AM");
        appointment.setStatus("Scheduled");
        appointment.setReason("Routine check-up");

        // Schedule the appointment
        ResponseEntity<Appointment> response = restTemplate.postForEntity(
                "http://localhost:8081/api/appointments/scheduleappointment/", appointment, Appointment.class);

        Appointment scheduledAppointment = response.getBody();

        assertNotNull(scheduledAppointment);
        assertEquals("Scheduled", scheduledAppointment.getStatus());
        assertEquals(101, scheduledAppointment.getPatient().getPatientId());
        assertEquals(201, scheduledAppointment.getDoctor().getDoctorId());
    }


    @Test
    void testCancelAppointment() {
        int appointmentId = 301; // Replace with a valid appointmentId from your database

        // Cancel the appointment
        restTemplate.delete("http://localhost:8081/api/appointments/cancelappointment/" + appointmentId);

        // Verify the appointment is canceled (assuming canceled status is updated in the backend)
        ResponseEntity<Appointment> response = restTemplate.getForEntity(
                "http://localhost:8081/api/appointments/get/" + appointmentId, Appointment.class);

        Appointment appointment = response.getBody();

        assertNotNull(appointment);
        assertEquals("Canceled", appointment.getStatus());
    }
}
