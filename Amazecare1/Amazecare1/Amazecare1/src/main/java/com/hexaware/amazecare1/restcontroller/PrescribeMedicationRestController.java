package com.hexaware.amazecare1.restcontroller;
/*
 * Author=Devansh
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.dto.PrescribeMedicationDTO;
import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;
import com.hexaware.amazecare1.exceptions.PrescriptionNotFoundException;
import com.hexaware.amazecare1.service.IPrescribeMedicationService;

import jakarta.validation.Valid;
//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/prescribemedications")
public class PrescribeMedicationRestController {
	
	@Autowired
	IPrescribeMedicationService service;
	Logger logger =	   LoggerFactory.getLogger(PrescribeMedicationRestController.class);
	
	
	//Add Prescription
	@PostMapping(value="/add-Prescription",consumes = "application/json",produces = "application/json")
	@PreAuthorize("hasAuthority('doctor')")
	public ResponseEntity<?> PrescribeMedications(@Valid @RequestBody PrescribeMedicationDTO prescribeDTO) throws PatientNotFoundException, DoctorNotFoundException {
		PrescribeMedication pres=service.prescribeMedications(prescribeDTO);
		return ResponseEntity.ok(pres);}
	
	
		//Get Prescription by ID
		@GetMapping("/get-Prescription-by-id/{pid}") 
		@PreAuthorize("hasAuthority('admin')")
		public ResponseEntity<PrescribeMedicationDTO>   getPrescriptionById(@PathVariable int pid) throws PrescriptionNotFoundException {
			
			PrescribeMedicationDTO pres=service.getPrescriptionById(pid);
			return ResponseEntity.ok(pres);

	}
		
		//Get All Prescriptions
		@GetMapping(value="/get-all-prescriptions",produces = "application/json")
		@PreAuthorize("hasAuthority('admin')")
		public ResponseEntity<List<PrescribeMedicationDTO>> viewPrescriptions() {
	        List<PrescribeMedicationDTO> prescriptions = service.viewAllPrescriptions();
	        return ResponseEntity.ok(prescriptions); // Return the list of PrescriptionDTOs
	    }
		
		
		//Get Prescription by Patient ID
		@GetMapping("/get-Prescription-by-patientid/{patientId}") 
		@PreAuthorize("hasAuthority('patient')")
		public ResponseEntity<?> findPrescriptionByPatientId(@PathVariable Integer patientId) throws PatientNotFoundException {
	        if (patientId == null) {
	            return ResponseEntity.badRequest().body("Patient ID is required.");
	        }

	        List<PrescribeMedicationDTO> prescriptions = service.findPrescriptionByPatientId(patientId);

	        if (prescriptions.isEmpty()) {
	            throw new PatientNotFoundException("No Patient found for patient ID: " + patientId);
	        }

	        return ResponseEntity.ok(prescriptions);
	    }
	}

