package com.drone.victordaberechinwogu.repository;

import com.drone.victordaberechinwogu.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long> {

     Optional<Drone> findById(Long id);

     @Query(value = "SELECT * FROM DRONE  WHERE  state = 'IDLE' OR state = 'LOADING'", nativeQuery = true)
     List<Drone> availableDrones();

     @Query(value = "SELECT COUNT(*) FROM DRONE  WHERE  serial_number =:serialNumber", nativeQuery = true)
     int exitsBySerialNumber(String serialNumber);

     List<Drone> findAll();
}
