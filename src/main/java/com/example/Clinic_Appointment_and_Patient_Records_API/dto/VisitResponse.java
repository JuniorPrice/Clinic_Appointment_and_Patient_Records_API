package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import java.time.LocalDateTime;

public class VisitResponse {

    private Long id;
    private Long appointmentId;
    private String diagnosis;
    private String prescription;
    private LocalDateTime recordedAt;

    public VisitResponse() {
    }

    public VisitResponse(Long id, Long appointmentId, String diagnosis, String prescription, LocalDateTime recordedAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.recordedAt = recordedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
