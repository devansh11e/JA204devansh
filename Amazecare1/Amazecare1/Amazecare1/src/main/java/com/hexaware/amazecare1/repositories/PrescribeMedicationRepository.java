package com.hexaware.amazecare1.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.amazecare1.entities.PrescribeMedication;
@Repository
public interface PrescribeMedicationRepository extends JpaRepository<PrescribeMedication,Integer>{

}
