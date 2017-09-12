package com.globallogic.dao;

import com.globallogic.model.animals.Animal;
import java.util.List;

public interface AnimalDao{

    void save(Animal animal);

    Animal getAnimal(int id);

    List<? extends Animal> getList();

    void deleteAnimal(int id);
}
