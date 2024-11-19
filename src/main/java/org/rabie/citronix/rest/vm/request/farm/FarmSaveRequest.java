package org.rabie.citronix.rest.vm.request.farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.rabie.citronix.domain.Field;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmSaveRequest {
    @NonNull @NotBlank
    private String name;
    @NonNull @NotBlank
    private String location;
    @NotNull
    private Double area;
    @NotNull
    private LocalDate dateCreation;

    private List<Field> fields;
}
