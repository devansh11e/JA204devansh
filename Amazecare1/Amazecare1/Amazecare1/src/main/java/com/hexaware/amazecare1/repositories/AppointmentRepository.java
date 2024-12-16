package com.hexaware.amazecare1.repositories;
/*
 * Author=Vinayak
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.amazecare1.dto.AppointmentDTO;
import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
	//Finding Appointment by doctor Id
	 List<Appointment> findAppointmentByDoctor_DoctorId(Integer doctorId) throws DoctorNotFoundException;

}
