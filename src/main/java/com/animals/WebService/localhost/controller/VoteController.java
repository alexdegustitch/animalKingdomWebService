package com.animals.WebService.localhost.controller;


import com.animals.WebService.localhost.model.VoteModel;
import com.animals.WebService.localhost.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;


    @PostMapping("/addVote")
    public ResponseEntity<String> newVote(@RequestBody VoteModel voteModel){
        voteService.addVote(voteModel);
        return new ResponseEntity<>("Vote is added!", HttpStatus.OK);
    }
}
