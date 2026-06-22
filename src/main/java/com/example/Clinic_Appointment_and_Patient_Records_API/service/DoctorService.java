package com.example.Clinic_Appointment_and_Patient_Records_API.service;

import com.example.Clinic_Appointment_and_Patient_Records_API.dto.DoctorRequest;
import com.example.Clinic_Appointment_and_Patient_Records_API.dto.DoctorResponse;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.BusinessRuleViolationException;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.InvalidRequestException;
import com.example.Clinic_Appointment_and_Patient_Records_API.exception.ResourceNotFoundException;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Doctor;
import com.example.Clinic_Appointment_and_Patient_Records_API.model.Slot;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.AppointmentRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.DoctorRepository;
import com.example.Clinic_Appointment_and_Patient_Records_API.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final SlotRepository slotRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository, SlotRepository slotRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.slotRepository = slotRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public DoctorResponse createDoctor(DoctorRequest request) {
        if (!request.getWorkingHoursStart().isBefore(request.getWorkingHoursEnd())) {
            throw new InvalidRequestException("Start time must be before end time");
        }

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setSpecialty(request.getSpecialty());
        doctor.setWorkingHoursStart(request.getWorkingHoursStart());
        doctor.setWorkingHoursEnd(request.getWorkingHoursEnd());

        Doctor savedDoctor = doctorRepository.save(doctor);
        return toResponse(savedDoctor);
    }

    public DoctorResponse updateWorkingHours(Long doctorId, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));

        List<com.example.Clinic_Appointment_and_Patient_Records_API.model.Appointment> bookedBefore =
                appointmentRepository.findBySlotDoctorDIdAndStatusAndSlotStartTimeBetween(
                        doctorId, "BOOKED", LocalDateTime.MIN, request.getWorkingHoursStart());

        List<com.example.Clinic_Appointment_and_Patient_Records_API.model.Appointment> bookedAfter =
                appointmentRepository.findBySlotDoctorDIdAndStatusAndSlotStartTimeBetween(
                        doctorId, "BOOKED", request.getWorkingHoursEnd(), LocalDateTime.MAX);

        if (!bookedBefore.isEmpty() || !bookedAfter.isEmpty()) {
            throw new BusinessRuleViolationException(
                    "Cannot change working hours: doctor has booked appointments outside the new hours");
        }

        List<Slot> slotsBefore = slotRepository.findByDoctorDIdAndStartTimeBetween(
                doctorId, LocalDateTime.MIN, request.getWorkingHoursStart());

        List<Slot> slotsAfter = slotRepository.findByDoctorDIdAndStartTimeBetween(
                doctorId, request.getWorkingHoursEnd(), LocalDateTime.MAX);

        for (Slot slot : slotsBefore) {
            slot.setIsActive("0");
            slotRepository.save(slot);
        }

        for (Slot slot : slotsAfter) {
            slot.setIsActive("0");
            slotRepository.save(slot);
        }

        doctor.setWorkingHoursStart(request.getWorkingHoursStart());
        doctor.setWorkingHoursEnd(request.getWorkingHoursEnd());
        Doctor savedDoctor = doctorRepository.save(doctor);
        return toResponse(savedDoctor);
    }

    private DoctorResponse toResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getDId(),
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getWorkingHoursStart(),
                doctor.getWorkingHoursEnd(),
                doctor.getCreatedAt()
        );
    }
}
