package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.Patient;

class AppointmentServiceImplTest {

    @Mock
    IAppointmentService mockService; // Mocking the service

    @InjectMocks
    AppointmentServiceImpl serviceImpl; // Service under test

    Logger logger = LoggerFactory.getLogger(AppointmentServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testScheduleAppointment() {
        Patient patient = new Patient(102, "Vinayak Soni", "04/07/2001", "Male", 7748049010.0, "Anxiety", "General", "07/11/2024");
        Doctor doctor = new Doctor(1002, "Dr. Sharma", "Neurology", 10, "MBBS", "Consultant");
        Appointment appointment = new Appointment(1, patient, doctor, new Date(System.currentTimeMillis() + 86400000), "10:00 AM", "Scheduled", "Regular Checkup");

        when(mockService.scheduleAppointment(1, appointment)).thenReturn(appointment);

        Appointment scheduledAppointment = serviceImpl.scheduleAppointment(1, appointment);

        assertNotNull(scheduledAppointment);
        assertEquals("Scheduled", scheduledAppointment.getStatus());
        verify(mockService, times(1)).scheduleAppointment(1, appointment);
        logger.info("Appointment scheduled successfully: {}", scheduledAppointment);
    }

    @Test
    void testCancelAppointment() {
        int appointmentId = 101;
        when(mockService.cancelAppointment(appointmentId)).thenReturn(1);

        int result = serviceImpl.cancelAppointment(appointmentId);

        assertEquals(1, result);
        verify(mockService, times(1)).cancelAppointment(appointmentId);
        logger.info("Appointment cancelled successfully. Result: {}", result);
    }
}
