package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.rest.mapper.TreeMapper;
import org.rabie.citronix.rest.vm.request.tree.TreeSaveRequestVM;
import org.rabie.citronix.rest.vm.request.tree.TreeUpdateRequestVM;
import org.rabie.citronix.rest.vm.response.TreeResponseVM;
import org.rabie.citronix.service.FieldService;
import org.rabie.citronix.service.TreeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<TreeResponseVM> save(@Valid @RequestBody TreeSaveRequestVM treeSaveRequestVM) {
        Tree tree = new Tree();
        return saveAndUpdateTree(tree, treeSaveRequestVM.getName(), treeSaveRequestVM.getDatePlantation(), treeSaveRequestVM.getFieldId());
    }
    @PutMapping("/update")
    public ResponseEntity<TreeResponseVM> update(@Valid @RequestBody TreeUpdateRequestVM updateRequest) {
        Tree tree = new Tree();
        tree.setId(updateRequest.getId());
        return saveAndUpdateTree(tree,updateRequest.getName(),  updateRequest.getDatePlantation(), updateRequest.getFieldId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        treeService.deleteById(id);
        return ResponseEntity.ok("Tree deleted successfully");
    }


    @GetMapping("/getAll")
    public List<TreeResponseVM> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Tree> trees = treeService.getAll(pageRequest).getContent();
        return trees.stream().map(tree->{
            TreeResponseVM treeResponseVM = treeMapper.toTreeResponse(tree);
            treeResponseVM.setAge(tree.getAge());
            if(tree.getAge()< 3 ) treeResponseVM.setTreeType("young tree");
            else if(tree.getAge() < 10) treeResponseVM.setTreeType("mature tree");
            else treeResponseVM.setTreeType("old tree");
            treeResponseVM.setProductivityMonthly(tree.getProductivity());
            return treeResponseVM;
        }).toList();
    }



    private ResponseEntity<TreeResponseVM> saveAndUpdateTree(Tree tree, String name, LocalDate datePlantation, Long fieldId) {
        tree.setDatePlantation(datePlantation);
        tree.setName(name);
        Field field = fieldService.findById(fieldId);
        if(field == null) throw new FieldsNullException("Field not found");
        tree.setField(field);
        TreeResponseVM treeResponseVM = treeMapper.toTreeResponse(treeService.save(tree));
        return treeToResponseVmOfTree(tree, treeResponseVM);
    }

    private ResponseEntity<TreeResponseVM> treeToResponseVmOfTree(Tree tree, TreeResponseVM treeResponseVM) {
        treeResponseVM.setAge(tree.getAge());
        if(treeResponseVM.getAge()< 3 ) treeResponseVM.setTreeType("young tree");
        else if(treeResponseVM.getAge() < 10) treeResponseVM.setTreeType("mature tree");
        else treeResponseVM.setTreeType("old tree");
        treeResponseVM.setProductivityMonthly(tree.getProductivity()*4);
        return ResponseEntity.ok(treeResponseVM);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TreeResponseVM> get(@PathVariable Long id) {
        Tree tree = treeService.findById(id);
        TreeResponseVM treeResponseVM = treeMapper.toTreeResponse(tree);
        return treeToResponseVmOfTree(tree, treeResponseVM);
    }

}
