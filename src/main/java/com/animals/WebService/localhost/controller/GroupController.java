package com.animals.WebService.localhost.controller;


import com.animals.WebService.localhost.model.GroupModel;
import com.animals.WebService.localhost.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @PostMapping("/getGroupsForStage")
    public ResponseEntity<List<GroupModel>> getAllGroupsForStage(@RequestBody Map<String, Integer> body){
        int stage = body.get("id_stage");
        return new ResponseEntity<>(groupService.getAllGroupsForStage(stage), HttpStatus.OK);
    }


    @GetMapping("/getGroupById/{id}")
    public ResponseEntity<GroupModel> getGroupById(@PathVariable(value = "id") Integer id_group){
        return new ResponseEntity<>(groupService.getGroupById(id_group), HttpStatus.OK);
    }


    @GetMapping("/getGroupNumbers/{stage}")
    public ResponseEntity<List<Integer>> getAllGroupNumbersForStage(@PathVariable(value = "stage") Integer stage){
        return new ResponseEntity<>(groupService.getAllGroupNumbersForStage(stage), HttpStatus.OK);
    }


    @GetMapping("/getGroupsForStageAndGroupNumber/stage={stage}&number={number}")
    public ResponseEntity<List<GroupModel>> getAllGroupsForStageAndGroupNumber(@PathVariable(value = "stage") Integer stage,
                                                                               @PathVariable(value = "number") Integer number){
        return new ResponseEntity<>(groupService.getAllGroupsForStageAndGroupNumber(stage, number), HttpStatus.OK);
    }


    @GetMapping("/getGroupsForStageAndGroupNumberOrderByName/stage={stage}&number={number}")
    public ResponseEntity<List<GroupModel>> getAllGroupsForStageAndGroupNumberOrderByName(@PathVariable(value = "stage") Integer stage,
                                                                               @PathVariable(value = "number") Integer number){
        return new ResponseEntity<>(groupService.getAllGroupsForStageAndGroupNumberOrderByName(stage, number), HttpStatus.OK);
    }
}
