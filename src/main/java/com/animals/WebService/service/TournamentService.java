package com.animals.WebService.service;


import com.animals.WebService.model.TournamentModel;
import com.animals.WebService.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public TournamentModel saveTournament(TournamentModel tournamentModel){
        return tournamentRepository.save(tournamentModel);
    }

    public List<TournamentModel> getActiveTournamnets(){
        return tournamentRepository.getActiveTournaments();
    }

    public List<TournamentModel> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public TournamentModel getTournamentById(int id){
        return tournamentRepository.getTournamentById(id);
    }

    public List<TournamentModel> getAllActiveTournaments(){
        return tournamentRepository.getActiveTournaments();
    }

    public List<TournamentModel> getNotFinishedTournaments(LocalDateTime now) {
        return tournamentRepository.getNotFinishedTournaments(now);
    }

}
