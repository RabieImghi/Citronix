package org.rabie.citronix.service;

import org.rabie.citronix.domain.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface TreeService {
    Tree save(Tree tree);
    Tree deleteById(Long id);
    Page<Tree> getAll(PageRequest pageRequest);
}
