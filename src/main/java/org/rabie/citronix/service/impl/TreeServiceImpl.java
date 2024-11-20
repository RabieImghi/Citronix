package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.TreeNullException;
import org.rabie.citronix.repository.TreeRepository;
import org.rabie.citronix.rest.api.TreeRest;
import org.rabie.citronix.service.HarvestDetailService;
import org.rabie.citronix.service.HarvestService;
import org.rabie.citronix.service.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("treeService")
public class TreeServiceImpl implements TreeService {
    private final TreeRepository treeRepository;
    private final HarvestDetailService harvestDetailService;

    public TreeServiceImpl(TreeRepository treeRepository , HarvestDetailService harvestDetailService) {
        this.treeRepository = treeRepository;
        this.harvestDetailService = harvestDetailService;
    }

    @Override
    public Tree save(Tree tree) {
        if (tree == null)
            throw new TreeNullException("fields is null");
        return treeRepository.save(tree);
    }

    public Tree deleteById(Long id) {
        Tree tree = treeRepository.findById(id).orElse(null);
        if (tree == null)
            throw new TreeNullException("Tree not found");
        harvestDetailService.deleteByTreeId(id);
        treeRepository.deleteById(id);
        return tree;
    }

    @Override
    public Page<Tree> getAll(PageRequest pageRequest) {
        return treeRepository.findAll(pageRequest);
    }

    public List<Tree> getByFieldId(Long fieldId) {
        return treeRepository.findByFieldId(fieldId);
    }

    @Override
    public Tree getById(Long id) {
        return treeRepository.findById(id).orElse(null);
    }
}
