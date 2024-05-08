package com.dreamgames.backendengineeringcasestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentGroupService {

    @Autowired
    private TournamentGroupRepository groupRepository;

    public Optional<TournamentGroup> findAvailableGroup() {
        // Retrieve all tournament groups
        List<TournamentGroup> groups = groupRepository.findAll();

        // Iterate through the groups to find an available one
        for (TournamentGroup group : groups) {
            // Check if the group status is "AVAILABLE"
            if ("AVAILABLE".equals(group.getStatus())) {
                return Optional.of(group);
            }
        }

        // If no available group found, return empty optional
        return Optional.empty();
    }

    public Optional<TournamentGroup> findGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public List<TournamentGroup> findByUserId(Long userId) {
        return groupRepository.findByParticipantsUserId(userId);
    }
}
