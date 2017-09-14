package com.globallogic.service;

import com.globallogic.dao.AnimalDaoImpl;
import com.globallogic.model.animals.Animal;
import com.globallogic.model.animals.Cat;
import com.globallogic.model.animals.Hamster;
import com.globallogic.model.animals.Lizard;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class AnimalServiceImplTest {

    @Mock
    AnimalDaoImpl animalDao;

    @InjectMocks
    AnimalServiceImpl service;

    @Captor
    ArgumentCaptor<Animal> captor;

    private List<Animal> animalList = new ArrayList<>();
    private List<Cat> catList = new ArrayList<>();
    private List<Hamster> hamsterList = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        for(int i = 1; i<5; i++) {
            Hamster hamster = new Hamster("hamster", new BigDecimal(i), new Date(2017, i, 15));
            Lizard lizard = new Lizard("lizard", new BigDecimal(i), new Date(2017, i, 15));
            Cat cat = new Cat("cat", new BigDecimal(i), new Date(2017, i, 15));
            hamster.setId(i);
            lizard.setId(i);
            cat.setId(i);
            catList.add(cat);
            hamsterList.add(hamster);
            animalList.add(hamster);
            animalList.add(lizard);
            animalList.add(cat);
        }
        }

    @AfterMethod
    public void reset() {
        Mockito.reset(animalDao);
    }


    @Test
    public void saveValidAnimal() {

        doNothing().when(animalDao).save(any(Animal.class));
        service.saveAnimal(animalList.get(0));
        verify(animalDao, times(1)).save(captor.capture());
        Assert.assertEquals(captor.getValue().getType(), "Hamster");
        Assert.assertEquals(captor.getValue().getId(), 1);
        Assert.assertTrue(captor.getValue().equals(animalList.get(0)));
    }
    @Test
    public void getAnimal() {
        when(animalDao.getAnimal(2)).thenReturn(animalList.get(4));
        Lizard lizard = (Lizard) service.getAnimal(2);
        verify((animalDao), times(1)).getAnimal(anyInt());
        Assert.assertEquals(lizard, animalList.get(4));
    }

    @Test
    public void getFirstPageAnimalTypeCat() {
        when(animalDao.getCatList()).thenReturn(catList);
        Cat youngestCat = catList.get(3);
        Cat cat = (Cat) service.getFirstPageAnimal("cat");
        Assert.assertEquals(cat, youngestCat);
    }

    @Test
    public void getFirstPageAnimalTypeHamster() {
        when(animalDao.getHamsterList()).thenReturn(hamsterList);
        Hamster cheapestHamster = hamsterList.get(0);
        Hamster hamster = (Hamster) service.getFirstPageAnimal("hamster");
        Assert.assertEquals(hamster.getPrice(), new BigDecimal(1));
        Assert.assertEquals(hamster, cheapestHamster);
    }




}
