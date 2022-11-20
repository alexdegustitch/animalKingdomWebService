package com.animals.WebService.heroku.repository;


import com.animals.WebService.heroku.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepositoryHeroku extends JpaRepository<VoteModel, Integer> {
    @Query(value = "SELECT * FROM vote v WHERE v.wanted_animal = :wanted_animal AND v.username = :username AND v.stage = :stage", nativeQuery = true)
    VoteModel getVote(@Param("wanted_animal") Integer wanted_animal, @Param("username") String user, @Param("stage") Integer stage);
}
