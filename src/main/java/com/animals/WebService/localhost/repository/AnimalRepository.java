package com.animals.WebService.localhost.repository;

import com.animals.WebService.localhost.model.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalModel, Integer> {

    @Query(value = "SELECT * FROM animal a WHERE a.number = :number", nativeQuery = true)
    AnimalModel getAnimalByNumber(@Param("number") Integer number);

    @Query(value = "SELECT * FROM animal a WHERE a.number <> :number and a.order_name = :order_name", nativeQuery = true)
    List<AnimalModel> getAnimalsOfSameOrder(@Param("order_name") String order, @Param("number") Integer number);
}
