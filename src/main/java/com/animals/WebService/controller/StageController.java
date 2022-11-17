package com.animals.WebService.controller;


import com.animals.WebService.model.AnimalModel;
import com.animals.WebService.model.GroupModel;
import com.animals.WebService.model.StageModel;
import com.animals.WebService.service.AnimalService;
import com.animals.WebService.service.GroupService;
import com.animals.WebService.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private AnimalService animalService;

    @CrossOrigin(origins = "*")
    @PostMapping("/addStage")
    public StageModel addStage(@RequestBody StageModel stageModel){
        StageModel currStage = stageService.saveStage(stageModel);
        if(currStage.getStage_type() == 1){
            makeGroups(currStage.getId_stage());
        }
        return currStage;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getStageForTour")
    public ResponseEntity<StageModel> getStageByTypeForTour(@RequestBody Map<String, String> body){
        int id_tour = Integer.parseInt(body.get("id_tour"));
        int stage = Integer.parseInt(body.get("stage_type"));
        System.out.println(id_tour + " " +  stage);
        return new ResponseEntity<>(stageService.getStageByTypeForTour(id_tour, stage), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getStages/{tour}")
    public List<StageModel> getStagesForTour(@PathVariable(value = "tour") Integer id_tour)
    {
        return stageService.getStagesForTour(id_tour);
    }

    private void makeGroups(int idStage) {
        List<AnimalModel> animals = animalService.getAllAnimals();
        List<GroupModel> groups = new LinkedList<>();
        int currGroup = 1;
        int currGroupSize = 0;

        Random rand = new Random();
        while (animals.size() > 0) {
            int next = rand.nextInt(animals.size());
            if (currGroupSize == 8 && currGroup != 8) {
                currGroup++;
                currGroupSize = 0;
            }
            currGroupSize++;
            GroupModel groupModel = new GroupModel();
            groupModel.setGroup_number(currGroup);
            groupModel.setPassed(0);
            groupModel.setPoints(0);
            groupModel.setAnimal(animals.get(next).getNumber());
            groupModel.setStage(idStage);
            groupService.saveGroup(groupModel);

            animals.remove(next);
        }
    }

}
