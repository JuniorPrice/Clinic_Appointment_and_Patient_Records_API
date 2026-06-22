package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import jakarta.validation.constraints.NotBlank;

public class VisitRequest {

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    private String prescription;

    public VisitRequest() {
    }

    public VisitRequest(String diagnosis, String prescription) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}
