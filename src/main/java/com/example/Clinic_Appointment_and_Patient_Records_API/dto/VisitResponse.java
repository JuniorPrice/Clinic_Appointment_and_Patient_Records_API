package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import java.time.LocalDateTime;

public class VisitResponse {

    private Long vId;
    private Long appointmentId;
    private String diagnosis;
    private String prescription;
    private LocalDateTime recordedAt;

    public VisitResponse() {
    }

    public VisitResponse(Long vId, Long appointmentId, String diagnosis, String prescription, LocalDateTime recordedAt) {
        this.vId = vId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.recordedAt = recordedAt;
    }

    public Long getVId() {
        return vId;
    }

    public void setVId(Long vId) {
        this.vId = vId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
