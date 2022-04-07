package com.drone.victordaberechinwogu.services;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.model.DispatcherRequest;
import com.drone.victordaberechinwogu.model.DispatcherResponse;

public interface DispatcherService {
    public DispatcherResponse load(DispatcherRequest dispatcherRequest);
}
