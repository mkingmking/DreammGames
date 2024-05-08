package com.dreamgames.backendengineeringcasestudy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentGroupRepository extends JpaRepository<TournamentGroup, Long> {

    List<TournamentGroup> findByParticipantsUserId(Long userId);

    public Optional<TournamentGroup> findAvailableGroup();
}
