package com.hexaware.amazecare1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hexaware.amazecare1.exceptions.DoctorNotFoundException;
import com.hexaware.amazecare1.exceptions.InvalidEmailException;
import com.hexaware.amazecare1.exceptions.MedicalRecordNotFoundException;
import com.hexaware.amazecare1.exceptions.PasswordIncorrectException;
import com.hexaware.amazecare1.exceptions.PrescriptionNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // This method handles all NotFoundException thrown in the application
    @ExceptionHandler({ PatientNotFoundException.class })
    @ResponseStatus(code = HttpStatus.NOT_FOUND) // Sets HTTP status to 404
    public String handleNotFoundException(PatientNotFoundException ex) {
        return ex.getMessage();  // Return the message of the exception
    }
    
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<String> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(MedicalRecordNotFoundException.class)
    public ResponseEntity<String> handleMedicalRecordNotFoundException(MedicalRecordNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);}
	
	@ExceptionHandler(PrescriptionNotFoundException.class)
    public ResponseEntity<String> handlePrescriptionNotFoundException(PrescriptionNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(InvalidEmailException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<String> AppointmentNotFoundException(AppointmentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);}
	
	@ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<String> handlePasswordIncorrectException(PasswordIncorrectException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);}
}

