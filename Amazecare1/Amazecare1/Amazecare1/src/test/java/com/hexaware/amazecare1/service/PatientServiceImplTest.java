package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.entities.Patient;

@SpringBootTest
class PatientServiceImplTest {

    @Mock
    private IPatientService mockService; // Mocked service

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
        Patient patient = new Patient(1, "Mock Vinayak", "04/07/2001", "Male", 7748049010.0, "Anxiety", "General", "07/11/2024");
        when(mockService.registerPatient(patient)).thenReturn(patient);

        // Act
        Patient savedPatient = serviceImpl.registerPatient(patient);

        // Assert
        assertNotNull(savedPatient);
        assertEquals("Mock Vinayak", savedPatient.getPatientName());
        verify(mockService, times(1)).registerPatient(patient); // Verify interaction with the mock
        logger.info("Mock test for registerPatient passed: {}", savedPatient);
    }

    @Test
    void testUpdatePatientInfoWithMock() {
        // Arrange
        Patient patient = new Patient(1, "Mock Vinayak", "04/07/2001", "Male", 7748049010.0, "Stress", "Neurology", "07/11/2024");
        when(mockService.updatePatientInfo(patient)).thenReturn(patient);

        // Act
        Patient updatedPatient = serviceImpl.updatePatientInfo(patient);

        // Assert
        assertNotNull(updatedPatient);
        assertEquals("Stress", updatedPatient.getSymptoms());
        verify(mockService, times(1)).updatePatientInfo(patient); // Verify interaction with the mock
        logger.info("Mock test for updatePatientInfo passed: {}", updatedPatient);
    }

    @Test
    void testDeletePatientByIdWithMock() {
        // Arrange
        int patientId = 102;
        when(mockService.deletePatientById(patientId)).thenReturn("Patient deleted successfully");

        // Act
        String result = serviceImpl.deletePatientById(patientId);

        // Assert
        assertEquals("Patient deleted successfully", result);
        verify(mockService, times(1)).deletePatientById(patientId); // Verify interaction with the mock
        logger.info("Mock test for deletePatientById passed: {}", result);
    }

    @Test
    void testGetPatientByIdWithMock() {
        // Arrange
        int patientId = 102;
        Patient mockPatient = new Patient(patientId, "Mock Vinayak", "04/07/2001", "Male", 7748049010.0, "Fever", "General", "07/11/2024");
        when(mockService.getPatientById(patientId)).thenReturn(mockPatient);

        // Act
        Patient patient = serviceImpl.getPatientById(patientId);

        // Assert
        assertNotNull(patient);
        assertEquals(patientId, patient.getPatientId());
        verify(mockService, times(1)).getPatientById(patientId); // Verify interaction with the mock
        logger.info("Mock test for getPatientById passed: {}", patient);
    }

    @Test
    void testGetByPatientNameWithMock() {
        // Arrange
        String patientName = "Mock Vinayak";
        Patient patient = new Patient(1, patientName, "04/07/2001", "Male", 7748049010.0, "Anxiety", "General", "07/11/2024");
        when(mockService.getByPatientName(patientName)).thenReturn(Arrays.asList(patient));

        // Act
        List<Patient> patients = serviceImpl.getByPatientName(patientName);

        // Assert
        assertNotNull(patients);
        assertEquals(1, patients.size());
        assertEquals(patientName, patients.get(0).getPatientName());
        verify(mockService, times(1)).getByPatientName(patientName); // Verify interaction with the mock
        logger.info("Mock test for getByPatientName passed: {}", patients);
    }
}
