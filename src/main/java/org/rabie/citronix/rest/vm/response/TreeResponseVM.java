package org.rabie.citronix.rest.vm.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TreeResponseVM {
    private Long id;
    private LocalDate datePlantation;
    private FieldResponseVM field;
    private Long age;
    private String treeType;
    private Double productivityMonthly;
}
