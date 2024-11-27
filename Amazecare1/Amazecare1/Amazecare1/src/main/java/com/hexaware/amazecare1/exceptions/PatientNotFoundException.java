package com.hexaware.amazecare1.exceptions;
/*
 * Author=Vinayak
 */
public class PatientNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	// Constructor to accept the message passed when throwing the exception
    public PatientNotFoundException(String message) {
        super(message);
    }
}
