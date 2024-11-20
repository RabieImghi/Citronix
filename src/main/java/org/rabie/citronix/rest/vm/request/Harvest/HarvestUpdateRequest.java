package org.rabie.citronix.rest.vm.request.Harvest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HarvestUpdateRequest {
    @NotNull
    private Long id;
    @NotNull
    private LocalDate harvestDate;
    private Double totalQuantity = 0.;
    private Long fieldId = null;
}
