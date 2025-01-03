package org.rabie.citronix.rest.vm.request.tree;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TreeSaveRequestVM {
    @NotNull
    private String name;
    @NotNull
    private LocalDate datePlantation;
    @NotNull
    private Long fieldId;
}
