package com.hexaware.amazecare1.restcontroller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hexaware.amazecare1.entities.Doctor;

@SpringBootTest
class DoctorRestControllerTest {

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(DoctorRestControllerTest.class);

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Perform any setup needed before running tests
    }

    @Test
    void testRegisterDoctor() {
        Doctor doctor = new Doctor();
        doctor.setDoctorName("Dr. John");
        doctor.setSpeciality("Cardiology");
        doctor.setExperience(10);
        doctor.setQualification("MBBS, MD");
        doctor.setDesignation("Consultant");
        doctor.setAvailability("Monday to Friday");

        ResponseEntity<Doctor> response = restTemplate.postForEntity(
                "http://localhost:8081/api/doctors/addDoctor", doctor, Doctor.class);

        Doctor registeredDoctor = response.getBody();

        assertNotNull(registeredDoctor);
        assertEquals("Dr. John", registeredDoctor.getDoctorName());
    }

    @Test
    void testUpdateDoctor() {
        int doctorId = 101;

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setDoctorId(doctorId);
        updatedDoctor.setDoctorName("Dr. John Updated");
        updatedDoctor.setSpeciality("Neurology");
        updatedDoctor.setExperience(12);
        updatedDoctor.setQualification("MBBS, MD");
        updatedDoctor.setDesignation("Senior Consultant");
        updatedDoctor.setAvailability("Monday to Saturday");

        restTemplate.put("http://localhost:8081/api/doctors/update/" + doctorId, updatedDoctor);

        ResponseEntity<Doctor> response = restTemplate.getForEntity(
                "http://localhost:8081/api/doctors/updateDoctor/" + doctorId, Doctor.class);

        Doctor retrievedDoctor = response.getBody();

        assertNotNull(retrievedDoctor);
        assertEquals("Dr. John Updated", retrievedDoctor.getDoctorName());
    }

    @Test
    void testGetDoctorById() {
        int doctorId = 101;

        ResponseEntity<Doctor> response = restTemplate.getForEntity(
                "http://localhost:8081/api/doctors/getDoctorbyid/" + doctorId, Doctor.class);

        Doctor doctor = response.getBody();

        assertNotNull(doctor);
        assertEquals(doctorId, doctor.getDoctorId());
    }

    @Test
    void testViewAllDoctors() {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8081/api/doctors/getallDoctor", List.class);

        List<Doctor> doctors = response.getBody();

        assertNotNull(doctors);
        assertTrue(doctors.size() > 0);
    }

    @Test
    void testDeleteDoctorById() {
        int doctorId = 105;

        restTemplate.delete("http://localhost:8081/api/doctors/deleteDoctorbyid/" + doctorId);

        ResponseEntity<Doctor> response = restTemplate.getForEntity(
                "http://localhost:8081/api/doctors/get/" + doctorId, Doctor.class);

        Doctor doctor = response.getBody();

        assertNull(doctor);
    }

    @Test
    void testGetByDoctorName() {
        String doctorName = "Dr. John";

        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8081/api/doctors/getbyDoctorName/" + doctorName, List.class);

        List<Doctor> doctors = response.getBody();

        assertNotNull(doctors);
        assertTrue(doctors.size() > 0);
    }
}
