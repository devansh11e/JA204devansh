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

import com.hexaware.amazecare1.entities.Doctor;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.service.IDoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {
	@Autowired
	IDoctorService service;
	Logger logger =	   LoggerFactory.getLogger(DoctorRestController.class);
	///////////////////DOCTOR MODULE///////////////////////
	@PostMapping(value="/addDoctor",consumes = "application/json",produces = "application/json")
	public Doctor registerDoctor(@Valid @RequestBody Doctor d) {

		return service.registerDoctor(d);

	}
	
	@PutMapping("/updateDoctor")
	public Doctor  updateDoctor(@Valid @RequestBody Doctor d) {

		return service.updateDoctor(d);}
	
	@GetMapping("/getDoctorbyid/{did}") 
	public Doctor   getDoctorById(@PathVariable int did) throws DoctorNotFoundException  {
		
		return  service.getDoctorById(did);
		
	}
	
	@GetMapping(value="/getallDoctor",produces = "application/json")
	public List<Doctor>  viewAllDoctors(){
		
		
		return service.viewAllDoctors();
		
	}
	
	
	@DeleteMapping("/deleteDoctorbyid/{did}") 
	public String  deleteDoctorById(@PathVariable int did) throws DoctorNotFoundException {
		
		return  service.deleteDoctorById(did);
		
	}
	@GetMapping("/getbyDoctorName/{doctor_name}")
	public List<Doctor>  getByDoctorName(@PathVariable String doctor_name) throws DoctorNotFoundException{
		
		
		return service.getByDoctorName(doctor_name);
		
	}
}
