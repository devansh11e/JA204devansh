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

import com.hexaware.amazecare1.dto.PrescribeMedicationDTO;
import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.exceptions.PrescriptionNotFoundException;
import com.hexaware.amazecare1.repositories.DoctorRepository;
import com.hexaware.amazecare1.repositories.PatientRepository;
import com.hexaware.amazecare1.repositories.PrescribeMedicationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrescribeMedicationServiceImpl implements IPrescribeMedicationService{
	
	 @Autowired
	    private PrescribeMedicationRepository medicationRepo;
	  @Autowired
		private PatientRepository patientRepo;
	     @Autowired
	     private DoctorRepository doctorRepo;
	    
	    Logger logger =LoggerFactory.getLogger( PrescribeMedicationServiceImpl.class);
	    
	    @Override
	    public PrescribeMedication prescribeMedications(PrescribeMedicationDTO medicationDTO) throws PatientNotFoundException,DoctorNotFoundException{
	    	 Doctor doctor = doctorRepo.findById(medicationDTO.getDoctorId())
		                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

		        Patient patient = patientRepo.findById(medicationDTO.getPatientId())
		                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));
		        
		        PrescribeMedication prescription = new PrescribeMedication();
		        prescription.setPatient(patient);  // Set the Patient reference
		        prescription.setDoctor(doctor);    // Set the Doctor reference
		        prescription.setMedicationName(medicationDTO.getMedicationName());
		        prescription.setDosage(medicationDTO.getDosage());
		        prescription.setFrequency(medicationDTO.getFrequency());
		        prescription.setDuration(medicationDTO.getDuration());
		        prescription.setInstruction(medicationDTO.getInstruction());
		        prescription.setStartDate(medicationDTO.getStartDate());
		        prescription.setEndDate(medicationDTO.getEndDate());
		        prescription.setQuantity(medicationDTO.getQuantity());

		        // Save the Prescription entity
		        return medicationRepo.save(prescription);
		    }
	    

	    @Override
	    public PrescribeMedicationDTO getPrescriptionById(int pid) throws PrescriptionNotFoundException {
	        	 PrescribeMedication pres=medicationRepo.findById(pid)
	                    .orElseThrow(() -> new PrescriptionNotFoundException("Prescription not found with id: " + pid));
	         return convertToDTO(pres);
	       
	    }

	    @Override
	    public List<PrescribeMedicationDTO> viewAllPrescriptions() {
	        List<PrescribeMedication> prescriptions = medicationRepo.findAll();
	        return prescriptions.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    // Helper method to map Prescription to PrescriptionDTO
	    private PrescribeMedicationDTO convertToDTO(PrescribeMedication prescription) {
	        PrescribeMedicationDTO dto = new PrescribeMedicationDTO();
	        dto.setPrescriptionId(prescription.getPrescriptionId());
	        dto.setPatientId(prescription.getPatient().getPatientId());
	        dto.setDoctorId(prescription.getDoctor().getDoctorId());
	        dto.setMedicationName(prescription.getMedicationName());
	        dto.setDosage(prescription.getDosage());
	        dto.setFrequency(prescription.getFrequency());
	        dto.setDuration(prescription.getDuration());
	        dto.setInstruction(prescription.getInstruction());
	        dto.setStartDate(prescription.getStartDate());
	        dto.setEndDate(prescription.getEndDate());
	        dto.setQuantity(prescription.getQuantity());
	        return dto;
	    }


		@Override
		public List<PrescribeMedicationDTO> findPrescriptionByPatientId(Integer patientId) throws PatientNotFoundException{
			 try {
		            List<PrescribeMedication> pres = medicationRepo.findPrescriptionByPatient_PatientId(patientId);
		            
		            // Check if no patient is found with the given ID
		            if (pres.isEmpty()) {
		                throw new PatientNotFoundException("No patients found with ID: " + patientId);
		            }
		            
		            return pres.stream()
			                .map(this::convertToDTO)
			                .collect(Collectors.toList());
		        } catch (PatientNotFoundException e) {
		            // Handle the exception (e.g., log it)
		            System.err.println("Error: " + e.getMessage());
		            
		            // Re-throw the exception so it can be handled further up the stack if needed
		            throw e;
		        }
		    }
			
		}


