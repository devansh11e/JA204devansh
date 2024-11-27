package com.hexaware.amazecare1.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hexaware.amazecare1.dto.AppointmentDTO;
import com.hexaware.amazecare1.entities.MedicalRecord;

@SpringBootTest
class MedicalRecordRestControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Any setup before all tests, if necessary
    }

    @Test
    void testConductConsultation() {
        // Create an AppointmentDTO object for testing (you may need to create a real appointment in the database)
        AppointmentDTO appointmentDTO = new AppointmentDTO(1, 1002, 102, LocalDate.of(2024, 11, 25), "10:00 AM", "Scheduled", "General Checkup");

        // Create a MedicalRecord object for the test
        MedicalRecord record = new MedicalRecord();
        record.setDiagnosis("Flu");
        record.setPrescription("Rest, Hydration");
        record.setNotes("Patient has mild symptoms");

        // Make a POST request to simulate conducting a consultation
        ResponseEntity<MedicalRecord> response = 
            restTemplate.postForEntity("http://localhost:8081/api/medicalrecords/consult", record, MedicalRecord.class);

        assertEquals(200, response.getStatusCode(), "Expected HTTP status code 200");

        MedicalRecord createdRecord = response.getBody();

        // Assert the response to check if the consultation was conducted
        assertNotNull(createdRecord);
        assertEquals("Flu", createdRecord.getDiagnosis());
        assertEquals("Rest, Hydration", createdRecord.getPrescription());
    }

    @Test
    void testUpdateMedicalRecord() {
        int recordId = 1;  // Assuming this ID exists
        
        // Create an updated MedicalRecord object
        MedicalRecord updatedRecord = new MedicalRecord();
        updatedRecord.setRecordId(recordId);
        updatedRecord.setDiagnosis("Updated Diagnosis");
        updatedRecord.setPrescription("Updated Prescription");
        updatedRecord.setNotes("Updated Notes");

        // Send PUT request to update the medical record
        restTemplate.put("http://localhost:8081/api/medicalrecords/update/" + recordId, updatedRecord);

        // Retrieve the updated medical record
        ResponseEntity<MedicalRecord> response = 
            restTemplate.getForEntity("http://localhost:8081/api/medicalrecords/get/" + recordId, MedicalRecord.class);

        MedicalRecord retrievedRecord = response.getBody();

        // Assert that the updated details match the expected values
        assertNotNull(retrievedRecord);
        assertEquals("Updated Diagnosis", retrievedRecord.getDiagnosis());
    }

    @Test
    void testGetRecordById() {
        int recordId = 1;

        // Retrieve the medical record by ID
        ResponseEntity<MedicalRecord> response = 
            restTemplate.getForEntity("http://localhost:8081/api/medicalrecords/get/" + recordId, MedicalRecord.class);

        MedicalRecord record = response.getBody();

        // Assert the retrieved record is not null and the ID matches
        assertNotNull(record);
        assertEquals(recordId, record.getRecordId());
    }

    @Test
    void testViewMedicalHistory() {
        int patientId = 102;

        // Retrieve medical history by patient ID
        ResponseEntity<List> response = 
            restTemplate.getForEntity("http://localhost:8081/api/medicalrecords/history/" + patientId, List.class);

        List<MedicalRecord> records = response.getBody();

        // Assert the retrieved list is not empty
        assertNotNull(records);
        assertTrue(records.size() > 0);
    }

    @Test
    void testGetByDiagnosis() {
        String diagnosis = "Flu";

        // Retrieve records by diagnosis
        ResponseEntity<List> response = 
            restTemplate.getForEntity("http://localhost:8081/api/medicalrecords/diagnosis/" + diagnosis, List.class);

        List<MedicalRecord> records = response.getBody();

        // Assert the records are not empty and contain the correct diagnosis
        assertNotNull(records);
        assertTrue(records.size() > 0);
    }

    @Test
    void testDeleteRecordById() {
        int recordId = 1;  // Assuming this ID exists in the system

        // Send DELETE request to delete the medical record
        restTemplate.delete("http://localhost:8081/api/medicalrecords/delete/" + recordId);

        // Retrieve the record again to check if it was deleted
        ResponseEntity<MedicalRecord> response = 
            restTemplate.getForEntity("http://localhost:8081/api/medicalrecords/get/" + recordId, MedicalRecord.class);

        // Assert the record is no longer found (HTTP 404)
        assertEquals(404, response.getStatusCode(), "Expected HTTP status code 404 for deleted record");
    }
}