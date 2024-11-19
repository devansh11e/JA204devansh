package com.hexaware.amazecare1.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hexaware.amazecare1.entities.Patient;
@SpringBootTest
class PatientRestControllerTest {

	@Autowired
	RestTemplate restTemplate;
	
	 Logger logger =LoggerFactory.getLogger(PatientRestControllerTest.class);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Disabled
	void testInsertPatient() {
		
		Patient emp = new Patient(1,"sohini","04/9/2005","female",7748049015.0,"pcod","general","07/11/2024");
		
	ResponseEntity<Patient>  response =  
			restTemplate.postForEntity("http://localhost:8081/api/patients/register", emp, Patient.class);

						Patient e = response.getBody();
						
							assertEquals(123, e.getPatientId());
							
					
			}	

	@Test
	void testUpdateEmployee() {
	    int patientId = 101;
	    
	    // Create an updated Patient object with modified details
	    Patient updatedPatient = new Patient(patientId, "Sohini Updated", "04/9/2005", "female", 7748049015.0, "updated diagnosis", "general", "07/11/2024");
	    
	    // Send PUT request to update the patient details
	    restTemplate.put("http://localhost:8081/api/patients/update/" + patientId, updatedPatient);
	    
	    // Retrieve the updated patient details
	    ResponseEntity<Patient> response = restTemplate.getForEntity("http://localhost:8081/api/patients/get/" + patientId, Patient.class);
	    Patient retrievedPatient = response.getBody();
	    
	    // Assert that the updated details match the expected values
	    assertNotNull(retrievedPatient);
	    assertEquals("Sohini Updated", retrievedPatient.getPatientName());
	   
	}

	@Test
	
	void testGetEmployeeById() {

		int patientId = 101;

		ResponseEntity<Patient> response = restTemplate.getForEntity("http://localhost:8081/api/patients/get/" + patientId,
				Patient.class);

		Patient emp = response.getBody();

		assertNotNull(emp);
		assertEquals(101, emp.getPatientId());

	}

	@Test
	//@Disabled
	void testGetAll() {

		ResponseEntity<List> response = 
				restTemplate.getForEntity("http://localhost:8081/api/patients/getall",List.class);

		List<Patient> list = response.getBody();
		
	

		assertTrue(list.size() > 0);
		
		
		
		List<Patient> list2 =	restTemplate.getForObject("http://localhost:8081/api/patients/getall", List.class);
		
		assertNotNull(list2);

	}

	@Test
	void testDeleteById() {
		
						int patientId = 124;
		
						restTemplate.delete("http://localhost:8081/api/patients/delete/"+patientId);
		

	}

}
