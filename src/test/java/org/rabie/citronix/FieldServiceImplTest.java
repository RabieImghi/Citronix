package org.rabie.citronix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.exception.AreaOfFiledMustBeInfAreaOfFarmException;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.repository.FieldRepository;
import org.rabie.citronix.service.FieldService;
import org.rabie.citronix.service.impl.FieldServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FieldServiceImplTest {
    @Mock
    private FieldRepository fieldRepository;

    @InjectMocks
    private FieldServiceImpl fieldService;
    private Farm farm;
    private Field field;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        farm = new Farm();
        farm.setId(1L);
        farm.setName("Farm 1");
        farm.setArea(100.0);
        List<Field> fields = new ArrayList<>();
        for (int i=1; i<10;i++){
            field = new Field();
            field.setId(1L+i);
            field.setName("Field "+i);
            field.setArea(9.0);
            field.setFarm(farm);
            fields.add(field);
        }
        farm.setFields(fields);
    }


    @Test
    void testSaveFieldIsNullThrowsFieldsNullException() {
        Field nullField = null;
        assertThrows(FieldsNullException.class, () -> fieldService.save(nullField));
    }

    @Test
    void testSaveFieldWithAreaInOfOneException(){
        field.setArea(0.01);
        assertThrows(AreaOfFiledMustBeInfAreaOfFarmException.class,()->fieldService.save(field));
    }

    @Test
    void testSaveFieldWithAreaHighThenHalfFarmAreaException(){
        field.setArea(120.);
        assertThrows(AreaOfFiledMustBeInfAreaOfFarmException.class,()->fieldService.save(field));
    }
    @Test
    void testSaveFieldOnFarmWithSizeFieldsHighThenTeenException(){
        List<Field> fields = new ArrayList<>(farm.getFields());
        fields.add(new Field());
        farm.setFields(fields);
        assertThrows(AreaOfFiledMustBeInfAreaOfFarmException.class, () -> fieldService.save(field));
    }
    @Test
    void testSaveWhenAreaOfAllFieldHighThanFarmArea(){
        field.setArea(20.);
        when(fieldRepository.findByFarmId(field.getFarm().getId())).thenReturn(field.getFarm().getFields());
        assertThrows(AreaOfFiledMustBeInfAreaOfFarmException.class, () -> fieldService.save(field));
    }
    @Test
    void testSaveFieldWithSuccess(){
        field.setId(100L);
        field.setArea(8.);
        when(fieldRepository.findByFarmId(field.getFarm().getId())).thenReturn(field.getFarm().getFields());
        when(fieldRepository.save(field)).thenReturn(field);
    }










}
