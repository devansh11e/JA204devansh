package com.hexaware.amazecare1.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.amazecare1.entities.MedicalRecord;
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer>{
	List<MedicalRecord>  findByDiagnosis(String diagnosis);
}
