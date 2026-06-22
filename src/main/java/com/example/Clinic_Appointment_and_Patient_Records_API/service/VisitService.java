package com.example.Clinic_Appointment_and_Patient_Records_API.service;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.VisitRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.VisitResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.BusinessRuleViolationException;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.ResourceNotFoundException;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Appointment;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Visit;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.AppointmentRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class VisitService {

    private final AppointmentRepository appointmentRepository;
    private final VisitRepository visitRepository;

    public VisitService(AppointmentRepository appointmentRepository, VisitRepository visitRepository) {
        this.appointmentRepository = appointmentRepository;
        this.visitRepository = visitRepository;
    }

    public VisitResponse recordVisit(Long appointmentId, VisitRequest request) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointmentId));

        if (!"BOOKED".equals(appointment.getStatus())) {
            throw new BusinessRuleViolationException("Visit can only be recorded for booked appointments");
        }

        if (!appointment.getSlot().getEndTime().isBefore(LocalDateTime.now())) {
            throw new BusinessRuleViolationException("Cannot record visit: appointment slot time has not yet passed");
        }

        Visit visit = new Visit();
        visit.setAppointment(appointment);
        visit.setDiagnosis(request.getDiagnosis());
        visit.setPrescription(request.getPrescription());
        visit.setRecordedAt(LocalDateTime.now());
        visit.setCreatedAt(LocalDateTime.now());

        visit = visitRepository.save(visit);

        appointment.setStatus("COMPLETED");
        appointment.setCompletedAt(LocalDateTime.now());
        appointmentRepository.save(appointment);

        return toResponse(visit);
    }

    private VisitResponse toResponse(Visit visit) {
        return new VisitResponse(
                visit.getId(),
                visit.getAppointment().getId(),
                visit.getDiagnosis(),
                visit.getPrescription(),
                visit.getRecordedAt()
        );
    }
}
