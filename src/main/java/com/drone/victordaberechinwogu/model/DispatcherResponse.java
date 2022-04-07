package com.drone.victordaberechinwogu.model;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.entity.Medication;
import lombok.Data;

import java.util.List;


@Data
public class DispatcherResponse {
    private String responseCode;
    private String responseMsg;
    private Drone drone;
    private List<Medication> medications;
}
