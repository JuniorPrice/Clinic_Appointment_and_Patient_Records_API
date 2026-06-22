package com.example.Clinic_Appointment_and_Patient_Records_API.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
