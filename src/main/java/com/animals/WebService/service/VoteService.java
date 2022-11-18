package com.animals.WebService.service;

import com.animals.WebService.model.GroupModel;
import com.animals.WebService.model.StageModel;
import com.animals.WebService.model.VoteModel;
import com.animals.WebService.repository.StageRepository;
import com.animals.WebService.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private GroupService groupService;
    @Autowired
    private StageRepository stageRepository;


    public void addVote(VoteModel voteModel){
        List<StageModel> activeStages = stageRepository.getActiveStages();
        LocalDateTime currentTime = LocalDateTime.now();
        for(StageModel stageModel : activeStages) {
            GroupModel animalForGroup = groupService.findAnimalByStage(voteModel.getWanted_animal(), stageModel.getId_stage());
            if (animalForGroup != null) {
                VoteModel newVote = new VoteModel();
                newVote.setStage(stageModel.getId_stage());
                newVote.setVoting_time(currentTime);
                newVote.setUsername(voteModel.getUsername());
                newVote.setOffered_animal(voteModel.getOffered_animal());
                newVote.setProfile(voteModel.getProfile());


                newVote.setWanted_animal(voteModel.getWanted_animal());
                try {
                    voteRepository.save(newVote);
                    System.out.println("User " + newVote.getUsername() + " voted for animal " + newVote.getWanted_animal() + " in stage " + newVote.getStage() + "! - time: " + currentTime);
                }
                catch (Exception e){
                    System.out.println("User has already voted for the given animal! - time: " + currentTime);
                }

            }
        }
    }
    public VoteModel getVote(int wanted_animal, String user, int stage){
        return voteRepository.getVote(wanted_animal, user, stage);
    }
}
