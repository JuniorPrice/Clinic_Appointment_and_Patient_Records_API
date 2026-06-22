package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

public class ErrorResponse {

    private String message;
    private String timestamp;
    private int status;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String timestamp, int status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
