package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;

class DoctorServiceImplTest {

    @Mock
    IDoctorService mockService;

    @InjectMocks
    DoctorServiceImpl serviceImpl;

    Logger logger = LoggerFactory.getLogger(DoctorServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterDoctor() {
        Doctor doctor = new Doctor(1002, "Dr. Sharma", "Cardiology", 15, "MD", "Senior Consultant");
        when(mockService.registerDoctor(doctor)).thenReturn(doctor);

        Doctor registeredDoctor = serviceImpl.registerDoctor(doctor);

        assertNotNull(registeredDoctor);
        assertEquals("Dr. Sharma", registeredDoctor.getDoctorName());
        verify(mockService, times(1)).registerDoctor(doctor);
        logger.info("Doctor registered successfully: {}", registeredDoctor);
    }

    @Test
    void testGetDoctorById() throws DoctorNotFoundException {
        int doctorId = 1002;
        Doctor doctor = new Doctor(doctorId, "Dr. Sharma", "Neurology", 10, "MBBS", "Consultant");
        when(mockService.getDoctorById(doctorId)).thenReturn(doctor);

        Doctor fetchedDoctor = serviceImpl.getDoctorById(doctorId);

        assertNotNull(fetchedDoctor);
        assertEquals(doctorId, fetchedDoctor.getDoctorId());
        verify(mockService, times(1)).getDoctorById(doctorId);
        logger.info("Doctor fetched successfully: {}", fetchedDoctor);
    }
}
