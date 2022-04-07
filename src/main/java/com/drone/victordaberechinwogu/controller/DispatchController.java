package com.drone.victordaberechinwogu.controller;

import com.drone.victordaberechinwogu.model.DispatcherRequest;
import com.drone.victordaberechinwogu.model.DispatcherResponse;
import com.drone.victordaberechinwogu.services.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/dispatcher")
public class DispatchController {

    @Autowired
    private DispatcherService dispatcherService;

    @PostMapping("/load-with-medication")
    public DispatcherResponse LoadMedication(@Valid @RequestBody DispatcherRequest dispatcherRequest) {
        return  dispatcherService.load(dispatcherRequest);
    }

}
