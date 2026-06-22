package com.example.Clinic_Appointment_and_Patient_Records_API.controller;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.AppointmentRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.AppointmentResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.RescheduleRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.VisitRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.VisitResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.service.AppointmentService;
import com.example.Clinic_Appointment_and_Patient_Records_API.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final VisitService visitService;

    public AppointmentController(AppointmentService appointmentService, VisitService visitService) {
        this.appointmentService = appointmentService;
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> bookAppointment(@Valid @RequestBody AppointmentRequest request) {
        return ResponseEntity.status(201).body(appointmentService.bookAppointment(request));
    }

    @PostMapping("/{aId}/cancel")
    public ResponseEntity<AppointmentResponse> cancelAppointment(@PathVariable Long aId) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(aId));
    }

    @PostMapping("/{aId}/reschedule")
    public ResponseEntity<AppointmentResponse> rescheduleAppointment(@PathVariable Long aId, @Valid @RequestBody RescheduleRequest request) {
        return ResponseEntity.ok(appointmentService.rescheduleAppointment(aId, request));
    }

    @PostMapping("/{aId}/visit")
    public ResponseEntity<VisitResponse> recordVisit(@PathVariable Long aId, @Valid @RequestBody VisitRequest request) {
        return ResponseEntity.status(201).body(visitService.recordVisit(aId, request));
    }
}
