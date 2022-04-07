package com.drone.victordaberechinwogu.configurations;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.logging.Logger;

@Configuration
@EnableScheduling
@Slf4j
public class Config {

    private final DroneRepository droneRepository;

    public Config(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedDelay = (60000 * 5)) // 5 mins
    public void checkDroneBattery() {
        droneRepository.findAll() .forEach(drone -> outPutResult(drone));
    }

    public void outPutResult(Drone drone) {
        String msg = "Drone SN is : " + drone.getSerialNumber() + "; Drone Battery Leve : " + drone.getBattery() + "%";
        if (drone.getBattery() < 25) {
            log.error(msg);
        } else {
            log.info(msg);
        }
    }
}
