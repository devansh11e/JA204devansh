package com.hexaware.amazecare1.service;
/*
 * Author=Vinayak
 */
import java.util.List;

import com.hexaware.amazecare1.dto.AppointmentDTO;
import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;
import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.PatientNotFoundException;

public interface IAppointmentService {
	
	//Scheduling
	  Appointment scheduleAppointment( AppointmentDTO appointmentDTO) throws PatientNotFoundException,DoctorNotFoundException;
	  //Finding by Patient ID 
	  List<Appointment> findAppointmentByPatientId(Integer patientId) throws PatientNotFoundException;
	  //Finding by Doctor ID 
	  List<Appointment> findAppointmentByDoctorId(Integer doctorId) throws DoctorNotFoundException;
	  //Cancelling appointment 
	  int cancelAppointment(int appointmentId) throws AppointmentNotFoundException;
}
