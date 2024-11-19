package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.entities.Patient;
@SpringBootTest
class PatientServiceImplTest {
	 @Autowired
	    IPatientService service;

	    Logger logger = LoggerFactory.getLogger(PatientServiceImplTest.class);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	 @Test
	    void testRegisterPatient() {
	        Patient patient = new Patient(1, "Vinayak Soni", "04/07/2001", "Male", 7748049010.0, "Anxiety", "General", "07/11/2024");
	        Patient savedPatient = service.registerPatient(patient);
	        assertNotNull(savedPatient);
	        assertEquals("Vinayak Soni", savedPatient.getPatientName());
	        logger.info("Patient registered successfully: {}", savedPatient);
	    }

	    @Test
	    void testUpdatePatientInfo() {
	        // Create a patient object with initial details
	        Patient patient = new Patient(1, "Vinayak Soni", "04/07/2001", "Male", 7748048010.0, "Stress", "Neurology", "07/11/2024");

	        // Call the service method to update patient information
	        Patient updatedPatient = service.updatePatientInfo(patient);

	        // Verify the symptoms are updated correctly
	        assertEquals("Stress", updatedPatient.getSymptoms()); 
	        logger.info("Patient updated successfully: {}", updatedPatient);
	    }



	    @Test
	    void testDeletePatientById() {
	        int patientId = 102;
	        String result = service.deletePatientById(patientId);
	        assertEquals("Patient deleted successfully", result);
	        logger.info("Delete Patient Test Passed: {}", result);
	    }

	    @Test
	    void testGetPatientById() {
	        int patientId = 102;
	        Patient patient = service.getPatientById(patientId);
	        assertNotNull(patient);
	        assertEquals(patientId, patient.getPatientId());
	        logger.info("Patient fetched successfully: {}", patient);
	    }

	    @Test
	    void testGetByPatientName() {
	        String patientName = "Vinayak";
	        List<Patient> patients = service.getByPatientName(patientName); // Corrected return type
	        assertNotNull(patients); // Ensure the returned list is not null
	        assertEquals(patientName, patients.get(0).getPatientName()); // Check the name of the first patient in the list
	        logger.info("Patients fetched successfully by name: {}", patients);
	    }


}
