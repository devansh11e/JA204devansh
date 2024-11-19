package com.hexaware.amazecare1.service;

import java.util.List;

import com.hexaware.amazecare1.entities.MedicalRecord;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.MedicalRecordNotFoundException;

public interface IMedicalRecordService {
	MedicalRecord conductConsultation(int appointmentId,MedicalRecord medical) throws AppointmentNotFoundException;
    MedicalRecord updateMedicalRecord(MedicalRecord medical);
    MedicalRecord getRecordById(int rid) throws MedicalRecordNotFoundException;
    List<MedicalRecord> viewMedicalHistory();
    List<MedicalRecord> getByDiagnosis(String diagnosis) throws MedicalRecordNotFoundException;
}
