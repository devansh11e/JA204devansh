package com.hexaware.amazecare1.service;
/*
 * Author=Devansh
 */
import java.util.List;

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
	    public PrescribeMedication getPrescriptionById(int pid) throws PrescriptionNotFoundException {
	        try {
	            return medicationRepo.findById(pid)
	                    .orElseThrow(() -> new PrescriptionNotFoundException("Prescription not found with id: " + pid));
	        } catch (PrescriptionNotFoundException e) {
	            e.printStackTrace(); // Log the exception details
	            throw e; // Re-throw the exception to let the caller handle it
	        }
	    }

	    @Override
	    public List<PrescribeMedication> viewAllPrescriptions() {
	        return medicationRepo.findAll();
	    }

		@Override
		public List<PrescribeMedication> findPrescriptionByPatientId(Integer patientId) throws PatientNotFoundException{
			 try {
		            List<PrescribeMedication> pres = medicationRepo.findPrescriptionByPatient_PatientId(patientId);
		            
		            // Check if no patient is found with the given ID
		            if (pres.isEmpty()) {
		                throw new PatientNotFoundException("No patients found with ID: " + patientId);
		            }
		            
		            return pres;
		        } catch (PatientNotFoundException e) {
		            // Handle the exception (e.g., log it)
		            System.err.println("Error: " + e.getMessage());
		            
		            // Re-throw the exception so it can be handled further up the stack if needed
		            throw e;
		        }
		    }
			
		}


