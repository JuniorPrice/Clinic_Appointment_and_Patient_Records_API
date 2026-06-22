package com.example.Clinic_Appointment_and_Patient_Records_API.service;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.HistoryResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.PatientRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.PatientResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.ResourceNotFoundException;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Appointment;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Patient;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Slot;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Visit;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.AppointmentRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.PatientRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final VisitRepository visitRepository;

    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository, VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.visitRepository = visitRepository;
    }

    public PatientResponse createPatient(PatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setPhone(request.getPhone());
        Patient savedPatient = patientRepository.save(patient);
        return toResponse(savedPatient);
    }

    public List<HistoryResponse> getPatientHistory(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));

        List<Appointment> appointments = appointmentRepository.findByPatientIdOrderBySlotSlotDateDesc(patientId);
        List<HistoryResponse> historyResponses = new ArrayList<>();

        for (Appointment appointment : appointments) {
            Slot slot = appointment.getSlot();
            Optional<Visit> visit = visitRepository.findByAppointmentId(appointment.getId());

            HistoryResponse response = new HistoryResponse();
            response.setAppointmentId(appointment.getId());
            response.setSlotDate(slot.getSlotDate());
            response.setStartTime(slot.getStartTime());
            response.setEndTime(slot.getEndTime());
            response.setDoctorName(slot.getDoctor().getName());
            response.setDoctorSpecialty(slot.getDoctor().getSpecialty());
            response.setStatus(appointment.getStatus());
            response.setDiagnosis(visit.map(Visit::getDiagnosis).orElse(null));
            response.setPrescription(visit.map(Visit::getPrescription).orElse(null));
            response.setBookedAt(appointment.getBookedAt());
            response.setCancelledAt(appointment.getCancelledAt());
            response.setCompletedAt(appointment.getCompletedAt());

            historyResponses.add(response);
        }

        return historyResponses;
    }

    private PatientResponse toResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setDateOfBirth(patient.getDateOfBirth());
        response.setPhone(patient.getPhone());
        response.setCreatedAt(patient.getCreatedAt());
        return response;
    }
}
