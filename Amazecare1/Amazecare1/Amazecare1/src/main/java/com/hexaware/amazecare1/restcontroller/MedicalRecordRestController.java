package com.hexaware.amazecare1.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	
	@PostMapping(value="/addMedicalrecord/{appointmentId}",consumes = "application/json",produces = "application/json")
	public MedicalRecord conductConsultation(@PathVariable int appointmentId,@Valid @RequestBody MedicalRecord m) throws AppointmentNotFoundException {

		return service.conductConsultation(appointmentId,m);

	}
	
	@PutMapping("/updateMedicalRecord")
	public MedicalRecord  updateMedicalRecord(@Valid @RequestBody MedicalRecord m) {

		return service.updateMedicalRecord(m);}
	
	@GetMapping("/getRecordbyid/{rid}") 
	public MedicalRecord   getRecordById(@PathVariable int rid) throws MedicalRecordNotFoundException {
		
		return  service.getRecordById(rid);}
	
	@GetMapping(value="/getallMedicalRecord",produces = "application/json")
	public List<MedicalRecord>  viewMedicalHistory(){
		
		
		return service.viewMedicalHistory();}
	@GetMapping("/getbydiagnosis/{diagnosis}")
	public List<MedicalRecord>  getByDiagnosis(@PathVariable String diagnosis) throws MedicalRecordNotFoundException{
		
		
		return service.getByDiagnosis(diagnosis);
		
	}
}
