package com.animals.WebService.repository;


import com.animals.WebService.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel, Integer> {
}
