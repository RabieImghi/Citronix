package org.rabie.citronix.rest.vm.request.farm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmUpdateRequest {
    @NotNull
    private Long id;
    @NonNull
    @NotBlank
    private String name;
    @NonNull @NotBlank
    private String location;
    @NotNull
    private Double area;
    @NotNull
    private LocalDate dateCreation;
}
