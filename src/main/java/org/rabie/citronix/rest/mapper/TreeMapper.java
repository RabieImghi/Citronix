package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.rest.vm.response.TreeResponseVM;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    TreeResponseVM toTreeResponse(Tree tree);
}
