package com.example.Clinic_Appointment_and_Patient_Records_API.repository;

import com.example.Clinic_Appointment_and_Patient_Records_API.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findByDoctorIdAndSlotDateAndIsActive(Long doctorId, LocalDate slotDate, String isActive);

    List<Slot> findByDoctorIdAndSlotDate(Long doctorId, LocalDate slotDate);

    Optional<Slot> findByIdAndIsActive(Long id, String isActive);

    List<Slot> findByDoctorIdAndStartTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
}
