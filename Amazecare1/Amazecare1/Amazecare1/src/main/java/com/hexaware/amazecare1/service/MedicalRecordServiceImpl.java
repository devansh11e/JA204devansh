package com.hexaware.amazecare1.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.entities.MedicalRecord;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.MedicalRecordNotFoundException;
import com.hexaware.amazecare1.repositories.AppointmentRepository;
import com.hexaware.amazecare1.repositories.MedicalRecordRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MedicalRecordServiceImpl implements IMedicalRecordService{
	
	 @Autowired
	    private AppointmentRepository appointmentRepo;
	 @Autowired
	    private MedicalRecordRepository medicalRecordRepo;
	 
	 Logger logger =LoggerFactory.getLogger( MedicalRecordServiceImpl.class);
	 
	 @Override
	    public MedicalRecord conductConsultation(int appointmentId,MedicalRecord medical) throws AppointmentNotFoundException {
	    	Appointment app = appointmentRepo.findById(appointmentId)
	                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " +appointmentId));
	        medical.setAppointment(app);
	    	return medicalRecordRepo.save(medical);
	    }

	    @Override
	    public MedicalRecord updateMedicalRecord(MedicalRecord medical) {
	        return medicalRecordRepo.save(medical);
	    }

	    @Override
	    public MedicalRecord getRecordById(int rid) throws MedicalRecordNotFoundException {
	        try {
	            return medicalRecordRepo.findById(rid)
	                    .orElseThrow(() -> new MedicalRecordNotFoundException("Medical record not found with id: " + rid));
	        } catch (MedicalRecordNotFoundException e) {
	            e.printStackTrace(); // Log the exception details
	            throw e; // Re-throw the exception to let the caller handle it
	        }
	    }

	    @Override
	    public List<MedicalRecord> getByDiagnosis(String diagnosis) throws MedicalRecordNotFoundException {
	        try {
	            // Fetch medical records by diagnosis
	            List<MedicalRecord> records = medicalRecordRepo.findByDiagnosis(diagnosis);

	            // If no records are found, throw a custom exception
	            if (records.isEmpty()) {
	                throw new MedicalRecordNotFoundException("No medical records found with diagnosis: " + diagnosis);
	            }

	            // Return the found records
	            return records;
	        } catch (MedicalRecordNotFoundException ex) {
	            // Log the exception message and rethrow the exception
	            System.err.println(ex.getMessage());
	            throw ex;
	        }
	    }


	    @Override
	    public List<MedicalRecord> viewMedicalHistory() {
	        return medicalRecordRepo.findAll();
	    }


}
