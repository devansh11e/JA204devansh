package com.hexaware.amazecare1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare1.dto.AppointmentDTO;
import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.repositories.AppointmentRepository;
import com.hexaware.amazecare1.repositories.DoctorRepository;
import com.hexaware.amazecare1.repositories.PatientRepository;
@SpringBootTest
class AppointmentServiceImplTest {

    @Mock
    AppointmentRepository appointmentRepo; // Mocking the service
    @Mock
    private DoctorRepository doctorRepo;
    @Mock
    private PatientRepository patientRepo;

    @InjectMocks
    AppointmentServiceImpl serviceImpl; // Service under test

    Logger logger = LoggerFactory.getLogger(AppointmentServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testScheduleAppointment() throws PatientNotFoundException, DoctorNotFoundException {
    	   Integer patientId = 1001;
    	    Integer doctorId = 100;
    	    LocalDate appointmentDate = LocalDate.of(2024, 11, 25);
    	    LocalDate dateOfBirth = LocalDate.of(2001, 7, 1);
    	    LocalDate prefferedDate = LocalDate.of(2024, 11, 25);
    	    Patient patient = new Patient(1001, "John Doe",dateOfBirth , "Male","7654321021", "Fever", "Routine Checkup", prefferedDate);
    	    Doctor doctor = new Doctor(100, "Dr. Sharma", "Cardiology", 15, "MD", "Senior Consultant","Available");
    	    
    	    when(doctorRepo.findById(doctorId)).thenReturn(Optional.of(doctor));
    	    when(patientRepo.findById(patientId)).thenReturn(Optional.of(patient));
    	    
    	    // Assuming the AppointmentDTO is updated to use patientId and doctorId
    	    AppointmentDTO appointment = new AppointmentDTO(1, patientId, doctorId, appointmentDate, "10:00 AM", "Scheduled", "Regular Checkup");

    	    // Assuming AppointmentDTO is converted to Appointment within the service layer
    	    Appointment expectedAppointment = new Appointment(1, patient, doctor, appointmentDate, "10:00 AM", "Scheduled", "Regular Checkup");

    	    // Mock the service to return an Appointment, not AppointmentDTO
    	    when(appointmentRepo.save(any(Appointment.class))).thenReturn(expectedAppointment);

    	    // Call the service method which internally handles AppointmentDTO to Appointment conversion
    	    Appointment scheduledAppointment = serviceImpl.scheduleAppointment(appointment);

    	    // Assertions
    	    assertNotNull(scheduledAppointment);
    	    assertEquals("Scheduled", scheduledAppointment.getStatus());
    	    verify(appointmentRepo, times(1)).save(any(Appointment.class));

    	    logger.info("Appointment scheduled successfully: {}", scheduledAppointment);
    	}
    

    @Test
    void testCancelAppointment() throws AppointmentNotFoundException {
        int appointmentId = 1;
        Integer patientId = 1001;
	    Integer doctorId = 100;
	    LocalDate appointmentDate = LocalDate.of(2024, 11, 25);
	    LocalDate dateOfBirth = LocalDate.of(2001, 7, 1);
	    LocalDate prefferedDate = LocalDate.of(2024, 11, 25);
	    Patient patient = new Patient(1001, "John Doe",dateOfBirth , "Male","7654321021", "Fever", "Routine Checkup", prefferedDate);
	    Doctor doctor = new Doctor(100, "Dr. Sharma", "Cardiology", 15, "MD", "Senior Consultant","Available");
        Appointment appointment = new Appointment(1, patient, doctor, appointmentDate, "10:00 AM", "Scheduled", "Regular Checkup");
        when(appointmentRepo.save(any(Appointment.class))).thenReturn(new Appointment());
        when(appointmentRepo.findById(appointmentId)).thenReturn(Optional.of(appointment));
        int result = serviceImpl.cancelAppointment(appointmentId);

        assertEquals(1, result);
        verify(appointmentRepo, times(1)).save(any(Appointment.class));
        logger.info("Appointment cancelled successfully. Result: {}", result);
    }
}
