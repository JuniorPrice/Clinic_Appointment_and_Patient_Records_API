package com.example.Clinic_Appointment_and_Patient_Records_API.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @SequenceGenerator(name = "doctor_seq", sequenceName = "DOCTORS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @Column(name = "d_id")
    private Long dId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialty;

    @Column(name = "working_hours_start", nullable = false)
    private LocalDateTime workingHoursStart;

    @Column(name = "working_hours_end", nullable = false)
    private LocalDateTime workingHoursEnd;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Doctor() {
    }

    public Doctor(Long dId, String name, String specialty, LocalDateTime workingHoursStart, LocalDateTime workingHoursEnd, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.dId = dId;
        this.name = name;
        this.specialty = specialty;
        this.workingHoursStart = workingHoursStart;
        this.workingHoursEnd = workingHoursEnd;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getDId() {
        return dId;
    }

    public void setDId(Long dId) {
        this.dId = dId;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
