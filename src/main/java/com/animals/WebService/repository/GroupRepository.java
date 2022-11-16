package com.animals.WebService.repository;


import com.animals.WebService.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Integer> {

    @Query(value = "SELECT * FROM group_table gt WHERE gt.stage = :stage ORDER BY gt.group_number ASC", nativeQuery = true)
    List<GroupModel> getAllGroupsForStage(@Param("stage") Integer stage);

    @Query(value = "SELECT * FROM group_table gt WHERE gt.id_group = :id", nativeQuery = true)
    GroupModel findGroupById(@Param("id") Integer id);

    @Query(value = "SELECT distinct gt.group_number FROM group_table gt WHERE gt.stage = :stage group by gt.group_number ORDER BY gt.group_number ASC", nativeQuery = true)
    List<Integer> getAllGroupNumbersForStage(@Param("stage") Integer stage);

    @Query(value = "SELECT * FROM group_table gt WHERE gt.stage = :stage and gt.group_number = :number ORDER BY gt.points DESC, gt.animal ASC", nativeQuery = true)
    List<GroupModel> getAllGroupsForStageAndGroupNumber(@Param("stage") Integer stage, @Param("number") Integer number);

    @Query(value = "SELECT gt.* FROM group_table gt, animal an WHERE gt.stage = :stage and gt.group_number = :number and gt.animal = an.number and gt.active = 1 ORDER BY gt.animal", nativeQuery = true)
    List<GroupModel> getAllGroupsForStageAndGroupNumberOrderByName(@Param("stage") Integer stage, @Param("number") Integer number);

    @Query(value = "SELECT * FROM group_table g WHERE g.animal = :animal AND g.stage = :stage", nativeQuery = true)
    GroupModel findAnimalByStage(@Param("animal") Integer animal, @Param("stage") Integer stage);

}
