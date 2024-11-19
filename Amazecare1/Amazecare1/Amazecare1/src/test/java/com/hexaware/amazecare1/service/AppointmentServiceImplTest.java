package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.Patient;
@SpringBootTest
class AppointmentServiceImplTest {

	 @Autowired
	    IAppointmentService service;

	    Logger logger = LoggerFactory.getLogger(AppointmentServiceImplTest.class);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	 @Test
	    void testScheduleAppointment() {
	        Patient patient = new Patient(102, "Vinayak Soni", "04/07/2001", "Male", 7748049010.0, "Anxiety", "General", "07/11/2024");
	        Doctor doctor = new Doctor(1002, "Dr. Sharma", "Neurology", 10, "MBBS", "Consultant");
	        Appointment appointment = new Appointment(1, patient, doctor, new Date(System.currentTimeMillis() + 86400000), "10:00 AM", "Scheduled", "Regular Checkup");

	        Appointment scheduledAppointment = service.scheduleAppointment(1, appointment);
	        assertNotNull(scheduledAppointment);
	        assertEquals("Scheduled", scheduledAppointment.getStatus());
	        logger.info("Appointment scheduled successfully: {}", scheduledAppointment);
	    }

	    @Test
	    void testFindByAppointmentId() {
	        int appointmentId = 101;
	        Appointment appointment = service.findByAppointmentId(appointmentId).get(0);
	        assertNotNull(appointment);
	        assertEquals(appointmentId, appointment.getAppointmentId());
	        logger.info("Appointment fetched successfully: {}", appointment);
	    }

	    @Test
	    void testCancelAppointment() {
	        int appointmentId = 101;
	        int result = service.cancelAppointment(appointmentId);
	        assertEquals(1, result); // Assuming 1 indicates cancellation success
	        logger.info("Appointment cancelled successfully. Result: {}", result);
	    }


}
