package com.drone.victordaberechinwogu.controller;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.model.DispatcherResponse;
import com.drone.victordaberechinwogu.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/drone")

public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/register")
    public DispatcherResponse newDrone(@Valid  @RequestBody Drone drone) {
        return droneService.save(drone);
    }


    @GetMapping("/drones-for-loading")
    public List<Drone> getDronesForLoading(){
        return droneService.DronesForLoading();
    }


    @GetMapping("/drone-battery-level/{id}")
    public DispatcherResponse getBatteryLevel(@Valid @PathVariable long id){
        return droneService.DroneBatteryLevel(id);
    }
}
