package com.animals.WebService.controller;

import com.animals.WebService.model.AnimalModel;
import com.animals.WebService.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findAnimal/{number}")
    public ResponseEntity<AnimalModel> getAnimalByNumber(@PathVariable(value = "number") Integer number){
        AnimalModel animalModel = animalService.getAnimalByNumber(number);
        if (animalModel == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(animalModel, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addAnimal")
    public AnimalModel newAnimal(@RequestBody AnimalModel newAnimal) {
        if (animalService.getAnimalByNumber(newAnimal.getNumber()) == null)
        {
            return animalService.saveAnimal(newAnimal);
        }
        return null;

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/animalsSameOrder")
    public List<AnimalModel> animalsOfSameOrder(@RequestBody Map<String, String> body) {
        String order = body.get("order");
        int number = Integer.parseInt(body.get("number"));

        return animalService.getAnimalsOfSameOrder(order, number);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/allAnimals")
    public ResponseEntity<List<AnimalModel>> allAnimals(){
        return new ResponseEntity<>(animalService.getAllAnimals(), HttpStatus.OK);
    }

}
