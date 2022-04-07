package com.drone.victordaberechinwogu.services;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.entity.LoadedMedication;
import com.drone.victordaberechinwogu.entity.Medication;
import com.drone.victordaberechinwogu.enums.State;
import com.drone.victordaberechinwogu.model.DispatcherRequest;
import com.drone.victordaberechinwogu.model.DispatcherResponse;
import com.drone.victordaberechinwogu.repository.DroneRepository;
import com.drone.victordaberechinwogu.repository.DispatacherRepository;
import com.drone.victordaberechinwogu.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DispatcherServiceImpl implements DispatcherService{

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DispatacherRepository dispatacherRepository;

    @Override
    public DispatcherResponse load(DispatcherRequest dispatcherRequest) {

        DispatcherResponse dispatcherResponse = new DispatcherResponse();

        if(Objects.isNull(dispatcherRequest)){
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("Dispatcher request cannot be empty");
            return dispatcherResponse;
        }

        Drone drone = droneRepository.findById(dispatcherRequest.getDroneId()).orElse(null);

        if(Objects.isNull(drone)){
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("Drone cannot be empty");
            return dispatcherResponse;
        }

        if(!drone.getState().equals(State.IDLE) || drone.getState().equals(State.LOADING)){
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("Drone needs to be idle or in loading state before medications can be loaded into it.");
            return dispatcherResponse;
        }

        if(drone.getBattery() < 25){
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("Drone batter is less than 25%, Please select another drone with higher battery power.");
            return dispatcherResponse;
        }

        dispatcherResponse.setDrone(drone);

        if(dispatcherRequest.getMedicationId().isEmpty()){
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("Medication list cannot be empty");
            return dispatcherResponse;
        }

        // set state to loading
        drone.setState(State.LOADING);
        droneRepository.save(drone);

        List<Medication> medicationList = new ArrayList<>();
        BigDecimal weightSum = BigDecimal.ZERO;

        for(long id : dispatcherRequest.getMedicationId()){
            Medication med = medicationRepository.findById(id).orElse(null);
            if(Objects.nonNull(med)){
                weightSum = weightSum.add(med.getWeight());
                medicationList.add(med);
            }
        }
        dispatcherResponse.setMedications(medicationList);

        if(weightSum.compareTo(drone.getWeight()) == 1){
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("The weight of the drone is less that the total weight of medications, Please use a drone with higher weight.");
            return dispatcherResponse;
        }

        // saving medications with drone
        for(Medication medication : medicationList){
            LoadedMedication loadedMedication = new LoadedMedication();
            loadedMedication.setDrone(drone);
            loadedMedication.setMedication(medication);
            dispatacherRepository.save(loadedMedication);
        }

        // set state as loaded
        drone.setState(State.LOADED);
        droneRepository.save(drone);
        dispatcherResponse.setResponseCode("00");
        dispatcherResponse.setResponseMsg("Success");

        return dispatcherResponse;
    }
}
