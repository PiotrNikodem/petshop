package com.globallogic.controler;

import com.globallogic.model.animals.Animal;
import com.globallogic.service.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    AnimalServiceImpl animalService;

    @RequestMapping(value = "/animal", method = RequestMethod.POST, consumes = "application/json")
    public <T extends Animal> void saveAnimal(@RequestBody T animal) {
        animalService.saveAnimal(animal);
    }

    @RequestMapping(value = "/animal/{animalId}", method = RequestMethod.GET, produces = "application/json")
    public Animal getAnimal(@PathVariable String animalId) {
        return animalService.getAnimal(Integer.valueOf(animalId));
    }
    @RequestMapping(value = "/animal/{animalId}", method = RequestMethod.DELETE)
    public void deleteAnimal(@PathVariable String animalId) {
        animalService.deleteAnimal(Integer.valueOf(animalId));
    }

    @RequestMapping(value = "/animal", method = RequestMethod.GET, produces = "application/json")
    public List<? extends Animal> getAnimal() {
        return animalService.getList();
    }

    @RequestMapping(value = "/specialanimal", method = RequestMethod.GET, produces = "application/json")
    public Animal getFirstPageAnimal(@RequestParam("type") String type) {
       return animalService.getFirstPageAnimal(type);
    }

}
