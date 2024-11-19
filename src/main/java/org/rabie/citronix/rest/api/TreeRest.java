package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.rest.mapper.TreeMapper;
import org.rabie.citronix.rest.vm.request.tree.TreeSaveRequest;
import org.rabie.citronix.rest.vm.response.TreeResponse;
import org.rabie.citronix.service.FieldService;
import org.rabie.citronix.service.TreeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tree")
public class TreeRest {
    private final TreeService treeService;
    private final TreeMapper treeMapper;
    private final FieldService fieldService;

    public TreeRest(@Qualifier("treeService") TreeService treeService, TreeMapper treeMapper,@Qualifier("fieldService") FieldService fieldService) {
        this.treeService = treeService;
        this.treeMapper = treeMapper;
        this.fieldService = fieldService;
    }

    @PostMapping("/save")
    public ResponseEntity<TreeResponse> save(@Valid @RequestBody TreeSaveRequest treeSaveRequest) {
        Tree tree = new Tree();
        tree.setDatePlantation(treeSaveRequest.getDatePlantation());
        Field field = fieldService.findById(treeSaveRequest.getFieldId());
        if(field == null) throw new FieldsNullException("Field not found");
        tree.setField(field);
        TreeResponse treeResponse = treeMapper.toTreeResponse(treeService.save(tree));
        return ResponseEntity.ok(treeResponse);
    }

}
