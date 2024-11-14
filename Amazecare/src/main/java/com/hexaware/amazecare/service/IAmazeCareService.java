package com.hexaware.amazecare.service;

import java.util.List;

import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.PrescribeMedication;

public interface IAmazeCareService {
	//login Doctor
	
	//add doctor
	Doctor registerDoctor(Doctor doctor);
	
	//update doctor
    Doctor updateDoctor(Doctor doctor);
    
    //Find Doctor by ID
    Doctor getDoctorById(int did);
    
    //Delete Doctor by ID
    String deleteDoctorById(int did);
    
    
    //display all doctors
    List<Doctor> viewAllDoctors();
    
    
    //add medical record
	MedicalRecord conductConsultation(MedicalRecord medical);
	
	//update Medical Record
    MedicalRecord updateMedicalRecord(MedicalRecord medical);
    
    //add prescribed medication
	PrescribeMedication prescribeMedications(PrescribeMedication pr);
	
	//display all records
	List<MedicalRecord> viewMedicalHistory();
}
