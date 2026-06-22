package com.example.Clinic_Appointment_and_Patient_Records_API.service;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.AppointmentRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.AppointmentResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.RescheduleRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.BusinessRuleViolationException;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.ResourceNotFoundException;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Appointment;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Patient;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Slot;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.AppointmentRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.PatientRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.SlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final SlotRepository slotRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(SlotRepository slotRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.slotRepository = slotRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public AppointmentResponse bookAppointment(AppointmentRequest request) {
        Slot slot = slotRepository.findByIdAndIsActive(request.getSlotId(), "1")
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found or not available"));

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));

        Optional<Appointment> existingBooking = appointmentRepository.findBySlotIdAndStatus(request.getSlotId(), "BOOKED");
        if (existingBooking.isPresent()) {
            throw new BusinessRuleViolationException("Slot is already booked");
        }

        List<Appointment> sameDayAppointments = appointmentRepository
                .findByPatientIdAndStatusAndSlotDoctorIdAndSlotSlotDate(
                        patient.getId(), "BOOKED", slot.getDoctor().getId(), slot.getSlotDate());
        if (!sameDayAppointments.isEmpty()) {
            throw new BusinessRuleViolationException("Patient already has a booked appointment with this doctor on this date");
        }

        Appointment appointment = new Appointment();
        appointment.setSlot(slot);
        appointment.setPatient(patient);
        appointment.setStatus("BOOKED");
        appointment.setBookedAt(LocalDateTime.now());
        appointment.setCreatedAt(LocalDateTime.now());

        appointment = appointmentRepository.save(appointment);

        return toResponse(appointment);
    }

    @Transactional
    public AppointmentResponse cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointmentId));

        if (!"BOOKED".equals(appointment.getStatus())) {
            throw new BusinessRuleViolationException("Only BOOKED appointments can be cancelled");
        }

        appointment.setStatus("CANCELLED");
        appointment.setCancelledAt(LocalDateTime.now());

        appointment = appointmentRepository.save(appointment);

        return toResponse(appointment);
    }

    @Transactional
    public AppointmentResponse rescheduleAppointment(Long appointmentId, RescheduleRequest request) {
        Appointment oldAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointmentId));

        if (!"BOOKED".equals(oldAppointment.getStatus())) {
            throw new BusinessRuleViolationException("Only BOOKED appointments can be rescheduled");
        }

        Slot newSlot = slotRepository.findByIdAndIsActive(request.getNewSlotId(), "1")
                .orElseThrow(() -> new ResourceNotFoundException("New slot not found or not available"));

        Optional<Appointment> existingBooking = appointmentRepository.findBySlotIdAndStatus(request.getNewSlotId(), "BOOKED");
        if (existingBooking.isPresent()) {
            throw new BusinessRuleViolationException("Slot is already booked");
        }

        List<Appointment> sameDayAppointments = appointmentRepository
                .findByPatientIdAndStatusAndSlotDoctorIdAndSlotSlotDate(
                        oldAppointment.getPatient().getId(), "BOOKED", newSlot.getDoctor().getId(), newSlot.getSlotDate());
        boolean hasConflict = false;
        for (Appointment a : sameDayAppointments) {
            if (!a.getId().equals(oldAppointment.getId())) {
                hasConflict = true;
                break;
            }
        }
        if (hasConflict) {
            throw new BusinessRuleViolationException("Patient already has a booked appointment with this doctor on this date");
        }

        Appointment newAppointment = new Appointment();
        newAppointment.setSlot(newSlot);
        newAppointment.setPatient(oldAppointment.getPatient());
        newAppointment.setStatus("BOOKED");
        newAppointment.setBookedAt(LocalDateTime.now());
        newAppointment.setCreatedAt(LocalDateTime.now());

        newAppointment = appointmentRepository.save(newAppointment);

        oldAppointment.setStatus("RESCHEDULED");
        oldAppointment.setRescheduledTo(newAppointment);

        appointmentRepository.save(oldAppointment);

        return toResponse(newAppointment);
    }

    private AppointmentResponse toResponse(Appointment appointment) {
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setSlotId(appointment.getSlot().getId());
        response.setPatientId(appointment.getPatient().getId());
        response.setPatientName(appointment.getPatient().getName());
        response.setDoctorName(appointment.getSlot().getDoctor().getName());
        response.setSlotDate(appointment.getSlot().getSlotDate());
        response.setStartTime(appointment.getSlot().getStartTime());
        response.setEndTime(appointment.getSlot().getEndTime());
        response.setStatus(appointment.getStatus());
        response.setBookedAt(appointment.getBookedAt());
        response.setCancelledAt(appointment.getCancelledAt());
        response.setCompletedAt(appointment.getCompletedAt());
        response.setRescheduledToId(appointment.getRescheduledTo() != null ? appointment.getRescheduledTo().getId() : null);
        return response;
    }
}
