package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientResponse {

    private Long pId;
    private String name;
    private LocalDate dateOfBirth;
    private String phone;
    private LocalDateTime createdAt;

    public PatientResponse() {
    }

    public PatientResponse(Long pId, String name, LocalDate dateOfBirth, String phone, LocalDateTime createdAt) {
        this.pId = pId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Long getPId() {
        return pId;
    }

    public void setPId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
