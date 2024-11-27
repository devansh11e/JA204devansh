package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.dto.PrescribeMedicationDTO;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.exceptions.PrescriptionNotFoundException;
@SpringBootTest
class PrescribeMedicationServiceImplTest {
	 @Autowired
	    IPrescribeMedicationService service;

	    Logger logger = LoggerFactory.getLogger(PrescribeMedicationServiceImplTest.class);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetPrescriptionById() throws PrescriptionNotFoundException {
		 int prescriptionId = 1;
		    PrescribeMedication prescription = service.getPrescriptionById(prescriptionId);
		    assertNotNull(prescription);
		    logger.info("Prescription retrieved successfully: {}", prescription);
	}

	@Test
	void testPrescribeMedications() throws PatientNotFoundException, DoctorNotFoundException {
		LocalDate prefferedDate = LocalDate.of(2024, 11, 25);
		LocalDate dateofbirth=LocalDate.of(2001, 7, 1);
		 Integer patientId = 1001;
 	    Integer doctorId = 100;

	    PrescribeMedicationDTO prescription = new  PrescribeMedicationDTO(1,patientId,doctorId,"Paracetamol", "500mg","Twice daily","5 days", "Take with water after meals", "2024-11-21", "2024-11-26",10);

	    PrescribeMedication createdPrescription = service.prescribeMedications(prescription);

	    // Assertions
	    assertNotNull(createdPrescription);
	    assertEquals("Paracetamol", createdPrescription.getMedicationName());
        logger.info("Prescription created successfully: {}", createdPrescription);
		
	}

	@Test
	void testViewAllPrescriptions() {
		 
		    List<PrescribeMedication> prescriptions = service.viewAllPrescriptions();
		    assertNotNull(prescriptions);
		    assertFalse(prescriptions.isEmpty());
		    logger.info("All prescriptions retrieved successfully: {}", prescriptions);
		
	}

}
