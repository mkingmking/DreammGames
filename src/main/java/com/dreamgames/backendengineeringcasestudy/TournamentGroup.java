package com.dreamgames.backendengineeringcasestudy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class TournamentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tournament tournament;

    @OneToMany(mappedBy = "tournamentGroup")
    private List<TournamentParticipant> participants;

    public List<TournamentParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<TournamentParticipant> participants) {
        this.participants = participants;
    }

    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
