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
public class SaleUpdateRequestVM {
    @NotNull
    private Long id;
    @NotNull
    private LocalDate saleDate;
    @NotNull
    private Double unitPrice;
    @NotNull
    private Double quantity;
    @NotNull
    private String client;
    @NotNull
    private Long harvestId;
}
