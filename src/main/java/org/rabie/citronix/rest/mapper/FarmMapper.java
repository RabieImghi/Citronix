package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.rest.vm.request.FarmSaveRequest;
import org.rabie.citronix.rest.vm.response.FarmResponse;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmResponse toFarmResponse(Farm farm);
    Farm toFarm(FarmSaveRequest farmSaveRequest);
}
