package com.animals.WebService.localhost.service;

import com.animals.WebService.heroku.repository.AnimalRepositoryHeroku;
import com.animals.WebService.localhost.model.AnimalModel;
import com.animals.WebService.localhost.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalRepositoryHeroku animalRepositoryHeroku;

    public AnimalModel saveAnimal(AnimalModel newAnimal) {
        AnimalModel a = animalRepository.save(newAnimal);
        return a;
    }

    public AnimalModel getAnimalByNumber(Integer number) {
        return animalRepository.getAnimalByNumber(number);
    }

    public List<AnimalModel> getAnimalsOfSameOrder(String order, Integer number) {
        return animalRepository.getAnimalsOfSameOrder(order, number);
    }

    public List<AnimalModel> getAllAnimals() {
        return animalRepository.findAll(Sort.by(Sort.Direction.ASC, "number"));
    }
}
