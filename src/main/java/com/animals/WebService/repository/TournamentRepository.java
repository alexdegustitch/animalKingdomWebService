package com.animals.WebService.repository;


import com.animals.WebService.model.AnimalModel;
import com.animals.WebService.model.TournamentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository  extends JpaRepository<TournamentModel, Integer> {

    @Query(value = "SELECT * FROM competition c WHERE c.active = 1", nativeQuery = true)
    List<TournamentModel> getActiveTournaments();

    @Query(value = "SELECT * FROM competition c WHERE c.id_competition = :id_tour", nativeQuery = true)
    TournamentModel getTournamentById(@Param("id_tour") Integer id_tour);
}
