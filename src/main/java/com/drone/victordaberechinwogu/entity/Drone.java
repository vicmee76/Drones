package com.drone.victordaberechinwogu.entity;

import com.drone.victordaberechinwogu.enums.Model;
import com.drone.victordaberechinwogu.enums.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name="Drone")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotNull(message = "Drone serial number should not be null")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Drone model should not be null")
    private Model model;

    @NotNull(message = "Drone weight should not be null")
    @Min(value = 1, message = "Drone weight should be greater than 1")
    @Max(value = 500, message = "Drone weight should not be more than 500kg")
    private BigDecimal weight;

    @NotNull(message = "Drone weight should not be null")
    @Min(value = 1, message = "Drone battery level should not be 1 or lesser")
    @Max(value = 100, message = "Drone battery level should not be more than 100%")
    private int battery;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Drone state should not be null")
    private State state;
}
