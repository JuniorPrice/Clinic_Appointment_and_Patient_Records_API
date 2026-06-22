package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import java.time.LocalDateTime;

public class DoctorResponse {

    private Long id;
    private String name;
    private String specialty;
    private LocalDateTime workingHoursStart;
    private LocalDateTime workingHoursEnd;
    private LocalDateTime createdAt;

    public DoctorResponse() {
    }

    public DoctorResponse(Long id, String name, String specialty, LocalDateTime workingHoursStart, LocalDateTime workingHoursEnd, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.workingHoursStart = workingHoursStart;
        this.workingHoursEnd = workingHoursEnd;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public LocalDateTime getWorkingHoursStart() {
        return workingHoursStart;
    }

    public void setWorkingHoursStart(LocalDateTime workingHoursStart) {
        this.workingHoursStart = workingHoursStart;
    }

    public LocalDateTime getWorkingHoursEnd() {
        return workingHoursEnd;
    }

    public void setWorkingHoursEnd(LocalDateTime workingHoursEnd) {
        this.workingHoursEnd = workingHoursEnd;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
