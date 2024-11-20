package org.rabie.citronix.rest.vm.request.Harvest;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.citronix.domain.enums.Session;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HarvestSaveRequest {
    @NotNull
    private LocalDate harvestDate;
    private Double totalQuantity = 0.;
    private Long fieldId = null;
}
