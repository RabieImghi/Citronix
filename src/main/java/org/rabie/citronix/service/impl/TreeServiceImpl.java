package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.TreeNullException;
import org.rabie.citronix.repository.TreeRepository;
import org.rabie.citronix.rest.api.TreeRest;
import org.rabie.citronix.service.TreeService;
import org.springframework.stereotype.Component;

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
}
