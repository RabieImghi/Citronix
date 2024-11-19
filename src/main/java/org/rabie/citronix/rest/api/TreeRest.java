package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.rest.mapper.TreeMapper;
import org.rabie.citronix.rest.vm.request.tree.TreeSaveRequest;
import org.rabie.citronix.rest.vm.request.tree.TreeUpdateRequest;
import org.rabie.citronix.rest.vm.response.TreeResponse;
import org.rabie.citronix.service.FieldService;
import org.rabie.citronix.service.TreeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        return saveAndUpdateTree(tree, treeSaveRequest.getDatePlantation(), treeSaveRequest.getFieldId());
    }
    @PutMapping("/update")
    public ResponseEntity<TreeResponse> update(@Valid @RequestBody TreeUpdateRequest updateRequest) {
        Tree tree = new Tree();
        tree.setId(updateRequest.getId());
        return saveAndUpdateTree(tree, updateRequest.getDatePlantation(), updateRequest.getFieldId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        treeService.deleteById(id);
        return ResponseEntity.ok("Tree deleted successfully");
    }


    @GetMapping("/getAll")
    public Page<TreeResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Tree> trees = treeService.getAll(pageRequest);
        return trees.map(treeMapper::toTreeResponse).map(tree -> {
            tree.setAge(tree.getAge());
            if(tree.getAge()< 3 ) tree.setTreeType("young tree");
            else if(tree.getAge() < 10) tree.setTreeType("mature tree");
            else tree.setTreeType("old tree");
            return tree;
        });
    }



    private ResponseEntity<TreeResponse> saveAndUpdateTree(Tree tree, LocalDate datePlantation, Long fieldId) {
        tree.setDatePlantation(datePlantation);
        Field field = fieldService.findById(fieldId);
        if(field == null) throw new FieldsNullException("Field not found");
        tree.setField(field);
        TreeResponse treeResponse = treeMapper.toTreeResponse(treeService.save(tree));
        treeResponse.setAge(tree.getAge());
        if(treeResponse.getAge()< 3 ) treeResponse.setTreeType("young tree");
        else if(treeResponse.getAge() < 10) treeResponse.setTreeType("mature tree");
        else treeResponse.setTreeType("old tree");
        return ResponseEntity.ok(treeResponse);
    }

}
