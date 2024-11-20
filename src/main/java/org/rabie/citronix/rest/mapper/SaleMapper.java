package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Sale;
import org.rabie.citronix.rest.vm.response.SaleResponse;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleResponse toSaleResponse(Sale sale);
}
