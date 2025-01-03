package org.rabie.citronix.rest.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseVM {
    private Long id;
    private LocalDate saleDate;
    private Double unitPrice;
    private String client;
    private HarvestResponseVM harvest;


}
