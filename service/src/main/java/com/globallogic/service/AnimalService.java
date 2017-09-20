package com.globallogic.service;

import com.globallogic.model.animals.Animal;
import com.globallogic.model.animals.Cat;
import com.globallogic.model.animals.Hamster;
import com.globallogic.model.animals.Lizard;

import java.util.List;

public interface AnimalService {

    void saveAnimal(Animal animal);

     Animal getAnimal(int id);

     List<? extends Animal> getList();

     boolean deleteAnimal(int id);

     Animal getFirstPageAnimal(String type);

}
