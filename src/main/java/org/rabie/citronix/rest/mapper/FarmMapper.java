package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.rest.vm.request.farm.FarmSaveRequest;
import org.rabie.citronix.rest.vm.request.farm.FarmUpdateRequest;
import org.rabie.citronix.rest.vm.response.FarmResponse;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmResponse toFarmResponse(Farm farm);
    Farm toFarmFromSaveRequest(FarmSaveRequest farmSaveRequest);
    Farm toFarmFromUpdateRequest(FarmUpdateRequest farmUpdateRequest);
}
