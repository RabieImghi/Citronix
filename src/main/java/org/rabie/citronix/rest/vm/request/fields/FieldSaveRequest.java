package org.rabie.citronix.rest.vm.request.fields;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.rabie.citronix.rest.vm.response.FarmResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldSaveRequest {
    @NonNull @NotBlank
    private String name;
    @NotNull
    private Double area;
    @NotNull
    private Long farmId;
}
