package com.example.Clinic_Appointment_and_Patient_Records_API.repository;

import com.example.Clinic_Appointment_and_Patient_Records_API.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
