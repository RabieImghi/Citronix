package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.rest.vm.request.farm.FarmSaveRequestVM;
import org.rabie.citronix.rest.vm.request.farm.FarmUpdateRequestVM;
import org.rabie.citronix.rest.vm.response.FarmResponseVm;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmResponseVm toFarmResponse(Farm farm);
    Farm toFarmFromSaveRequest(FarmSaveRequestVM farmSaveRequestVM);
    Farm toFarmFromUpdateRequest(FarmUpdateRequestVM farmUpdateRequestVM);
}
