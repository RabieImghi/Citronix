package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.rest.vm.response.HarvestDetailResponse;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {
    HarvestDetailResponse toHarvestDetailResponse(HarvestDetail harvestDetail);
}
