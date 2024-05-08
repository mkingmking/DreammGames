package com.dreamgames.backendengineeringcasestudy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dreamgames.Country;

@Service
public class TournamentService {
    // @Autowired
    // private TournamentRepository tournamentRepository;
    @Autowired
    private TournamentGroupRepository groupRepository;
    @Autowired
    private TournamentParticipantRepository participantRepository;

    private UserRepository userRepository;

    public TournamentGroup enterTournament(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Verify user's level
        if (user.getLevel() < 20) {
            throw new IllegalArgumentException("User must be at least level 20 to enter the tournament");
        }

        // Verify and deduct coins
        if (user.getCoins() < 1000) {
            throw new IllegalArgumentException("User does not have enough coins to enter the tournament");
        }
        user.setCoins(user.getCoins() - 1000);
        userRepository.save(user);

        // Assign to a group
        TournamentGroup group = findOrCreateTournamentGroup();
        TournamentParticipant participant = new TournamentParticipant(user, group, 0, false);
        participantRepository.save(participant);

        return group;
    }

    private TournamentGroup findOrCreateTournamentGroup() {
        // Example method to find an existing group or create a new one
        // This would ideally handle the logic to ensure groups are filled with one user
        // from each country
        Optional<TournamentGroup> availableGroup = groupRepository.findAvailableGroup();
        return availableGroup.orElseGet(() -> {
            TournamentGroup newGroup = new TournamentGroup();
            groupRepository.save(newGroup);
            return newGroup;
        });
    }

    public User claimReward(Long userId) {
        TournamentParticipant participant = participantRepository.findByUserId(userId);

        if (participant.getRewardsClaimed()) {
            throw new IllegalStateException("Rewards have already been claimed");
        }

        // Determine ranking and update coins
        int rank = determineRank(participant);
        int reward = calculateReward(rank);
        User user = participant.getUser();
        user.setCoins(user.getCoins() + reward);
        userRepository.save(user);

        // Mark rewards as claimed
        participant.setRewardsClaimed(true);
        participantRepository.save(participant);

        return user;
    }

    private int determineRank(TournamentParticipant participant) {
        // Get the tournament group associated with the participant
        TournamentGroup group = participant.getTournamentGroup();

        // Get the list of participants from the tournament group
        List<TournamentParticipant> participants = group.getParticipants();

        // Sort the participants based on their scores in descending order
        participants.sort(Comparator.comparing(TournamentParticipant::getScore).reversed());

        // Determine the rank of the participant within the sorted list
        return participants.indexOf(participant) + 1; // Rank is position in sorted list + 1
    }

    private int calculateReward(int rank) {
        // Calculate reward based on rank
        if (rank == 1) {
            return 10000; // First place
        } else if (rank == 2) {
            return 5000; // Second place
        }
        return 0; // No reward for other ranks
    }

    public Leaderboard getGroupLeaderboard(Long groupId) {
        // TournamentGroup group = groupRepository.findById(groupId).orElseThrow(() ->
        // new IllegalArgumentException("Group not found"));

        List<TournamentParticipant> participants = participantRepository.findByGroupId(groupId);
        participants.sort(Comparator.comparingInt(TournamentParticipant::getScore).reversed());

        List<LeaderboardEntry> entries = new ArrayList<>();
        for (int i = 0; i < participants.size(); i++) {
            TournamentParticipant participant = participants.get(i);
            User user = participant.getUser();
            entries.add(new LeaderboardEntry(user.getId(), user.getCountry(),
                    participant.getScore(), i + 1));
        }

        return new Leaderboard(entries);
    }

    public Leaderboard getCountryLeaderboard() {
        List<CountryScore> countryScores = new ArrayList<>();

        // Aggregate scores by country
        List<Object[]> results = participantRepository.aggregateScoresByCountry();
        for (Object[] result : results) {
            Country country = (Country) result[0];
            int totalScore = ((Number) result[1]).intValue();
            countryScores.add(new CountryScore(country, totalScore));
        }

        // Sort countries by total score
        countryScores.sort(Comparator.comparingInt(CountryScore::getTotalScore).reversed());

        List<LeaderboardEntry> entries = new ArrayList<>();
        for (int i = 0; i < countryScores.size(); i++) {
            CountryScore countryScore = countryScores.get(i);
            entries.add(
                    new LeaderboardEntry(null, countryScore.getCountry(), countryScore.getTotalScore(), i + 1));
        }

        return new Leaderboard(entries);
    }

}
