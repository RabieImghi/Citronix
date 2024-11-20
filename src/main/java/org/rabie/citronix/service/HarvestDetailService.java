package org.rabie.citronix.service;

import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HarvestDetailService {
    List<HarvestDetail> getListDetail(List<Tree> trees);
}
