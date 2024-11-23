package com.hexaware.amazecare1.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.exceptions.PrescriptionNotFoundException;
import com.hexaware.amazecare1.service.IPrescribeMedicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prescribemedications")
public class PrescribeMedicationRestController {
	
	@Autowired
	IPrescribeMedicationService service;
	Logger logger =	   LoggerFactory.getLogger(PrescribeMedicationRestController.class);
	
	@PostMapping(value="/addPrescription",consumes = "application/json",produces = "application/json")
	public PrescribeMedication PrescribeMedications(@Valid @RequestBody PrescribeMedication p) {

		return service.prescribeMedications(p);}
		
		@GetMapping("/getPrescriptionbyid/{pid}") 
		public PrescribeMedication   getPrescriptionById(@PathVariable int pid) throws PrescriptionNotFoundException {
			
			return  service.getPrescriptionById(pid);

	}
		@GetMapping(value="/getallprescriptions",produces = "application/json")
		public List<PrescribeMedication>  viewAllPrescriptions(){
			
			
			return service.viewAllPrescriptions();}	

}
