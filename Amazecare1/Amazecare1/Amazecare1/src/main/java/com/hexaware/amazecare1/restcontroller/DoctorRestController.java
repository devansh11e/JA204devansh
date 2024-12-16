package com.hexaware.amazecare1.restcontroller;
import java.util.HashMap;
/*
 * Author=Devansh
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
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.service.IDoctorService;

import jakarta.validation.Valid;
//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {
	@Autowired
	IDoctorService service;
	Logger logger =	   LoggerFactory.getLogger(DoctorRestController.class);
	
	
	///////////////////DOCTOR MODULE///////////////////////
	
	// Adding Doctor
	@PostMapping(value="/addDoctor",consumes = "application/json",produces = "application/json")
	@PreAuthorize("hasAuthority('doctor')")
	public Doctor registerDoctor(@Valid @RequestBody Doctor doc) {

		return service.registerDoctor(doc);

	}
	
	//Updating Doctor by DoctorId
	@PutMapping("/updateDoctor/{doctorId}")
	@PreAuthorize("hasAuthority('doctor')")
	public ResponseEntity<Map<String, String>>  updateDoctor(@PathVariable int doctorId,@Valid @RequestBody Doctor doc) throws DoctorNotFoundException{

		service.updateDoctor(doctorId,doc);
		 Map<String, String> response = new HashMap<>();
	        response.put("message", "Doctor Updated Successfully");

	        return ResponseEntity.ok(response);}
	
	
	//Get Doctor By ID
	@GetMapping("/getDoctorbyid/{did}") 
	@PreAuthorize("hasAuthority('admin') or hasAuthority('patient') or hasAuthority('doctor')")
	public Doctor   getDoctorById(@PathVariable int did) throws DoctorNotFoundException  {
		
		return  service.getDoctorById(did);
		
	}
	
	
	// Get All Doctor
	@GetMapping(value="/getallDoctor",produces = "application/json")
	@PreAuthorize(" hasAuthority('patient') ")
	public List<Doctor>  viewAllDoctors(){
		
		
		return service.viewAllDoctors();
		
	}
	
	
	//Get Doctor By Name
	@GetMapping("/getbyDoctorName/{doctor_name}")
	@PreAuthorize("hasAuthority('admin')")
	public List<Doctor>  getByDoctorName(@PathVariable String doctor_name) throws DoctorNotFoundException{
		
		
		return service.getByDoctorName(doctor_name);
		
	}
}
