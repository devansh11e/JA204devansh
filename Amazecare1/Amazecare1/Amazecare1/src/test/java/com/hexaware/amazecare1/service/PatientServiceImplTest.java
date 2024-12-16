package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.repositories.PatientRepository;

@SpringBootTest
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepo; // Mocked service

    @InjectMocks
    private PatientServiceImpl serviceImpl; // Service under test

    Logger logger = LoggerFactory.getLogger(PatientServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize Mockito mocks
    }

    @Test
    void testRegisterPatientWithMock() {
        // Arrange
    	LocalDate prefferedDate = LocalDate.of(2024, 11, 25);
    	LocalDate dateofbirth=LocalDate.of(2001, 7, 1);
        Patient patient = new Patient(1, "Mock Vinayak", dateofbirth, "Male", "7748049010");
        when(patientRepo.save(patient)).thenReturn(patient);

        // Act
        Patient savedPatient = serviceImpl.registerPatient(patient);

        // Assert
        assertNotNull(savedPatient);
        assertEquals("Mock Vinayak", savedPatient.getPatientName());
        verify(patientRepo, times(1)).save(patient); // Verify interaction with the mock
        logger.info("Mock test for registerPatient passed: {}", savedPatient);
    }

    @Test
    void testGetPatientByIdWithMock() {
    	LocalDate prefferedDate = LocalDate.of(2024, 11, 25);
    	LocalDate dateofbirth=LocalDate.of(2001, 7, 1);
        // Arrange
        int patientId = 1;
        Patient mockPatient = new Patient(patientId, "Mock Vinayak", dateofbirth, "Male", "7748049010");
        when(patientRepo.findById(patientId)).thenReturn(Optional.of(mockPatient));

        // Act
        Patient patient = serviceImpl.getPatientById(patientId);

        // Assert
        assertNotNull(patient);
        assertEquals(patientId, patient.getPatientId());
        verify(patientRepo, times(1)).findById(patientId); // Verify interaction with the mock
        logger.info("Mock test for getPatientById passed: {}", patient);
    }

    @Test
    void testGetByPatientNameWithMock() {
    	LocalDate dateofbirth=LocalDate.of(2001, 7, 1);
        // Arrange
        String patientName = "Mock Vinayak";
        Patient patient = new Patient(1, patientName, dateofbirth, "Male", "7748049010");
        when(patientRepo.findByPatientName(patientName)).thenReturn(Arrays.asList(patient));

        // Act
        List<Patient> patients = serviceImpl.getByPatientName(patientName);

        // Assert
        assertNotNull(patients);
        assertEquals(1, patients.size());
        assertEquals(patientName, patients.get(0).getPatientName());
        verify(patientRepo, times(1)).findByPatientName(patientName); // Verify interaction with the mock
        logger.info("Mock test for getByPatientName passed: {}", patients);
    }
}
