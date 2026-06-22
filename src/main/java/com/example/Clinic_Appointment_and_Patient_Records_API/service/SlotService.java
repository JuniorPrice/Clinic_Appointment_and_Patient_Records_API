package com.example.Clinic_Appointment_and_Patient_Records_API.service;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.ScheduleResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.SlotRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.InvalidRequestException;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.ResourceNotFoundException;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Doctor;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Slot;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.AppointmentRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.DoctorRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SlotService {

    private final DoctorRepository doctorRepository;
    private final SlotRepository slotRepository;
    private final AppointmentRepository appointmentRepository;

    public SlotService(DoctorRepository doctorRepository, SlotRepository slotRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.slotRepository = slotRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<ScheduleResponse> generateSlots(Long doctorId, SlotRequest request) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));

        if (!request.getDate().isAfter(LocalDate.now())) {
            throw new InvalidRequestException("Slot date must be in the future");
        }

        LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), doctor.getWorkingHoursStart().toLocalTime());
        LocalDateTime endDateTime = LocalDateTime.of(request.getDate(), doctor.getWorkingHoursEnd().toLocalTime());

        List<Slot> existingSlots = slotRepository.findByDoctorDIdAndSlotDate(doctorId, request.getDate());
        Set<LocalDateTime> existingStartTimes = existingSlots.stream()
                .map(Slot::getStartTime)
                .collect(Collectors.toSet());

        LocalDateTime current = startDateTime;
        while (current.isBefore(endDateTime)) {
            LocalDateTime slotEnd = current.plusMinutes(30);

            if (!existingStartTimes.contains(current)) {
                Slot slot = new Slot();
                slot.setDoctor(doctor);
                slot.setSlotDate(request.getDate());
                slot.setStartTime(current);
                slot.setEndTime(slotEnd);
                slot.setIsActive("1");
                slotRepository.save(slot);
            }

            current = slotEnd;
        }

        return getSchedule(doctorId, request.getDate());
    }

    public List<ScheduleResponse> getSchedule(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));

        List<Slot> slots = slotRepository.findByDoctorDIdAndSlotDateAndIsActive(doctorId, date, "1");

        return slots.stream()
                .map(slot -> {
                    String status = appointmentRepository
                            .findBySlotSIdAndStatus(slot.getSId(), "BOOKED")
                            .map(appointment -> "BOOKED")
                            .orElse("FREE");
                    return toScheduleResponse(slot, status);
                })
                .collect(Collectors.toList());
    }

    private ScheduleResponse toScheduleResponse(Slot slot, String status) {
        return new ScheduleResponse(
                slot.getSId(),
                slot.getSlotDate(),
                slot.getStartTime(),
                slot.getEndTime(),
                status
        );
    }
}
