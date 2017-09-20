package com.globallogic.dao;

import com.globallogic.model.animals.Animal;
import com.globallogic.model.animals.Cat;
import com.globallogic.model.animals.Hamster;
import com.globallogic.model.animals.Lizard;

import java.util.List;

public interface AnimalDao{

    void save(Animal animal);

    Animal getAnimal(int id);

    List<? extends Animal> getList();

    boolean deleteAnimal(int id);

    List<Cat> getCatList();
    List<Hamster> getHamsterList();
    List<Lizard> getLizardList();
}
