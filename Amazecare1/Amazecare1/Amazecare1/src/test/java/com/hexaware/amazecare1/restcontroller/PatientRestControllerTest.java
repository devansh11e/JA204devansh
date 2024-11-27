package com.hexaware.amazecare1.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hexaware.amazecare1.entities.Patient;
@SpringBootTest
class PatientRestControllerTest {

	@Mock
    private RestTemplate restTemplate; // Mock RestTemplate

    @InjectMocks
    private PatientRestController patientRestController;
	 Logger logger =LoggerFactory.getLogger(PatientRestControllerTest.class);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testInsertPatient() {
		LocalDate prefferedDate = LocalDate.of(2024, 11, 25);
		LocalDate dateofbirth=LocalDate.of(2001, 7, 1);
		Patient emp = new Patient(1,"sohini",dateofbirth,"female","7748049015","pcod","general",prefferedDate);
		
	ResponseEntity<Patient>  response =  
			restTemplate.postForEntity("http://localhost:8081/api/patients/registerpatient", emp, Patient.class);

						Patient e = response.getBody();
						System.out.println("Status code: " + response.getStatusCode());
						
							assertEquals(123, e.getPatientId());
							
					
			}	

	@Test
	void testUpdateEmployee() {
	    int patientId = 1;
	    LocalDate prefferedDate = LocalDate.of(2024, 11, 25);
	    LocalDate dateofbirth=LocalDate.of(2001, 7, 1);
	    // Create an updated Patient object with modified details
	    Patient updatedPatient = new Patient(patientId, "Sohini Updated", dateofbirth, "female", "7748049015", "updated diagnosis", "general",prefferedDate);
	    
	    // Send PUT request to update the patient details
	    restTemplate.put("http://localhost:8081/api/patients/updatepatient/" + patientId, updatedPatient);
	    
	    // Retrieve the updated patient details
	    ResponseEntity<Patient> response = restTemplate.getForEntity("http://localhost:8081/api/patients/getPatientbyid/" + patientId, Patient.class);
	    Patient retrievedPatient = response.getBody();
	    
	    // Assert that the updated details match the expected values
	    assertNotNull(retrievedPatient);
	    assertEquals("Sohini Updated", retrievedPatient.getPatientName());
	   
	}

	@Test
	
	void testGetEmployeeById() {

		int patientId = 1;

		ResponseEntity<Patient> response = restTemplate.getForEntity("http://localhost:8081/api/patients/getPatientbyid/" + patientId,
				Patient.class);

		Patient emp = response.getBody();

		assertNotNull(emp);
		assertEquals(1, emp.getPatientId());

	}


	@Test
	void testDeleteById() {
		
						int patientId = 1;
		
						restTemplate.delete("http://localhost:8081/api/patients/deletePatientbyid/"+patientId);
		

	}

}
