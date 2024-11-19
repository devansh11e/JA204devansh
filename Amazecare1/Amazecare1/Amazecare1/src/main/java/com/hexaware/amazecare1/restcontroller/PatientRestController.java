package com.hexaware.amazecare1.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.service.IPatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    @Autowired
    IPatientService service;

    Logger logger =	   LoggerFactory.getLogger(PatientRestController.class);
    // Register a new patient
    @PostMapping("/registerpatient")
    public Patient registerPatient(@Valid @RequestBody Patient patient) {
        return service.registerPatient(patient);
    }

    // Update patient information
    @PutMapping("/updatepatient/{patientId}")
    public Patient updatePatientInfo(@RequestBody Patient patient) {
        return service.updatePatientInfo(patient);
    }
    //Delete Patient by ID
    @DeleteMapping("/deletePatientbyid/{patientId}") 
	public String  deletePatientById(@PathVariable int patientId) throws PatientNotFoundException {
		
		return  service.deletePatientById(patientId);
		
	}
    //Get Patient By ID
    @GetMapping("/getPatientbyid/{patientId}") 
	public Patient   getPatientById(@PathVariable int patientId) throws PatientNotFoundException {
		
		return  service.getPatientById(patientId);
		
	}
    //Get By Patient Name
    @GetMapping("/getPatientByName/{patientName}")
	public List<Patient>  getPatientByName(@PathVariable String patientName) throws PatientNotFoundException{
		
		
		return service.getByPatientName(patientName);
		
	}
    
}
   
