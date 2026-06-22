package com.example.Clinic_Appointment_and_Patient_Records_API.controller;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.DoctorRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.DoctorResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.ScheduleResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.SlotRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.service.DoctorService;
import com.example.Clinic_Appointment_and_Patient_Records_API.service.SlotService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final SlotService slotService;

    public DoctorController(DoctorService doctorService, SlotService slotService) {
        this.doctorService = doctorService;
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody DoctorRequest request) {
        return ResponseEntity.status(201).body(doctorService.createDoctor(request));
    }

    @PostMapping("/{dId}/slots")
    public ResponseEntity<List<ScheduleResponse>> generateSlots(@PathVariable Long dId, @Valid @RequestBody SlotRequest request) {
        return ResponseEntity.status(201).body(slotService.generateSlots(dId, request));
    }

    @GetMapping("/{dId}/schedule")
    public ResponseEntity<List<ScheduleResponse>> getSchedule(@PathVariable Long dId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(slotService.getSchedule(dId, date));
    }
}
