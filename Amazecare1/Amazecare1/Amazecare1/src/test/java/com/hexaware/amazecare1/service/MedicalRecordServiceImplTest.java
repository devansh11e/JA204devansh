package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.MedicalRecord;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;

class MedicalRecordServiceImplTest {

    @Mock
    IMedicalRecordService mockService;

    @InjectMocks
    MedicalRecordServiceImpl serviceImpl;

    Logger logger = LoggerFactory.getLogger(MedicalRecordServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConductConsultation() throws AppointmentNotFoundException {
        Appointment appointment = new Appointment(101, null, null, new Date(), "3 PM", "Routine Checkup", "Hypertension");
        MedicalRecord medicalRecord = new MedicalRecord(1, appointment, "Anxiety", "Amoxicillin", "Patient is stable");

        when(mockService.conductConsultation(101, medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord newRecord = serviceImpl.conductConsultation(101, medicalRecord);

        assertNotNull(newRecord);
        assertEquals("Anxiety", newRecord.getDiagnosis());
        verify(mockService, times(1)).conductConsultation(101, medicalRecord);
        logger.info("Consultation conducted successfully: {}", newRecord);
    }

    @Test
    void testViewMedicalHistory() {
        List<MedicalRecord> records = Arrays.asList(new MedicalRecord());
        when(mockService.viewMedicalHistory()).thenReturn(records);

        List<MedicalRecord> fetchedRecords = serviceImpl.viewMedicalHistory();

        assertNotNull(fetchedRecords);
        assertFalse(fetchedRecords.isEmpty());
        verify(mockService, times(1)).viewMedicalHistory();
        logger.info("Medical history fetched successfully.");
    }
}
