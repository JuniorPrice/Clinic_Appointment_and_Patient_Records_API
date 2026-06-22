package com.example.Clinic_Appointment_and_Patient_Records_API.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class SlotRequest {

    @NotNull(message = "Slot date is required")
    private LocalDate date;

    public SlotRequest() {
    }

    public SlotRequest(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
