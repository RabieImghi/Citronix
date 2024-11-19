package org.rabie.citronix.rest.vm.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.citronix.domain.Field;

import java.time.LocalDate;

@Getter
@Setter
public class TreeResponse {
    private Long id;
    private LocalDate datePlantation;
    private FieldResponse field;
    private Long age;
    private String treeType;
    private Double productivityMonthly;
}
