package com.hexaware.amazecare1.repositories;
/*
 * Author=Devansh
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.amazecare1.entities.PrescribeMedication;
@Repository
public interface PrescribeMedicationRepository extends JpaRepository<PrescribeMedication,Integer>{
	
	//Find Prescription by Patient Id
	 List<PrescribeMedication>  findPrescriptionByPatient_PatientId(Integer patientId);
}
