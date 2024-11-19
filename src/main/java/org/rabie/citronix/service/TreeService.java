package org.rabie.citronix.service;

import org.rabie.citronix.domain.Tree;
import org.springframework.stereotype.Service;

@Service
public interface TreeService {
    Tree save(Tree tree);
}
