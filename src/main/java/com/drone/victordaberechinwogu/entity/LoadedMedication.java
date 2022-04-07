package com.drone.victordaberechinwogu.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name="LoadedMedication")
    @Entity
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoadedMedication {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "drone_id")
        @NotNull(message = "Drone Id is required")
        private Drone drone;

        @OneToOne
        @JoinColumn(name = "medication_id")
        @NotNull(message = "All medication Ids are required")
        private Medication medication;
    }


