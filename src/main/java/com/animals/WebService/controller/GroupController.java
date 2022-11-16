package com.animals.WebService.controller;


import com.animals.WebService.model.AnimalModel;
import com.animals.WebService.model.GroupModel;
import com.animals.WebService.service.AnimalService;
import com.animals.WebService.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(path = "/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getGroupsForStage")
    public ResponseEntity<List<GroupModel>> getAllGroupsForStage(@RequestBody Map<String, Integer> body){
        int stage = body.get("id_stage");
        return new ResponseEntity<>(groupService.getAllGroupsForStage(stage), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getGroupById/{id}")
    public ResponseEntity<GroupModel> getGroupById(@PathVariable(value = "id") Integer id_group){
        return new ResponseEntity<>(groupService.getGroupById(id_group), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getGroupNumbers/{stage}")
    public ResponseEntity<List<Integer>> getAllGroupNumbersForStage(@PathVariable(value = "stage") Integer stage){
        return new ResponseEntity<>(groupService.getAllGroupNumbersForStage(stage), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getGroupsForStageAndGroupNumber/stage={stage}&number={number}")
    public ResponseEntity<List<GroupModel>> getAllGroupsForStageAndGroupNumber(@PathVariable(value = "stage") Integer stage,
                                                                               @PathVariable(value = "number") Integer number){
        return new ResponseEntity<>(groupService.getAllGroupsForStageAndGroupNumber(stage, number), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getGroupsForStageAndGroupNumberOrderByName/stage={stage}&number={number}")
    public ResponseEntity<List<GroupModel>> getAllGroupsForStageAndGroupNumberOrderByName(@PathVariable(value = "stage") Integer stage,
                                                                               @PathVariable(value = "number") Integer number){
        return new ResponseEntity<>(groupService.getAllGroupsForStageAndGroupNumberOrderByName(stage, number), HttpStatus.OK);
    }
}
