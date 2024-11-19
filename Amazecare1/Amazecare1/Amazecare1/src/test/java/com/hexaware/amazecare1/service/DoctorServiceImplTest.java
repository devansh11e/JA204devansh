package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
@SpringBootTest
class DoctorServiceImplTest {
	@Autowired
    IDoctorService service;

    Logger logger = LoggerFactory.getLogger(DoctorServiceImplTest.class);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	 @Test
	    void testRegisterDoctor() {
	        Doctor doctor = new Doctor(1002, "Dr. Sharma", "Cardiology", 15, "MD", "Senior Consultant");
	        Doctor registeredDoctor = service.registerDoctor(doctor);
	        assertNotNull(registeredDoctor);
	        assertEquals("Dr. Sharma", registeredDoctor.getDoctorName());
	        logger.info("Doctor registered successfully: {}", registeredDoctor);
	    }

	    @Test
	    void testUpdateDoctor() {
	        Doctor doctor = new Doctor(1002, "Dr. Sharma", "Neurology", 12, "MBBS, MD", "Consultant");
	        Doctor updatedDoctor = service.updateDoctor(doctor);
	        assertEquals("Neurology", updatedDoctor.getSpeciality());
	        logger.info("Doctor updated successfully: {}", updatedDoctor);
	    }

	    @Test
	    void testGetDoctorById() throws DoctorNotFoundException {
	        int doctorId = 1;
	        Doctor doctor = service.getDoctorById(doctorId);
	        assertNotNull(doctor);
	        assertEquals(doctorId, doctor.getDoctorId());
	        logger.info("Doctor fetched successfully: {}", doctor);
	    }

	    @Test
	    void testDeleteDoctorById() throws DoctorNotFoundException {
	        int doctorId = 1002;
	        String result = service.deleteDoctorById(doctorId);
	        assertEquals("Doctor deleted successfully", result);
	        logger.info("Doctor deleted successfully. Result: {}", result);
	    }

	    @Test
	    void testGetByDoctorName() throws DoctorNotFoundException {
	        String doctorName = "Dr. Sharma";
	        Doctor doctor = service.getByDoctorName(doctorName).get(0);
	        assertNotNull(doctor);
	        assertEquals(doctorName, doctor.getDoctorName());
	        logger.info("Doctor fetched successfully by name: {}", doctor);
	    }

}
