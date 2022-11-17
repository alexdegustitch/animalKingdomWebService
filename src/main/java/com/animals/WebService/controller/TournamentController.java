package com.animals.WebService.controller;


import com.animals.WebService.model.TournamentModel;
import com.animals.WebService.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;


    @CrossOrigin(origins = "*")
    @PostMapping("/addTournament")
    public TournamentModel newTournament(@RequestBody TournamentModel tournamentModel){
        return tournamentService.saveTournament(tournamentModel);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/activeTournament")
    public ResponseEntity<TournamentModel> getActiveTournament(){
        return new ResponseEntity<>(tournamentService.getActiveTournamnets().get(0), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allTournaments")
    public List<TournamentModel> getAllTournaments(){
        return tournamentService.getAllTournaments();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getTourById/{tour}")
    public TournamentModel getAllTournaments(@PathVariable(value = "tour") Integer id_tour){
        return tournamentService.getTournamentById(id_tour);
    }

}
