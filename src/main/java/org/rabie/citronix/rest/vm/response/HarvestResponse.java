package org.rabie.citronix.rest.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.citronix.domain.enums.Session;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HarvestResponse {
    private Long id;
    private String session;
    private LocalDate harvestDate;
    private Double totalQuantity;
}
