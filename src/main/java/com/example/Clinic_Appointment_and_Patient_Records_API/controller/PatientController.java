package com.example.Clinic_Appointment_and_Patient_Records_API.controller;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.HistoryResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.PatientRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.PatientResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(request));
    }

    @GetMapping("/{pId}/history")
    public ResponseEntity<List<HistoryResponse>> getPatientHistory(@PathVariable Long pId) {
        return ResponseEntity.ok(patientService.getPatientHistory(pId));
    }
}
