package com.dreamgames.backendengineeringcasestudy;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class TournamentParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private TournamentGroup tournamentGroup;

    public TournamentGroup getTournamentGroup() {
        return tournamentGroup;
    }

    public void setTournamentGroup(TournamentGroup tournamentGroup) {
        this.tournamentGroup = tournamentGroup;
    }

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private boolean rewardsClaimed;

    public TournamentParticipant(User user, TournamentGroup tournamentGroup, int score,
            boolean rewardsClaimed) {

        this.user = user;
        this.tournamentGroup = tournamentGroup;
        this.score = score;
        this.rewardsClaimed = rewardsClaimed;
    }

    public boolean getRewardsClaimed() {
        return rewardsClaimed;
    }

    public void setRewardsClaimed(boolean rewardsClaimed) {
        this.rewardsClaimed = rewardsClaimed;
    }

}
