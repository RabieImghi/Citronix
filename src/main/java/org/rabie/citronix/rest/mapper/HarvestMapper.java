package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestSaveRequestVM;
import org.rabie.citronix.rest.vm.response.HarvestResponseVM;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestResponseVM toResponse(Harvest harvest);
    Harvest toHarvestFromSaveRequest(HarvestSaveRequestVM request);
}
