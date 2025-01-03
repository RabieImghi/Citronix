package org.rabie.citronix.rest.vm.request.Harvest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HarvestDetailRequestVM {
    private Long treeId;
    private Long harvestId;
}
