package com.drone.victordaberechinwogu.services;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.model.DispatcherResponse;
import com.drone.victordaberechinwogu.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public DispatcherResponse save(Drone drone) {
        DispatcherResponse dispatcherResponse = new DispatcherResponse();
        int drones = droneRepository.exitsBySerialNumber(drone.getSerialNumber());
        if(drones > 0) {
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("Serial Number already exits");
        }
        else{
            Drone savedDrone = droneRepository.save(drone);
            dispatcherResponse.setResponseCode("00");
            dispatcherResponse.setResponseMsg("Success");
            dispatcherResponse.setDrone(savedDrone);
        }
        return dispatcherResponse;
    }


    @Override
    public List<Drone> DronesForLoading() {
        return droneRepository.availableDrones();
    }


    @Override
    public DispatcherResponse DroneBatteryLevel(long id) {
        DispatcherResponse dispatcherResponse = new DispatcherResponse();
        Drone drone = droneRepository.findById(id).orElse(null);
        if(Objects.isNull(drone)){
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("No drone found with this id");
        }
        else{
            dispatcherResponse.setResponseCode("00");
            dispatcherResponse.setResponseMsg("Success with drone batter");
            dispatcherResponse.setDrone(drone);
        }
        return dispatcherResponse;
    }

}
