package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestSaveRequest;
import org.rabie.citronix.rest.vm.response.HarvestResponse;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestResponse toResponse(Harvest harvest);
    Harvest toHarvestFromSaveRequest(HarvestSaveRequest request);
}
