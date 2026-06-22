package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScheduleResponse {

    private Long sId;
    private LocalDate slotDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public ScheduleResponse() {
    }

    public ScheduleResponse(Long sId, LocalDate slotDate, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.sId = sId;
        this.slotDate = slotDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Long getSId() {
        return sId;
    }

    public void setSId(Long sId) {
        this.sId = sId;
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
}
