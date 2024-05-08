package com.dreamgames.backendengineeringcasestudy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, Long> {
    public List<Object[]> aggregateScoresByCountry();

    List<TournamentParticipant> findByGroupId(Long groupId);

    TournamentParticipant findByUserId(Long groupId);

}
