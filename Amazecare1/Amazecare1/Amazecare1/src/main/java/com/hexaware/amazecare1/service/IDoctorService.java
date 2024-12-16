package com.hexaware.amazecare1.service;
/*
 * Author=Devansh
 */
import java.util.List;

import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;

public interface IDoctorService {
	
	    //Register Doctor
	    Doctor registerDoctor(Doctor doctor);
	    
	    //Update Doctor
	    String updateDoctor(int doctorId,Doctor doctor) throws DoctorNotFoundException;
	    
	    //Get DoctorById
	    Doctor getDoctorById(int did) throws DoctorNotFoundException;
	    
	    
	    
	    //Get Doctor by Name
	    List<Doctor> getByDoctorName(String doctorName) throws DoctorNotFoundException;
	    
	    
	    //View All Doctors
	    List<Doctor> viewAllDoctors();
}
