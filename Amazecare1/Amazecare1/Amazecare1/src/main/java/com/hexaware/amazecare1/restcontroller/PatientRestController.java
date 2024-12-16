package com.hexaware.amazecare1.restcontroller;
import java.util.HashMap;
/*
 * Author=Vinayak
 */
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.entities.Patient;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.service.IPatientService;

import jakarta.validation.Valid;
//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    @Autowired
    IPatientService service;

    Logger logger =	   LoggerFactory.getLogger(PatientRestController.class);
    
    // Register a new patient
    @PostMapping("/registerpatient")
    @PreAuthorize("hasAuthority('patient')")
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody Patient patient) {
        Patient patient1=service.registerPatient(patient);
        return ResponseEntity.ok(patient1);
    }

    // Update patient information
    @PutMapping("/updatepatient/{patientId}")
    @PreAuthorize("hasAuthority('patient')")
    public ResponseEntity<Map<String, String>> updatePatientInfo(@PathVariable int patientId,@Valid @RequestBody Patient patient) throws PatientNotFoundException{
    	service.updatePatientInfo(patientId, patient);

        // Return a JSON response with a success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "Patient Updated Successfully");

        return ResponseEntity.ok(response); // Return JSON response with HTTP 200 OK
    }
    
    
    //Get Patient By ID
    @GetMapping("/getPatientbyid/{patientId}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('patient') or hasAuthority('doctor')")
	public Patient   getPatientById(@PathVariable int patientId) throws PatientNotFoundException {
		
		return  service.getPatientById(patientId);
		
	}
    //Get By Patient Name
    @GetMapping("/getPatientByName/{patientName}")
    @PreAuthorize("hasAuthority('admin')")
	public List<Patient>  getPatientByName(@PathVariable String patientName) throws PatientNotFoundException{
		
		
		return service.getByPatientName(patientName);
		
	}
    
    @GetMapping("/getAllPatients")
    @PreAuthorize("hasAuthority('admin')")
    public List<Patient> viewAllPatients()
    { return service.viewAllPatients();}
    
}
   
