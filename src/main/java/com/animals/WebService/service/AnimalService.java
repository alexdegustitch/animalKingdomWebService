package com.animals.WebService.service;

import com.animals.WebService.model.AnimalModel;
import com.animals.WebService.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public AnimalModel saveAnimal(AnimalModel newAnimal){
        return animalRepository.save(newAnimal);
    }

    public AnimalModel getAnimalByNumber(Integer number){
        return animalRepository.getAnimalByNumber(number);
    }

    public List<AnimalModel> getAnimalsOfSameOrder(String order, Integer number){
        return animalRepository.getAnimalsOfSameOrder(order, number);
    }

    public List<AnimalModel> getAllAnimals(){
        return animalRepository.findAll(Sort.by(Sort.Direction.ASC,"number"));
    }
}
