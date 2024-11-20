package org.rabie.citronix.rest.vm.request.sale;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleSaveRequest {
    @NotNull
    private LocalDate saleDate;
    @NotNull
    private Double unitPrice;
    @NotNull
    private String client;
    @NotNull
    private Long harvestId;
}
