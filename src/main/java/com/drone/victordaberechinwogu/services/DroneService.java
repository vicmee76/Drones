package com.drone.victordaberechinwogu.services;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.model.DispatcherResponse;

import java.util.List;

public interface DroneService {

    DispatcherResponse save(Drone drone);

    List<Drone> DronesForLoading();

    DispatcherResponse DroneBatteryLevel(long id);
}
