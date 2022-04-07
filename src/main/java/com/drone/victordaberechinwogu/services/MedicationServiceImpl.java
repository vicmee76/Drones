package com.drone.victordaberechinwogu.services;

import com.drone.victordaberechinwogu.entity.Drone;
import com.drone.victordaberechinwogu.entity.LoadedMedication;
import com.drone.victordaberechinwogu.entity.Medication;
import com.drone.victordaberechinwogu.model.DispatcherResponse;
import com.drone.victordaberechinwogu.repository.DroneRepository;
import com.drone.victordaberechinwogu.repository.DispatacherRepository;
import com.drone.victordaberechinwogu.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final DispatacherRepository dispatacherRepository;
    private final DroneRepository droneRepository;

    @Override
    public DispatcherResponse save(Medication medication) {
        DispatcherResponse dispatcherResponse = new DispatcherResponse();
        int medsCount = medicationRepository.exitsByCode(medication.getCode());

        if(medsCount > 0) {
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("Medication code already exits");
        }
        else{
            Medication saveMeds = medicationRepository.save(medication);
            dispatcherResponse.setResponseCode("00");
            dispatcherResponse.setResponseMsg("Success");
            dispatcherResponse.setMedications(Arrays.asList(saveMeds));
        }
        return dispatcherResponse;
    }

    @Override
    public DispatcherResponse getMedications(long id) {
        DispatcherResponse dispatcherResponse = new DispatcherResponse();
        List<Medication> medicationList = new ArrayList<>();
        Drone drone = droneRepository.findById(id).orElse(null);

        if (Objects.isNull(drone)) {
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("No drone was found for this ID");
            return dispatcherResponse;
        }

        List<Integer> loadedMedications = dispatacherRepository.findAllMedicationsByDroneById(id);
        if (loadedMedications.isEmpty()) {
            dispatcherResponse.setResponseCode("01");
            dispatcherResponse.setResponseMsg("No medication was found for this drone");
            return dispatcherResponse;
        }

        for (int meds : loadedMedications) {
            Medication medication = medicationRepository.findById((long) meds).orElse(null);
            if (!Objects.isNull(medication)) {
                medicationList.add(medication);
            }
        }
        dispatcherResponse.setResponseCode("00");
        dispatcherResponse.setResponseMsg("Success");
        dispatcherResponse.setDrone(drone);
        dispatcherResponse.setMedications(medicationList);

        return dispatcherResponse;
    }
}
