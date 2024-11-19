package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.MedicalRecord;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.MedicalRecordNotFoundException;
@SpringBootTest
class MedicalRecordServiceImplTest {
	 @Autowired
	    IMedicalRecordService service;

	    Logger logger = LoggerFactory.getLogger(MedicalRecordServiceImplTest.class);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	 @Test
		void testConductConsultation() throws AppointmentNotFoundException {
			 Appointment appointment = new Appointment(101, null, null,  new Date(System.currentTimeMillis() + 86400000),"3 PM", "Routine Checkup","Hypertension");
			    MedicalRecord medicalRecord = new MedicalRecord(4, appointment, "Anxiety", "Amoxicillin", "Patient is stable");

			    MedicalRecord new1 = service.conductConsultation(101, medicalRecord);

			    // Assertions
			    assertNotNull(new1);
			    assertEquals("Diabetes", new1.getDiagnosis());

			    logger.info("Medical record created successfully: {}", new1);
		}

		@Test
		void testUpdateMedicalRecord() {
			 Appointment appointment = new Appointment(101, null, null, new Date(System.currentTimeMillis() + 86400000),"3 PM", "Routine Checkup","Hypertension");
			    MedicalRecord medicalRecord = new MedicalRecord(4, appointment, "Hypertension", "Amoxicillin", "Patient is stable");

			    MedicalRecord updatedRecord = service.updateMedicalRecord(medicalRecord);

			    // Assertions
			    assertNotNull(updatedRecord);
			    assertEquals("Diabetes", updatedRecord.getDiagnosis());

			    logger.info("Medical record updated successfully: {}", updatedRecord);
			}
			

		@Test
		void testViewMedicalHistory() {
		    List<MedicalRecord> medicalHistory = service.viewMedicalHistory();
		    assertNotNull(medicalHistory);
		    logger.info("Medical history retrieved successfully: {}", medicalHistory);
		}
		

		@Test
		void testGetRecordById() throws MedicalRecordNotFoundException {
			 int recordId = 1;
			    MedicalRecord medicalRecord = service.getRecordById(recordId);
			    assertNotNull(medicalRecord);
			    logger.info("Medical record retrieved successfully: {}", medicalRecord);
		}

		@Test
		void testGetByDiagnosis() throws MedicalRecordNotFoundException {
			String diagnosis = "string";
		    List<MedicalRecord> m = service.getByDiagnosis(diagnosis);
		    assertNotNull(m);
		    assertFalse(m.isEmpty());
		    logger.info("Medical History retrieved successfully for diagnosis '{}': {}", diagnosis, m);
		}
}
