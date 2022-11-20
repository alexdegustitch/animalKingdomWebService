package com.animals.WebService.localhost.service;


import com.animals.WebService.heroku.repository.StageRepositoryHeroku;
import com.animals.WebService.localhost.repository.StageRepository;
import com.animals.WebService.localhost.model.StageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private StageRepositoryHeroku stageRepositoryHeroku;

    public StageModel saveStage(StageModel stageModel){
        StageModel s = stageRepository.save(stageModel);
        com.animals.WebService.heroku.model.StageModel stageModel1 = new com.animals.WebService.heroku.model.StageModel();
        stageModel1.setActive(s.getActive());
        stageModel1.setStage_type(s.getStage_type());
        stageModel1.setCompetition(s.getCompetition());
        stageModel1.setId_stage(s.getId_stage());
        stageModel1.setStart_date(s.getStart_date());
        stageModel1.setEnd_date(s.getEnd_date());
        stageRepositoryHeroku.save(stageModel1);
        return s;

    }

    public List<StageModel> getStagesForTour(int id_tour){
        return stageRepository.getStagesForTour(id_tour);
    }

    public StageModel getStageByTypeForTour(Integer id_tour, Integer stage){
        return stageRepository.getStageTypeForTour(id_tour, stage);

    }

    public List<StageModel> getActiveStages(){
        return stageRepository.getActiveStages();
    }

    public StageModel getActiveStageForCompetition(int id_competition){
        return stageRepository.getActiveStageForCompetition(id_competition);
    }

    public List<StageModel> getStagesForCompetition(int id_competition){
        return stageRepository.getStagesForCompetition(id_competition);
    }


    public StageModel getStageForCompetitionByStageType(int id_competition, int stage){
        return stageRepository.getStageForCompetitionByStageType(id_competition, stage);
    }
}
