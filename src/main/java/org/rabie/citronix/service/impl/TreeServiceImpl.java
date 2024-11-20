package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.TreeNullException;
import org.rabie.citronix.repository.TreeRepository;
import org.rabie.citronix.rest.api.TreeRest;
import org.rabie.citronix.service.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("treeService")
public class TreeServiceImpl implements TreeService {
    private final TreeRepository treeRepository;

    public TreeServiceImpl(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
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
