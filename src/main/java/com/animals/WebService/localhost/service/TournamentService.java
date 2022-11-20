package com.animals.WebService.localhost.service;


import com.animals.WebService.heroku.repository.TournamentRepositoryHeroku;
import com.animals.WebService.localhost.model.TournamentModel;
import com.animals.WebService.localhost.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private TournamentRepositoryHeroku tournamentRepositoryHeroku;

    public TournamentModel saveTournament(TournamentModel tournamentModel) {
        TournamentModel t = tournamentRepository.save(tournamentModel);
        com.animals.WebService.heroku.model.TournamentModel tournamentModel1 = new com.animals.WebService.heroku.model.TournamentModel();
        tournamentModel1.setActive(t.getActive());
        tournamentModel1.setName(t.getName());
        tournamentModel1.setEnd_date(t.getEnd_date());
        tournamentModel1.setStart_date(t.getStart_date());
        tournamentModel1.setId_competition(t.getId_competition());
        tournamentModel1.setActive_stage(t.getActive_stage());
        tournamentRepositoryHeroku.save(tournamentModel1);
        return t;
    }

    public List<TournamentModel> getActiveTournamnets() {
        return tournamentRepository.getActiveTournaments();
    }

    public List<TournamentModel> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public TournamentModel getTournamentById(int id) {
        return tournamentRepository.getTournamentById(id);
    }

    public List<TournamentModel> getAllActiveTournaments() {
        return tournamentRepository.getActiveTournaments();
    }

    public List<TournamentModel> getNotFinishedTournaments(LocalDateTime now) {
        return tournamentRepository.getNotFinishedTournaments(now);
    }

}
