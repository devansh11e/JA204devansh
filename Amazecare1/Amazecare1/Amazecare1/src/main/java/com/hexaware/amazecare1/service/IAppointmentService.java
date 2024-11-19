package com.hexaware.amazecare1.service;

import java.util.List;

import com.hexaware.amazecare1.entities.Appointment;

public interface IAppointmentService {
	  Appointment scheduleAppointment(int patientId, Appointment appointment);
	    List<Appointment> findByAppointmentId(int appointmentId);
	    int cancelAppointment(int appointmentId);
}
