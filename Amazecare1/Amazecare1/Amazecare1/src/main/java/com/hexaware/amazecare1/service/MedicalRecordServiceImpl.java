package com.hexaware.amazecare1.service;
/*
 * Author=Devansh
 */
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.dto.MedicalRecordDTO;
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
	    public MedicalRecord conductConsultation(MedicalRecordDTO medicalDTO) throws AppointmentNotFoundException {
	    	Appointment app = appointmentRepo.findById(medicalDTO.getAppointmentId())
	                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
	    	 if ("Cancelled".equalsIgnoreCase(app.getStatus())) {
	    	        throw new AppointmentNotFoundException("Appointment has been cancelled");
	    	    }  
	    	
	                MedicalRecord medicalRecord = new MedicalRecord();
	                medicalRecord.setAppointment(app);  // Set the appointment reference
	                medicalRecord.setDiagnosis(medicalDTO.getDiagnosis());
	                medicalRecord.setPrescription(medicalDTO.getPrescription());
	                medicalRecord.setNotes(medicalDTO.getNotes());

	                // Save the MedicalRecord entity
	                return medicalRecordRepo.save(medicalRecord);
	            }
	    

	    @Override
	    public String updateMedicalRecord(int recordId,MedicalRecordDTO medicalDTO) throws MedicalRecordNotFoundException, AppointmentNotFoundException{
	    	MedicalRecord medicalRecord = medicalRecordRepo.findById(recordId)
	                .orElseThrow(() -> new MedicalRecordNotFoundException("Record not found"));
	    	Appointment app = appointmentRepo.findById(medicalDTO.getAppointmentId())
	                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
	    	 if ("Cancelled".equalsIgnoreCase(app.getStatus())) {
	    	        throw new AppointmentNotFoundException("Appointment has been cancelled");
	    	    }  
	    	      
	                
	                medicalRecord.setAppointment(app);  // Set the appointment reference
	                medicalRecord.setDiagnosis(medicalDTO.getDiagnosis());
	                medicalRecord.setPrescription(medicalDTO.getPrescription());
	                medicalRecord.setNotes(medicalDTO.getNotes());

	                // Save the MedicalRecord entity
	                 medicalRecordRepo.save(medicalRecord);
	                 return "Record updated Successfully";
	    }

	    @Override
	    public MedicalRecordDTO getRecordById(int rid) throws MedicalRecordNotFoundException {
	        try {
	            MedicalRecord medical= medicalRecordRepo.findById(rid)
	                    .orElseThrow(() -> new MedicalRecordNotFoundException("Medical record not found with id: " + rid));
	            return convertToDTO(medical);
	        } catch (MedicalRecordNotFoundException e) {
	            e.printStackTrace(); // Log the exception details
	            throw e; // Re-throw the exception to let the caller handle it
	        }
	    }

	    @Override
	    public List<MedicalRecordDTO> getByDiagnosis(String diagnosis) throws MedicalRecordNotFoundException {
	        try {
	            // Fetch medical records by diagnosis
	            List<MedicalRecord> records = medicalRecordRepo.findByDiagnosis(diagnosis);

	            // If no records are found, throw a custom exception
	            if (records.isEmpty()) {
	                throw new MedicalRecordNotFoundException("No medical records found with diagnosis: " + diagnosis);
	            }

	            // Return the found records
	            return records.stream()
		                .map(this::convertToDTO)
		                .collect(Collectors.toList());
	        } catch (MedicalRecordNotFoundException ex) {
	            // Log the exception message and rethrow the exception
	            System.err.println(ex.getMessage());
	            throw ex;
	        }
	    }


	    @Override
	    public List<MedicalRecordDTO> viewMedicalHistory() {
	        List<MedicalRecord> records = medicalRecordRepo.findAll();
	        return records.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    // Helper method to map MedicalRecord to MedicalRecordDTO
	    private MedicalRecordDTO convertToDTO(MedicalRecord record) {
	        MedicalRecordDTO dto = new MedicalRecordDTO();
	        dto.setRecordId(record.getRecordId());
	        dto.setAppointmentId(record.getAppointment().getAppointmentId());
	        dto.setDiagnosis(record.getDiagnosis());
	        dto.setPrescription(record.getPrescription());
	        dto.setNotes(record.getNotes());
	        return dto;
	    }

}
