package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistoryResponse {

    private Long appointmentId;
    private LocalDate slotDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String doctorName;
    private String doctorSpecialty;
    private String status;
    private String diagnosis;
    private String prescription;
    private LocalDateTime bookedAt;
    private LocalDateTime cancelledAt;
    private LocalDateTime completedAt;

    public HistoryResponse() {
    }

    public HistoryResponse(Long appointmentId, LocalDate slotDate, LocalDateTime startTime, LocalDateTime endTime, String doctorName, String doctorSpecialty, String status, String diagnosis, String prescription, LocalDateTime bookedAt, LocalDateTime cancelledAt, LocalDateTime completedAt) {
        this.appointmentId = appointmentId;
        this.slotDate = slotDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorName = doctorName;
        this.doctorSpecialty = doctorSpecialty;
        this.status = status;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.bookedAt = bookedAt;
        this.cancelledAt = cancelledAt;
        this.completedAt = completedAt;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(LocalDate slotDate) {
        this.slotDate = slotDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }

    public void setDoctorSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
