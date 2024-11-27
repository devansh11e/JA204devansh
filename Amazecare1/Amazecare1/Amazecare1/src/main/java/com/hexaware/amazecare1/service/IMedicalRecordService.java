package com.hexaware.amazecare1.service;
/*
 * Author=Devansh
 */
import java.util.List;

import com.hexaware.amazecare1.dto.MedicalRecordDTO;
import com.hexaware.amazecare1.entities.MedicalRecord;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.MedicalRecordNotFoundException;

public interface IMedicalRecordService {
	//Creating medical Record
	MedicalRecord conductConsultation(MedicalRecordDTO medicalDTO) throws AppointmentNotFoundException;
	
	//Update Medical Record
    String updateMedicalRecord(int recordId,MedicalRecordDTO medicalDTO) throws AppointmentNotFoundException, MedicalRecordNotFoundException;
    
    //Get Record BY ID
    MedicalRecord getRecordById(int rid) throws MedicalRecordNotFoundException;
    
    //View Medical History
    List<MedicalRecord> viewMedicalHistory();
    
    //Get By Diagnosis
    List<MedicalRecord> getByDiagnosis(String diagnosis) throws MedicalRecordNotFoundException;
}
