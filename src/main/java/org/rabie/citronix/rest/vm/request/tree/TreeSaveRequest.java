package org.rabie.citronix.rest.vm.request.tree;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TreeSaveRequest {
    @NotNull
    private LocalDate datePlantation;
    @NotNull
    private Long fieldId;
}
