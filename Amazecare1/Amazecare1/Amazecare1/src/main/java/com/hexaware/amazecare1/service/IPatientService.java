package com.hexaware.amazecare1.service;
/*
 * Author=Vinayak
 */
import java.util.List;

import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;

public interface IPatientService {
	
	//Register patient
	 Patient registerPatient(Patient patient);
	 
	 //Updating Patient
	    String updatePatientInfo(int patientId,Patient patient)throws PatientNotFoundException;

	    // Delete Patient By ID
	    String deletePatientById(int patientId) throws PatientNotFoundException;
	    
	    //Get Patient By ID
	    Patient getPatientById(int patientId) throws PatientNotFoundException;
	    
	    //Get Patient by Name
	    List<Patient> getByPatientName(String patientName) throws PatientNotFoundException;
        
}
