package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.rest.vm.request.tree.TreeSaveRequest;
import org.rabie.citronix.rest.vm.response.TreeResponse;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    TreeResponse toTreeResponse(Tree tree);
}
