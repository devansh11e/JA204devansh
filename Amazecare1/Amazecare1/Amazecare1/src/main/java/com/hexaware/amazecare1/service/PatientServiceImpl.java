package com.hexaware.amazecare1.service;

 /*
  * Author=Vinayak
  */
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService{
	 @Autowired
	    private PatientRepository patientRepo;
	 
	 Logger logger =LoggerFactory.getLogger(PatientServiceImpl.class);
	 
	 @Override
	    public Patient registerPatient(Patient patient) {
	      return  patientRepo.save(patient);
	    
	    }

	    @Override
	    public String updatePatientInfo(int patientId,Patient patient) throws PatientNotFoundException {
	       Patient existing=patientRepo.findById(patientId).orElseThrow(()-> new PatientNotFoundException ("Patient not found with ID:  "+patientId));
	       existing.setPatientName(patient.getPatientName());   
	       existing.setDob(patient.getDob());
	       existing.setGender(patient.getGender());
	       existing.setContact(patient.getContact());
	      
	       patientRepo.save(existing);
	             return "Patient Updated Successfully";
	             
	        }


	    @Override
	    public Patient getPatientById(int patientId) throws PatientNotFoundException {
	        try {
	            // Attempt to find the patient by ID
	            return patientRepo.findById(patientId)
	                    .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + patientId));
	        } catch (PatientNotFoundException e) {
	            // Log the error message and rethrow the exception if needed
	            System.err.println(e.getMessage());
	            throw e;  // Rethrow the exception
	        }
	    }

	    @Override
	    public List<Patient> getByPatientName(String patientName) throws PatientNotFoundException {
	        try {
	            List<Patient> patients = patientRepo.findByPatientName(patientName);
	            
	            // Check if no patient is found with the given name
	            if (patients.isEmpty()) {
	                throw new PatientNotFoundException("No patients found with name: " + patientName);
	            }
	            
	            return patients;
	        } catch (PatientNotFoundException e) {
	            // Handle the exception (e.g., log it)
	            System.err.println("Error: " + e.getMessage());
	            
	            // Re-throw the exception so it can be handled further up the stack if needed
	            throw e;
	        }
	    }

		@Override
		public List<Patient> viewAllPatients() {
			// TODO Auto-generated method stub
			return patientRepo.findAll();
		}
	    
	    
}
