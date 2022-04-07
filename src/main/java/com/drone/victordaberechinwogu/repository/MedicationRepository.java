package com.drone.victordaberechinwogu.repository;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findById(Long id);

    @Query(value = "SELECT COUNT(*) FROM MEDICATION  WHERE  code =:code", nativeQuery = true)
    int exitsByCode(String code);
}
