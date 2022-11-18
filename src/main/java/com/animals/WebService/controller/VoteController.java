package com.animals.WebService.controller;


import com.animals.WebService.model.TournamentModel;
import com.animals.WebService.model.VoteModel;
import com.animals.WebService.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @CrossOrigin(origins = "https://digitalni-album.igraoni.ca/*")
    @PostMapping("/addVote")
    public ResponseEntity<String> newVote(@RequestBody VoteModel voteModel){
        voteService.addVote(voteModel);
        return new ResponseEntity<>("Vote is added!", HttpStatus.OK);
    }
}
