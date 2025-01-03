package org.rabie.citronix.rest.vm.request.field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldSaveRequestVM {
    @NonNull @NotBlank
    private String name;
    @NotNull
    private Double area;
    @NotNull
    private Long farmId;
}