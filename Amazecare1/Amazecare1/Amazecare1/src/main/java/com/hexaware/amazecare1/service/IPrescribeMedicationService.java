package com.hexaware.amazecare1.service;

import java.util.List;

import com.hexaware.amazecare1.entities.PrescribeMedication;
import com.hexaware.amazecare1.exceptions.PrescriptionNotFoundException;

public interface IPrescribeMedicationService {
	PrescribeMedication prescribeMedications(PrescribeMedication pr);
    PrescribeMedication getPrescriptionById(int pid) throws PrescriptionNotFoundException;
    List<PrescribeMedication> viewAllPrescriptions();
}
