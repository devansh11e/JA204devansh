package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.hexaware.amazecare1.dto.MedicalRecordDTO;
import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.MedicalRecord;
import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.repositories.AppointmentRepository;
import com.hexaware.amazecare1.repositories.MedicalRecordRepository;
@SpringBootTest
class MedicalRecordServiceImplTest {

    @Mock
    MedicalRecordRepository medicalRepo;
    
    @Mock
    AppointmentRepository appointmentRepo;

    @InjectMocks
    MedicalRecordServiceImpl serviceImpl;

    Logger logger = LoggerFactory.getLogger(MedicalRecordServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConductConsultation() throws AppointmentNotFoundException {
    	LocalDate appointmentDate = LocalDate.of(2024, 11, 25);
    	Integer appointmentId=1;
    	Patient patient = mock(Patient.class);
        Doctor doctor = mock(Doctor.class);
        Appointment appointment = new Appointment(1, patient, doctor, appointmentDate, "3 PM", "Cancelled", "Hypertension");
        MedicalRecordDTO medicalRecord = new MedicalRecordDTO(100, appointmentId, "Anxiety", "Amoxicillin", "Patient is stable");
        MedicalRecord medical = new MedicalRecord(100, appointment, "Anxiety", "Amoxicillin", "Patient is stable");
        when(medicalRepo.save(any(MedicalRecord.class))).thenReturn(medical);
        

        MedicalRecord newRecord = serviceImpl.conductConsultation(medicalRecord);

        assertNotNull(newRecord);
        assertEquals("Anxiety", newRecord.getDiagnosis());
        verify(medicalRepo, times(1)).save(any(MedicalRecord.class));
        logger.info("Consultation conducted successfully: {}", newRecord);
    }

    @Test
    void testViewMedicalHistory() {
        List<MedicalRecord> records = Arrays.asList(new MedicalRecord());
        when(medicalRepo.findAll()).thenReturn(records);

        List<MedicalRecord> fetchedRecords = serviceImpl.viewMedicalHistory();

        assertNotNull(fetchedRecords);
        assertFalse(fetchedRecords.isEmpty());
        verify(medicalRepo, times(1)).findAll();
        logger.info("Medical history fetched successfully.");
    }
}
