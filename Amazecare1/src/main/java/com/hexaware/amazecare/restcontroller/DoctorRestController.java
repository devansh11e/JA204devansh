package com.hexaware.amazecare.restcontroller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.PrescribeMedication;
import com.hexaware.amazecare.service.IAmazeCareService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {
	@Autowired
	IAmazeCareService service;
	
	@PostMapping(value="/addDoctor",consumes = "application/json",produces = "application/json")
	public Doctor registerDoctor(@RequestBody Doctor d) {

		return service.registerDoctor(d);

	}
	
	@PutMapping("/updateDoctor")
	public Doctor  updateDoctor(@RequestBody Doctor d) {

		return service.updateDoctor(d);}
	
	@GetMapping("/getDoctorbyid/{eid}") 
	public Doctor   getDoctorById(@PathVariable int did) {
		
		return  service.getDoctorById(did);
		
	}
	
	@GetMapping(value="/getallDoctor",produces = "application/json")
	public List<Doctor>  viewAllDoctors(){
		
		
		return service.viewAllDoctors();
		
	}
	
	
	@DeleteMapping("/deleteDoctorbyid/{eid}") 
	public String  deleteDoctorById(@PathVariable int did) {
		
		return  service.deleteDoctorById(did);
		
	}
	
	@PostMapping(value="/addMedicalrecord",consumes = "application/json",produces = "application/json")
	public MedicalRecord conductConsultation(@RequestBody MedicalRecord m) {

		return service.conductConsultation(m);

	}
	
	@PutMapping("/updateMedicalRecord")
	public MedicalRecord  updateMedicalRecord(@RequestBody MedicalRecord m) {

		return service.updateMedicalRecord(m);}
	
	@GetMapping(value="/getallMedicalRecord",produces = "application/json")
	public List<MedicalRecord>  viewMedicalHistory(){
		
		
		return service.viewMedicalHistory();}
	
	@PostMapping(value="/addPrescription",consumes = "application/json",produces = "application/json")
	public PrescribeMedication PrescribeMedications(@RequestBody PrescribeMedication p) {

		return service.prescribeMedications(p);

	}
}
