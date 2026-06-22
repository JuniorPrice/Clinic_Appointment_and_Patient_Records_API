package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import jakarta.validation.constraints.NotNull;

public class AppointmentRequest {

    @NotNull
    private Long slotId;

    @NotNull
    private Long patientId;

    public AppointmentRequest() {
    }

    public AppointmentRequest(Long slotId, Long patientId) {
        this.slotId = slotId;
        this.patientId = patientId;
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
}
