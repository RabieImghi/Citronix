package org.rabie.citronix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.TreeNullException;
import org.rabie.citronix.repository.TreeRepository;
import org.rabie.citronix.service.impl.TreeServiceImpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TreeServiceImplTest {
    @Mock
    private TreeRepository treeRepository;

    @InjectMocks
    private TreeServiceImpl treeService;

    private Tree tree;
    private Farm farm;
    private Field field;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        farm = new Farm();
        farm.setId(1L);
        farm.setName("Farm 1");
        farm.setArea(100.0);

        field = new Field();
        field.setId(1L);
        field.setFarm(farm);
        field.setArea(0.1);
        field.setName("Field 1");
        List<Tree> trees = new ArrayList<>();
        for (int i = 0;i<11;i++){
            tree = new Tree();
            tree.setId(1L+i);
            tree.setName("tree 1");
            tree.setDatePlantation(LocalDate.now());
            tree.setField(field);
            trees.add(tree);
        }
        field.setTrees(trees);

    }

//    @Test
//    void testSaveTreeWithNullException(){
//        assertThrows(TreeNullException.class,()->treeService.save(null));
//    }
//
//    @Test
//    void testSaveTreeWithOldAgeException(){
//        tree.setDatePlantation(LocalDate.now().minusYears(21));
//        assertThrows(TreeNullException.class,()->treeService.save(tree));
//    }
//
//    @Test
//    void testSaveTreeInInvalidSessionException(){
//        tree.setDatePlantation(LocalDate.of(2023,1,2));
//        assertTrue(tree.getDatePlantation().getMonth().getValue() < Month.MARCH.getValue() ||
//                tree.getDatePlantation().getMonth().getValue() > Month.MAY.getValue());
//        assertThrows(TreeNullException.class, () -> treeService.save(tree));
//    }
//

}
