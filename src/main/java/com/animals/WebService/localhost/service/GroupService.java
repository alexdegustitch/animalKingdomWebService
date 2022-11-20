package com.animals.WebService.localhost.service;


import com.animals.WebService.heroku.repository.GroupRepositoryHeroku;
import com.animals.WebService.localhost.model.GroupModel;
import com.animals.WebService.localhost.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupRepositoryHeroku groupRepositoryHeroku;

    public GroupModel saveGroup(GroupModel groupModel) {
        GroupModel gm = groupRepository.save(groupModel);
        com.animals.WebService.heroku.model.GroupModel groupModel1 = new com.animals.WebService.heroku.model.GroupModel();
        groupModel1.setGroup_number(gm.getGroup_number());
        groupModel1.setActive(gm.getActive());
        groupModel1.setAnimal(gm.getAnimal());
        groupModel1.setId_group(gm.getId_group());
        groupModel1.setStage(gm.getStage());
        groupModel1.setPassed(gm.getPassed());
        groupModel1.setPoints(gm.getPoints());
        groupModel1.setLast_vote_time(gm.getLast_vote_time());
        groupRepositoryHeroku.save(groupModel1);
        return gm;
    }

    public List<GroupModel> getAllGroupsForStage(int stage) {
        return groupRepository.getAllGroupsForStage(stage);
    }

    public GroupModel getGroupById(int id_group) {
        return groupRepository.findGroupById(id_group);
    }

    public List<Integer> getAllGroupNumbersForStage(int stage) {
        return groupRepository.getAllGroupNumbersForStage(stage);
    }

    public List<GroupModel> getAllGroupsForStageAndGroupNumber(int stage, int number) {
        return groupRepository.getAllGroupsForStageAndGroupNumber(stage, number);
    }

    public List<GroupModel> getAllGroupsForStageAndGroupNumberOrderByName(int stage, int number) {
        return groupRepository.getAllGroupsForStageAndGroupNumberOrderByName(stage, number);
    }

    public GroupModel findAnimalByStage(int animal, int stage) {
        return groupRepository.findAnimalByStage(animal, stage);
    }


    public List<GroupModel> groupsForStage(int stage, int number) {
        return groupRepository.groupsForStage(stage, number);
    }


    public GroupModel updatePoints(GroupModel groupModel) {
        return groupRepository.saveAndFlush(groupModel);
    }
}
