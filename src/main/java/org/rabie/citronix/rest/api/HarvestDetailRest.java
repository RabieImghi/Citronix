package org.rabie.citronix.rest.api;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.HarvestDetailAlreadyExistException;
import org.rabie.citronix.rest.mapper.HarvestDetailMapper;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestDetailRequestVM;
import org.rabie.citronix.rest.vm.response.HarvestDetailResponseVM;
import org.rabie.citronix.service.HarvestDetailService;
import org.rabie.citronix.service.impl.HarvestServiceImpl;
import org.rabie.citronix.service.impl.TreeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvest-detail")
public class HarvestDetailRest {
    private final HarvestDetailService harvestDetailService;
    private final HarvestDetailMapper harvestDetailMapper;
    private final HarvestServiceImpl harvestService;
    private final TreeServiceImpl treeService;
    public HarvestDetailRest(HarvestDetailService harvestDetailService, HarvestDetailMapper harvestDetailMapper, HarvestServiceImpl harvestService, TreeServiceImpl treeService) {
        this.harvestDetailService = harvestDetailService;
        this.harvestDetailMapper = harvestDetailMapper;
        this.harvestService = harvestService;
        this.treeService = treeService;
    }

    @PostMapping("/save")
    public ResponseEntity<HarvestDetailResponseVM> save(@RequestBody HarvestDetailRequestVM harvestDetailRequestVM) {
        Harvest harvest = harvestService.findById(harvestDetailRequestVM.getHarvestId());
        Tree tree = treeService.getById(harvestDetailRequestVM.getTreeId());
        if(harvestDetailService.existsByHarvestAndTree(harvest.getId(), tree.getId()))
            throw new HarvestDetailAlreadyExistException("Harvest detail already exists");
        HarvestDetail harvestDetail = new HarvestDetail();
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);
        harvestDetail.setQuantityHarvested(tree.getProductivity());
        harvest.setTotalQuantity(harvest.getTotalQuantity() + harvestDetail.getQuantityHarvested());
        harvestService.save(harvest,tree.getField().getId());
        return ResponseEntity.ok(harvestDetailMapper.toHarvestDetailResponse(harvestDetailService.save(harvestDetail)));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<HarvestDetailResponseVM> get(@PathVariable Long id){
        HarvestDetail harvestDetail = harvestDetailService.findById(id);
        return ResponseEntity.ok(harvestDetailMapper.toHarvestDetailResponse(harvestDetail));
    }


}
