package com.hexaware.amazecare1.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.amazecare1.entities.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
    // Find all appointments for a specific patient
    List<Appointment> findByAppointmentId(int appointmentId);

   
    //List<Appointment> findByappointmentDate(String date);
//
}
