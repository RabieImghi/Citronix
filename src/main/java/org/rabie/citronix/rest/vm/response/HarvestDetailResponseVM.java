package org.rabie.citronix.rest.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HarvestDetailResponseVM {
    private Long id;
    private HarvestResponseVM harvest;
    private TreeResponseVM tree;
}
