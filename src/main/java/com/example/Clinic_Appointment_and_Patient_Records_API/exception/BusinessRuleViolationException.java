package com.example.Clinic_Appointment_and_Patient_Records_API.exception;

public class BusinessRuleViolationException extends RuntimeException {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
