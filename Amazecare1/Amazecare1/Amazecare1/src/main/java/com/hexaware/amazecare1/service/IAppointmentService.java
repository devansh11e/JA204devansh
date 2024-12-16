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
	  AppointmentDTO findAppointmentById(Integer appointmentId) throws AppointmentNotFoundException;
	  //Cancelling appointment 
	  int cancelAppointment(int appointmentId) throws AppointmentNotFoundException;
	  
	  List<AppointmentDTO> viewAppointments();
	  
	  List<AppointmentDTO> findAppointmentByDoctor_DoctorId(Integer doctorId) throws DoctorNotFoundException;
	  
	  String updateAppointment(int appointmentId,AppointmentDTO appointmentDTO)  throws PatientNotFoundException,DoctorNotFoundException, AppointmentNotFoundException;
}
