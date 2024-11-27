package com.hexaware.amazecare1.restcontroller;

import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.entities.Doctor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrescribeMedicationRestControllerTest {

    private static RestTemplate restTemplate;
    private static final String baseUrl = "http://localhost:8081/api/prescriptions"; // Adjust as needed

    @BeforeAll
    static void setUpBeforeClass() {
        restTemplate = new RestTemplate(); // Initialize RestTemplate
    }

    @Test
    void testPrescribeMedications() {
        // Create a Patient object using constructor
        Patient patient = new Patient(0, "John", LocalDate.of(1990, 5, 15), "Male", "9876543210", "Headache", "Routine Checkup", LocalDate.of(2024, 12, 1));
        
        // Create a Doctor object using constructor
        Doctor doctor = new Doctor(0, "Dr. Smith", "General Physician", 5, "MBBS", "Consultant", "9 AM - 5 PM");
        
        // Create PrescribeMedication object using constructor
        PrescribeMedication prescription = new PrescribeMedication(0, patient, doctor, "Aspirin", "500mg","Twice a day","5 days", "Take after meals", "2024-11-26", "2024-11-30", 30 );

        // Make a POST request to prescribe the medication
        ResponseEntity<PrescribeMedication> response = 
            restTemplate.postForEntity(baseUrl, prescription, PrescribeMedication.class);

        // Assert the response status code is 200 (OK)
        assertEquals(200, response.getStatusCode(), "Expected HTTP status code 200");

        // Assert the prescription is created correctly
        PrescribeMedication createdPrescription = response.getBody();
        assertNotNull(createdPrescription);
        assertEquals("Aspirin", createdPrescription.getMedicationName());
        assertEquals("500mg", createdPrescription.getDosage());
        assertEquals("Twice a day", createdPrescription.getFrequency());
    }

    @Test
    void testGetPrescriptionById() {
        // Assuming you have a prescription with ID 1 for this test
        int prescriptionId = 1;
        
        ResponseEntity<PrescribeMedication> response = 
            restTemplate.getForEntity(baseUrl + "/" + prescriptionId, PrescribeMedication.class);

        // Assert the response status code is 200 (OK)
        assertEquals(200, response.getStatusCode(), "Expected HTTP status code 200");

        // Assert that the prescription details match
        PrescribeMedication prescription = response.getBody();
        assertNotNull(prescription);
        assertEquals(prescriptionId, prescription.getPrescriptionId());
    }

    @Test
    void testViewAllPrescriptions() {
        // Fetch all prescriptions
        ResponseEntity<PrescribeMedication[]> response = 
            restTemplate.getForEntity(baseUrl, PrescribeMedication[].class);

        // Assert the response status code is 200 (OK)
        assertEquals(200, response.getStatusCode(), "Expected HTTP status code 200");

        // Assert that the prescriptions array is not empty
        PrescribeMedication[] prescriptions = response.getBody();
        assertTrue(prescriptions.length > 0, "Expected prescriptions list to be non-empty");
    }
}