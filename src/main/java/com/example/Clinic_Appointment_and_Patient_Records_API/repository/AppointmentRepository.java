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

    Optional<Appointment> findBySlotSIdAndStatus(Long slotId, String status);

    List<Appointment> findByPatientPIdAndStatusAndSlotDoctorDIdAndSlotSlotDate(Long patientId, String status, Long doctorId, LocalDate slotDate);

    List<Appointment> findByPatientPIdOrderBySlotSlotDateDesc(Long patientId);

    List<Appointment> findByPatientPId(Long patientId);

    Optional<Appointment> findByAIdAndStatus(Long aId, String status);

    List<Appointment> findBySlotDoctorDIdAndStatusAndSlotStartTimeBetween(Long doctorId, String status, LocalDateTime start, LocalDateTime end);
}
