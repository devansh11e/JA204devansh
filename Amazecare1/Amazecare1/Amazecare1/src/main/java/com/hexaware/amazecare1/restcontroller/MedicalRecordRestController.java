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
//@CrossOrigin("http://localhost:4200")
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
	public   ResponseEntity<Map<String, String>>  updateMedicalRecord(@PathVariable int recordId,@Valid @RequestBody MedicalRecordDTO medDTO) throws AppointmentNotFoundException, MedicalRecordNotFoundException {

		service.updateMedicalRecord(recordId,medDTO);
		Map<String, String> response = new HashMap<>();
        response.put("message", "Appointment Updated Successfully");
		return ResponseEntity.ok(response);}
	
	
	
	//Get Record by ID
	@GetMapping("/getRecordbyid/{rid}") 
	@PreAuthorize("hasAuthority('doctor') ")
	public ResponseEntity<MedicalRecordDTO>   getRecordById(@PathVariable int rid) throws MedicalRecordNotFoundException {
		
		MedicalRecordDTO medical =service.getRecordById(rid);
		
		return ResponseEntity.ok(medical);}
	
	
	
	//Get all Medical Record
	@GetMapping(value="/getallMedicalRecord",produces = "application/json")
	@PreAuthorize(" hasAuthority('admin')")
	public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords() {
        List<MedicalRecordDTO> records = service.viewMedicalHistory();
        return ResponseEntity.ok(records);
    }
	
	
	//Get Record by Diagnosis Name
	@GetMapping("/getbydiagnosis/{diagnosis}")
	@PreAuthorize("hasAuthority('doctor')")
	public ResponseEntity<List<MedicalRecordDTO>>  getByDiagnosis(@PathVariable String diagnosis) throws MedicalRecordNotFoundException{
		
		
		List<MedicalRecordDTO> medical=service.getByDiagnosis(diagnosis);
		return ResponseEntity.ok(medical);
		
	}
}
