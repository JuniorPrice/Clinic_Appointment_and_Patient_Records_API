package com.example.Clinic_Appointment_and_Patient_Records_API.repository;

import com.example.Clinic_Appointment_and_Patient_Records_API.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findBySlotIdAndStatus(Long slotId, String status);

    List<Appointment> findByPatientIdAndStatusAndSlotDoctorIdAndSlotSlotDate(Long patientId, String status, Long doctorId, LocalDate slotDate);

    List<Appointment> findByPatientIdOrderBySlotSlotDateDesc(Long patientId);

    List<Appointment> findByPatientId(Long patientId);

    Optional<Appointment> findByIdAndStatus(Long id, String status);

    List<Appointment> findBySlotDoctorIdAndStatusAndSlotStartTimeBetween(Long doctorId, String status, LocalDateTime start, LocalDateTime end);
}
