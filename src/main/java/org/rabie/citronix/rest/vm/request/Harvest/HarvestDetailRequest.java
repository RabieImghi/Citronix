package org.rabie.citronix.rest.vm.request.Harvest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HarvestDetailRequest {
    private Long treeId;
    private Long harvestId;
}
