package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DoctorRequest {

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Specialty is required")
    private String specialty;

    @NotNull(message = "Working hours start is required")
    private LocalDateTime workingHoursStart;

    @NotNull(message = "Working hours end is required")
    private LocalDateTime workingHoursEnd;

    public DoctorRequest() {
    }

    public DoctorRequest(String name, String specialty, LocalDateTime workingHoursStart, LocalDateTime workingHoursEnd) {
        this.name = name;
        this.specialty = specialty;
        this.workingHoursStart = workingHoursStart;
        this.workingHoursEnd = workingHoursEnd;
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
}
