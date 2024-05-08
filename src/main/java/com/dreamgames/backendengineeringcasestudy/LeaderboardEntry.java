
package com.dreamgames.backendengineeringcasestudy;

import com.dreamgames.Country;

public class LeaderboardEntry {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LeaderboardEntry(Long userId, Country country, int score, int rank) {
        this.userId = userId;
        this.country = country;
        this.score = score;
        this.rank = rank;
    }

    private int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int tournamentScore;

    public int getTournamentScore() {
        return tournamentScore;
    }

    public void setTournamentScore(int tournamentScore) {
        this.tournamentScore = tournamentScore;
    }

}