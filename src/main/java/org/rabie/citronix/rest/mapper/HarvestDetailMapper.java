package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.rest.vm.response.HarvestDetailResponseVM;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {
    HarvestDetailResponseVM toHarvestDetailResponse(HarvestDetail harvestDetail);
}
