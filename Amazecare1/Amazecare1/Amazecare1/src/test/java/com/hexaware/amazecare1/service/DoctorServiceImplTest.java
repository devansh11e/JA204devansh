package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.repositories.DoctorRepository;
@SpringBootTest
class DoctorServiceImplTest {

	 @Mock
	    private DoctorRepository doctorRepo;

    @InjectMocks
    private DoctorServiceImpl serviceImpl;

    Logger logger = LoggerFactory.getLogger(DoctorServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterDoctor() {
        Doctor doctor = new Doctor(101, "Dr. Sharma", "Cardiology", 15, "MD", "Senior Consultant","Available");
        when(doctorRepo.save(doctor)).thenReturn(doctor);

        Doctor registeredDoctor = serviceImpl.registerDoctor(doctor);

        assertNotNull(registeredDoctor);
        assertEquals("Dr. Sharma", registeredDoctor.getDoctorName());
        verify(doctorRepo, times(1)).save(doctor);
        logger.info("Doctor registered successfully: {}", registeredDoctor);
    }

    @Test
    void testGetDoctorById() throws DoctorNotFoundException {
        int doctorId = 101;
        Doctor doctor = new Doctor(doctorId, "Dr. Sharma", "Neurology", 10, "MBBS", "Consultant","Available");
        when(doctorRepo.findById(doctorId)).thenReturn(Optional.of(doctor));

        Doctor fetchedDoctor = serviceImpl.getDoctorById(doctorId);

        assertNotNull(fetchedDoctor);
        assertEquals(doctorId, fetchedDoctor.getDoctorId());
        verify(doctorRepo, times(1)).findById(doctorId);
        logger.info("Doctor fetched successfully: {}", fetchedDoctor);
    }
}
