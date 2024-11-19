package com.hexaware.amazecare1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.amazecare1.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByPatientName(String patientName);
    // select * from patient where patient_name = ?;
    
    List<Patient> findBySymptoms(String symptoms);
    // select * from patient where symptoms = ?;
    
    List<Patient> findByGender(String gender);
    // select * from patient where gender = ?;

    List<Patient> findByContactGreaterThan(double contact);
    // select * from patient where contact > ?;
}
