package com.drone.victordaberechinwogu.repository;
import com.drone.victordaberechinwogu.entity.LoadedMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DispatacherRepository extends JpaRepository<LoadedMedication, Long> {

    @Query(value = "SELECT medication_id FROM LOADED_MEDICATION  WHERE drone_id = :id", nativeQuery = true)
    List<Integer> findAllMedicationsByDroneById(long id);
}
