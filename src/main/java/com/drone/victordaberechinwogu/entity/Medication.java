package com.drone.victordaberechinwogu.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Table(name="Medication")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Medication name should not be null")
    @Pattern(regexp = "^[A-Z0-9]+[-_]*[a-z0-9]*$", message = "Allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;

    @NotNull(message = "Medication weight should not be null")
    @Min(value = 1, message = "Medication weight should not be 1 or lesser")
    @Max(value = 500, message = "Medication weight should not be more than 500kg")
    private BigDecimal weight;

    @NotNull(message = "Medication code should not be null")
    @Pattern(regexp = "[A-Z0-9]+[-_]*", message = "Allowed only upper case letters, underscore and numbers")
    private String code;

    @NotNull(message = "Medication image url should not be null")
    private String image;
}
