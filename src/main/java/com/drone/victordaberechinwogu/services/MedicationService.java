package com.drone.victordaberechinwogu.services;

import com.drone.victordaberechinwogu.entity.Medication;
import com.drone.victordaberechinwogu.model.DispatcherResponse;

public interface MedicationService {

    DispatcherResponse save(Medication medication);

    DispatcherResponse getMedications(long id);
}
