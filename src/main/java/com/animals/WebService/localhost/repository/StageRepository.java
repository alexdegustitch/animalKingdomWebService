package com.animals.WebService.localhost.repository;

import com.animals.WebService.localhost.model.StageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<StageModel, Integer> {

    @Query(value = "SELECT * FROM stage s WHERE s.competition = :id_tour ORDER BY s.stage_type ASC", nativeQuery = true)
    List<StageModel> getStagesForTour(@Param("id_tour") Integer number);

    @Query(value = "SELECT * FROM stage s WHERE s.competition = :id_tour and s.stage_type = :stage", nativeQuery = true)
    StageModel getStageTypeForTour(@Param("id_tour") Integer id_tour, @Param("stage") Integer stage);

    @Query(value = "SELECT * FROM stage s WHERE s.active = 1", nativeQuery = true)
    List<StageModel> getActiveStages();

    @Query(value="SELECT * FROM stage s WHERE s.competition = :competition AND s.active = 1", nativeQuery = true)
    StageModel getActiveStageForCompetition(@Param("competition") Integer id_competition);

    @Query(value="SELECT * FROM stage s WHERE s.competition = :competition ORDER BY s.stage_type ASC", nativeQuery = true)
    List<StageModel> getStagesForCompetition(@Param("competition") Integer id_competition);

    @Query(value="SELECT * FROM stage s WHERE s.competition = :competition AND s.stage_type = :type", nativeQuery = true)
    StageModel getStageForCompetitionByStageType(@Param("competition") Integer id_competition, @Param("type") Integer stage);

}
