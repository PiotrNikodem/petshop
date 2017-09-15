package com.globallogic.service;

import com.globallogic.dao.AnimalDao;
import com.globallogic.model.animals.Animal;
import com.globallogic.model.animals.Cat;
import com.globallogic.model.animals.Hamster;
import com.globallogic.model.animals.Lizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalDao animalDao;

    @Override
    public void saveAnimal(Animal animal) {
        animalDao.save(animal);
    }

    @Override
    public Animal getAnimal(int id) {
        return animalDao.getAnimal(id);
    }
    @Override
    public List<? extends Animal> getList() {
        return animalDao.getList();
    }

    @Override
    public void deleteAnimal(int id) {
        animalDao.deleteAnimal(id);
    }

    @Override
    public Animal getFirstPageAnimal(String type) {
        if (type.equalsIgnoreCase("hamster"))
            return getFirstPageHamster();
        if (type.equalsIgnoreCase("cat"))
            return  getFirstPageCat();
        if (type.equalsIgnoreCase("lizard"))
            return getFirstPageLizard();
        return null;
    }

    private Hamster getFirstPageHamster() {
        List<Hamster> hamsterList = animalDao.getHamsterList();
        Collections.sort(hamsterList);
        return hamsterList.get(0);
    }

    private Cat getFirstPageCat() {
        List<Cat> catList = animalDao.getCatList();
        Collections.sort(catList);
        return catList.get(0);
    }

    private Lizard getFirstPageLizard() {
        List<Lizard> lizardList = animalDao.getLizardList();
        Collections.sort(lizardList);
        return lizardList.get(0);
    }
}
