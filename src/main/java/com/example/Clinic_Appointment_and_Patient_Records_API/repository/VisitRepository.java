package com.example.Clinic_Appointment_and_Patient_Records_API.repository;

import com.example.Clinic_Appointment_and_Patient_Records_API.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findByAppointmentAId(Long appointmentId);
}
