package com.animals.WebService.service;


import com.animals.WebService.model.GroupModel;
import com.animals.WebService.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupModel saveGroup(GroupModel groupModel){
        return groupRepository.save(groupModel);
    }

    public List<GroupModel> getAllGroupsForStage(int stage){
        return groupRepository.getAllGroupsForStage(stage);
    }

    public GroupModel getGroupById(int id_group){
        return groupRepository.findGroupById(id_group);
    }

    public List<Integer> getAllGroupNumbersForStage(int stage){
        return groupRepository.getAllGroupNumbersForStage(stage);
    }

    public List<GroupModel> getAllGroupsForStageAndGroupNumber(int stage, int number){
        return groupRepository.getAllGroupsForStageAndGroupNumber(stage, number);
    }

    public List<GroupModel> getAllGroupsForStageAndGroupNumberOrderByName(int stage, int number){
        return groupRepository.getAllGroupsForStageAndGroupNumberOrderByName(stage, number);
    }

    public GroupModel findAnimalByStage(int animal, int stage){
        return groupRepository.findAnimalByStage(animal, stage);
    }


    public List<GroupModel> groupsForStage(int stage, int number){
        return groupRepository.groupsForStage(stage, number);
    }


    public GroupModel updatePoints(GroupModel groupModel){
        return groupRepository.saveAndFlush(groupModel);
    }
}
