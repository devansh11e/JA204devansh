package com.hexaware.amazecare1.service;
/*
 * Author=Devansh
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.repositories.DoctorRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class DoctorServiceImpl implements IDoctorService{
	
	 @Autowired
	  private DoctorRepository doctorRepo;
	 
	 Logger logger =LoggerFactory.getLogger(DoctorServiceImpl.class);

	 
	 //Register Doctor
	 @Override
	    public Doctor registerDoctor(Doctor doctor) {
	        return doctorRepo.save(doctor);
	    }

	 
	 //Update Doctor
	    @Override
	    public String updateDoctor(int doctorId,Doctor doctor)throws DoctorNotFoundException {
	    	Doctor existing=doctorRepo.findById(doctorId).orElseThrow(()-> new DoctorNotFoundException("Doctor not found with ID:  "+doctorId));
	    	existing.setDoctorName(doctor.getDoctorName());
	    	existing.setSpeciality(doctor.getSpeciality());
	    	existing.setExperience(doctor.getExperience());
	    	existing.setQualification(doctor.getQualification());
	    	existing.setDesignation(doctor.getDesignation());
	    	existing.setAvailability(doctor.getAvailability());
	         doctorRepo.save(existing);
	         return "Doctor updated Successfully";
	    }

	    
	    //Get Doctor By ID
	    @Override
	    public Doctor getDoctorById(int did) throws DoctorNotFoundException  {
	        try {
				return doctorRepo.findById(did)
				        .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + did));
			} catch (DoctorNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
	    }

	    
	    //Delete Doctor By ID
	    @Override
	    public String deleteDoctorById(int did) throws DoctorNotFoundException {
	        try {
	            // Check if the doctor exists before attempting deletion
	            if (!doctorRepo.existsById(did)) {
	                // If not, throw a custom exception with a relevant message
	                throw new DoctorNotFoundException("Doctor with ID " + did + " not found");
	            }
	            // If the doctor exists, delete the doctor
	            doctorRepo.deleteById(did);
	            return "Doctor Record Deleted";
	        } catch (DoctorNotFoundException ex) {
	            // Log the exception message and rethrow the exception
	            System.err.println(ex.getMessage());
	            throw ex;
	        }
	    }


	    
	    //Get Doctor By Name
	    @Override
	    public List<Doctor> getByDoctorName(String doctorName) throws DoctorNotFoundException {
	        try {
	            // Attempt to find doctors by name
	            List<Doctor> doctors = doctorRepo.findByDoctorName(doctorName);
	            
	            // If no doctors are found, throw DoctorNotFoundException
	            if (doctors.isEmpty()) {
	                throw new DoctorNotFoundException("No doctors found with name: " + doctorName);
	            }
	            
	            // Return the list of doctors if found
	            return doctors;
	        } catch (DoctorNotFoundException e) {
	            // Log the error and rethrow the exception
	            System.err.println(e.getMessage());
	            throw e;  // Rethrow the exception
	        }
	    }


	    
	    //View All doctors
	    @Override
	    public List<Doctor> viewAllDoctors() {
	        return doctorRepo.findAll();
	    }

}
