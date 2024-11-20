package org.rabie.citronix.rest.api;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.HarvestDetailAlreadyExistException;
import org.rabie.citronix.rest.mapper.HarvestDetailMapper;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestDetailRequest;
import org.rabie.citronix.rest.vm.response.HarvestDetailResponse;
import org.rabie.citronix.service.HarvestDetailService;
import org.rabie.citronix.service.HarvestService;
import org.rabie.citronix.service.impl.HarvestDetailServiceImpl;
import org.rabie.citronix.service.impl.HarvestServiceImpl;
import org.rabie.citronix.service.impl.TreeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<HarvestDetailResponse> save(@RequestBody HarvestDetailRequest harvestDetailRequest) {
        Harvest harvest = harvestService.findById(harvestDetailRequest.getHarvestId());
        Tree tree = treeService.getById(harvestDetailRequest.getTreeId());
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


}
