package com.drone.victordaberechinwogu.controller;

import com.drone.victordaberechinwogu.entity.Medication;
import com.drone.victordaberechinwogu.model.DispatcherResponse;
import com.drone.victordaberechinwogu.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping("/register")
    public DispatcherResponse newMedication(@Valid @RequestBody Medication medication) {
            return medicationService.save(medication);
    }


    @GetMapping("/medications-for-drone/{id}")
    public DispatcherResponse getMedicationsForDrone(@Valid @PathVariable long id){
        return medicationService.getMedications(id);
    }
}
