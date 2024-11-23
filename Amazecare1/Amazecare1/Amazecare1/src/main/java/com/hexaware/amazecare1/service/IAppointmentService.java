package com.hexaware.amazecare1.service;

import java.util.List;

import com.hexaware.amazecare1.entities.Appointment;
import com.hexaware.amazecare1.exceptions.AppointmentNotFoundException;

public interface IAppointmentService {
	  Appointment scheduleAppointment(int patientId, Appointment appointment);
	    List<Appointment> findByAppointmentId(int appointmentId) throws AppointmentNotFoundException;
	    int cancelAppointment(int appointmentId) throws AppointmentNotFoundException;
}
