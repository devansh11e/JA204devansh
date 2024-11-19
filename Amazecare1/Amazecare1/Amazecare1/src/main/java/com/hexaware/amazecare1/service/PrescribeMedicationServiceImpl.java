package com.hexaware.amazecare1.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.exceptions.PrescriptionNotFoundException;
import com.hexaware.amazecare1.repositories.PrescribeMedicationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrescribeMedicationServiceImpl implements IPrescribeMedicationService{
	
	 @Autowired
	    private PrescribeMedicationRepository medicationRepo;
	    
	    Logger logger =LoggerFactory.getLogger( PrescribeMedicationServiceImpl.class);
	    
	    @Override
	    public PrescribeMedication prescribeMedications(PrescribeMedication medication) {
	        return medicationRepo.save(medication);
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

}
