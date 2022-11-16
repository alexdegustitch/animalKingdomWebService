package com.animals.WebService.service;


import com.animals.WebService.model.StageModel;
import com.animals.WebService.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public StageModel saveStage(StageModel stageModel){
        return stageRepository.save(stageModel);
    }

    public List<StageModel> getStagesForTour(int id_tour){
        return stageRepository.getStagesForTour(id_tour);
    }

    public StageModel getStageByTypeForTour(Integer id_tour, Integer stage){
        StageModel s = stageRepository.getStageTypeForTour(id_tour, stage);
        return s;
    }

    public List<StageModel> getActiveStages(){
        return stageRepository.getActiveStages();
    }
}
