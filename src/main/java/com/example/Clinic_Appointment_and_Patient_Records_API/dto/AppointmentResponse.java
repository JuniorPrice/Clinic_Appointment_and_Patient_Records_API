package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentResponse {

    private Long aId;
    private Long slotId;
    private Long patientId;
    private String patientName;
    private String doctorName;
    private LocalDate slotDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private LocalDateTime bookedAt;
    private LocalDateTime cancelledAt;
    private LocalDateTime completedAt;
    private Long rescheduledToAId;

    public AppointmentResponse() {
    }

    public AppointmentResponse(Long aId, Long slotId, Long patientId, String patientName, String doctorName, LocalDate slotDate, LocalDateTime startTime, LocalDateTime endTime, String status, LocalDateTime bookedAt, LocalDateTime cancelledAt, LocalDateTime completedAt, Long rescheduledToAId) {
        this.aId = aId;
        this.slotId = slotId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.slotDate = slotDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.bookedAt = bookedAt;
        this.cancelledAt = cancelledAt;
        this.completedAt = completedAt;
        this.rescheduledToAId = rescheduledToAId;
    }

    public Long getAId() {
        return aId;
    }

    public void setAId(Long aId) {
        this.aId = aId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getRescheduledToAId() {
        return rescheduledToAId;
    }

    public void setRescheduledToAId(Long rescheduledToAId) {
        this.rescheduledToAId = rescheduledToAId;
    }
}
