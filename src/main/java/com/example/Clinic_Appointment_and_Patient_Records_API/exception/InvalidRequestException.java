package com.example.Clinic_Appointment_and_Patient_Records_API.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
