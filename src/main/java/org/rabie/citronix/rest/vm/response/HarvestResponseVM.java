package org.rabie.citronix.rest.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.citronix.domain.enums.Session;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HarvestResponseVM {
    private Long id;
    private Session session;
    private LocalDate harvestDate;
    private Double totalQuantity;
}
