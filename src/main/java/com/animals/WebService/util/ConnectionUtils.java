package com.animals.WebService.util;

import com.animals.WebService.model.GroupModel;
import com.animals.WebService.model.StageModel;
import com.animals.WebService.model.VoteModel;
import com.animals.WebService.service.GroupService;
import com.animals.WebService.service.StageService;
import com.animals.WebService.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ConnectionUtils {

    @Autowired
    private GroupService groupService;
    private int cnt = 1;

    @Autowired
    private VoteService voteService;

    @Autowired
    private StageService stageService;

    private Map<Integer, List<String>> mapGroupNumbers;

    @PostConstruct
    private void init() throws IOException {

        mapGroupNumbers = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1423");
        list.add("4132");
        list.add("2314");
        list.add("3241");
        list.add("1423");
        list.add("4132");
        list.add("2314");
        list.add("3241");
        mapGroupNumbers.put(1, list);

        list = new ArrayList<>();
        list.add("121");
        list.add("212");
        list.add("121");
        list.add("212");
        mapGroupNumbers.put(2, list);

        list = new ArrayList<>();
        list.add("1221");
        mapGroupNumbers.put(3, list);
    }



    public void makeGroups(StageModel currStage){
        switch (currStage.getStage_type()){
            case 1: stage1(currStage); break;
            case 2: stage2(currStage); break;
            case 3: stage3(currStage); break;
            case 4: stage4(currStage); break;
            case 5: stage5(currStage); break;
        }
    }

    private void stage1(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);
        List<String> groupNumbers = mapGroupNumbers.get(1);

        for(int i=1;i<=8;i++)
        {
            List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), i);
            for(int j=0;j<4;j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(1);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(nextStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(Integer.parseInt(groupNumbers.get(i - 1).substring(j, j + 1)));
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            for(int j=4;j<animals.size();j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(-1);
                groupModel.setActive(0);
                groupService.saveGroup(groupModel);
            }
        }
    }
    private void stage2(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 2);
        StageModel qualificationStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);
        List<String> groupNumbers = mapGroupNumbers.get(2);

        for(int i=1;i<=4;i++)
        {
            List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), i);
            for(int j=0;j<3;j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(1);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(nextStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(Integer.parseInt(groupNumbers.get(i - 1).substring(j, j + 1)));
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            for(int j=3;j<5;j++)
            {
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(2);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(qualificationStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(1);
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            for(int j=5;j<animals.size();j++)
            {
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(-1);
                groupModel.setActive(0);
                groupService.saveGroup(groupModel);
            }
            int lastStageType = currStage.getStage_type() - 1;
            while(lastStageType >= 1){
                StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
                for(int j=5;j<animals.size();j++){
                    GroupModel groupModel = animals.get(j);
                    GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                    if(currGroup == null)
                        continue;
                    currGroup.setActive(0);
                    groupService.saveGroup(currGroup);
                }
                lastStageType--;
            }
        }
    }
    private void stage3(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);
        List<String> groupNumbers = mapGroupNumbers.get(3);

        List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), 1);

        for(int j=0;j<4;j++){
            GroupModel groupModel = animals.get(j);
            groupModel.setPassed(1);
            groupService.saveGroup(groupModel);

            GroupModel newGroup = new GroupModel();
            newGroup.setPassed(0);
            newGroup.setStage(nextStage.getId_stage());
            newGroup.setPoints(0);
            newGroup.setGroup_number(Integer.parseInt(groupNumbers.get(0).substring(j, j + 1)));
            newGroup.setAnimal(groupModel.getAnimal());
            groupService.saveGroup(newGroup);
        }
        int lastStageType = currStage.getStage_type();
        while(lastStageType >= 1){
            StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
            for(int j=4;j<8;j++){
                GroupModel groupModel = animals.get(j);
                GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                if(currGroup == null)
                    continue;
                currGroup.setActive(0);
                groupService.saveGroup(currGroup);
            }
            lastStageType--;
        }

    }
    private void stage4(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);

        for(int i=1;i<=2;i++)
        {
            List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), i);
            for(int j=0;j<4;j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(1);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(nextStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(1);
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            int lastStageType = currStage.getStage_type();
            while(lastStageType >= 1){
                StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
                for(int j=4;j<8;j++){
                    GroupModel groupModel = animals.get(j);
                    GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                    if(currGroup == null)
                        continue;
                    currGroup.setActive(0);
                    groupService.saveGroup(currGroup);
                }
                lastStageType--;
            }
        }
    }
    private void stage5(StageModel currStage){

        List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), 1);
        for(int j=0;j<animals.size();j++){
            GroupModel groupModel = animals.get(j);
            groupModel.setPassed(j + 1);
            groupService.saveGroup(groupModel);
        }

        int lastStageType = currStage.getStage_type();
        while(lastStageType >= 1){
            StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
            for(int j=0;j<animals.size();j++){
                GroupModel groupModel = animals.get(j);
                GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                if(currGroup == null)
                    continue;
                currGroup.setActive(0);
                groupService.saveGroup(currGroup);
            }
            lastStageType--;
        }

    }


    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
