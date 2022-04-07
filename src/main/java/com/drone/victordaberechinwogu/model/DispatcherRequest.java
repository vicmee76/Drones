package com.drone.victordaberechinwogu.model;

import lombok.Data;

import java.util.List;

@Data
public class DispatcherRequest {
    private Long droneId;
    private List<Long> medicationId;
}
