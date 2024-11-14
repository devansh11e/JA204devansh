package com.hexaware.amazecare.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.entities.Doctor;
import com.hexaware.amazecare.entities.MedicalRecord;
import com.hexaware.amazecare.entities.PrescribeMedication;
import com.hexaware.amazecare.repositories.DoctorRepository;
import com.hexaware.amazecare.repositories.MedicalRecordRepository;
import com.hexaware.amazecare.repositories.PrescribeMedicationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AmazeCareImpl implements IAmazeCareService{
   @Autowired
   DoctorRepository repo1;
   MedicalRecordRepository repo2;
   PrescribeMedicationRepository repo3;
	@Override
	public Doctor registerDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return repo1.save(doctor);
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return repo1.save(doctor);
	}

	@Override
	public Doctor getDoctorById(int did) {
		// TODO Auto-generated method stub
		return repo1.findById(did).orElse(null);
	}

	@Override
	public String deleteDoctorById(int did) {
		// TODO Auto-generated method stub
		repo1.deleteById(did);
		return "Doctor Record Deleted";
	}

	@Override
	public List<Doctor> viewAllDoctors() {
		// TODO Auto-generated method stub
		return repo1.findAll();
	}

	@Override
	public MedicalRecord conductConsultation(MedicalRecord medical) {
		// TODO Auto-generated method stub
		return repo2.save(medical);
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medical) {
		// TODO Auto-generated method stub
		return repo2.save(medical);
	}

	@Override
	public PrescribeMedication prescribeMedications(PrescribeMedication pr) {
		// TODO Auto-generated method stub
		return repo3.save(pr);
	}

	@Override
	public List<MedicalRecord> viewMedicalHistory() {
		// TODO Auto-generated method stub
		return repo2.findAll();
	}

}
