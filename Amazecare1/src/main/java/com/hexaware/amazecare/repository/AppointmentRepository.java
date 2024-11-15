package com.hexaware.amazecare.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hexaware.amazecare.entities.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
    // Find all appointments for a specific patient
    List<Appointment> findByPatientId(int patientId);

    // Find all appointments by a specific date
    List<Appointment> findByDate(String date);

    // Optional: Find appointments by patient and date (if needed for additional filtering)
    List<Appointment> findByPatientIdAndDate(int patientId, String date);
}
