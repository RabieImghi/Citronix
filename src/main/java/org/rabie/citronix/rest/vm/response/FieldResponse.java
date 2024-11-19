package org.rabie.citronix.rest.vm.response;

import jakarta.persistence.ManyToOne;
import org.rabie.citronix.domain.Farm;

public class FieldResponse {
    private Long id;
    private String name;
    private Double area;
    private FarmResponse farmResponse;
}
