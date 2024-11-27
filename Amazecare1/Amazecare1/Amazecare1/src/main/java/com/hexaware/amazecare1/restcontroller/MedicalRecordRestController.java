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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare1.dto.MedicalRecordDTO;
import com.hexaware.amazecare1.entities.MedicalRecord;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.MedicalRecordNotFoundException;
import com.hexaware.amazecare1.service.IMedicalRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicalrecords")
public class MedicalRecordRestController {
	
	@Autowired
	IMedicalRecordService service;
	Logger logger =	   LoggerFactory.getLogger(MedicalRecordRestController.class);

	//Adding Medical Record
	@PostMapping(value="/addMedicalrecord",consumes = "application/json",produces = "application/json")
	@PreAuthorize("hasAuthority('doctor')")
	public ResponseEntity<?> conductConsultation(@Valid @RequestBody MedicalRecordDTO medicalDTO) throws AppointmentNotFoundException {
		MedicalRecord med=service.conductConsultation(medicalDTO);
		return ResponseEntity.ok(med);

	}
	
	
	//Updating Medical Record By Record ID
	@PutMapping("/updateMedicalRecord/{recordId}")
	@PreAuthorize("hasAuthority('doctor')")
	public  ResponseEntity<?>  updateMedicalRecord(@PathVariable int recordId,@Valid @RequestBody MedicalRecordDTO medDTO) throws AppointmentNotFoundException, MedicalRecordNotFoundException {

		String med= service.updateMedicalRecord(recordId,medDTO);
		return ResponseEntity.ok(med);}
	
	
	
	//Get Record by ID
	@GetMapping("/getRecordbyid/{rid}") 
	@PreAuthorize("hasAuthority('doctor') or hasAuthority('patient') ")
	public MedicalRecord   getRecordById(@PathVariable int rid) throws MedicalRecordNotFoundException {
		
		return  service.getRecordById(rid);}
	
	
	
	//Get all Medical Record
	@GetMapping(value="/getallMedicalRecord",produces = "application/json")
	@PreAuthorize("hasAuthority('doctor') or hasAuthority('admin')")
	public List<MedicalRecord>  viewMedicalHistory(){
		
		
		return service.viewMedicalHistory();}
	
	
	//Get Record by Diagnosis Name
	@GetMapping("/getbydiagnosis/{diagnosis}")
	@PreAuthorize("hasAuthority('doctor')")
	public List<MedicalRecord>  getByDiagnosis(@PathVariable String diagnosis) throws MedicalRecordNotFoundException{
		
		
		return service.getByDiagnosis(diagnosis);
		
	}
}
