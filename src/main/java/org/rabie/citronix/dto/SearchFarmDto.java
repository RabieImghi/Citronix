package org.rabie.citronix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchFarmDto {
    private Long id;
    private String name;
    private String location;
    private Double area;
    private LocalDate dateCreation;
}
