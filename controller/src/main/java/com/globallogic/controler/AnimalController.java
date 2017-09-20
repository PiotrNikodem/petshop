package com.globallogic.controler;

import com.globallogic.model.animals.Animal;
import com.globallogic.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AnimalController {

    @Autowired
     AnimalService animalService;

    @RequestMapping(value = "/animal", method = RequestMethod.POST, consumes = "application/json")
    public <T extends Animal> void saveAnimal(@RequestBody T animal) {
        animalService.saveAnimal(animal);
    }

    @RequestMapping(value = "/animal/{animalId}", method = RequestMethod.GET, produces = "application/json")
    public Animal getAnimal(@PathVariable String animalId) {
        return animalService.getAnimal(Integer.valueOf(animalId));
    }
    @RequestMapping(value = "/animal/{animalId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAnimal(@PathVariable String animalId) {
        if (animalService.deleteAnimal(Integer.valueOf(animalId)))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity<>("Animals with given id does not exists.", HttpStatus.NOT_FOUND);
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
