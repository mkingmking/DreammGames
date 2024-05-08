package com.dreamgames.backendengineeringcasestudy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dreamgames.Country;

public class Leaderboard {
    private List<LeaderboardEntry> entries;

    public Leaderboard(List<LeaderboardEntry> entries) {
        this.entries = entries;
    }

    public List<LeaderboardEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<LeaderboardEntry> entries) {
        this.entries = entries;
    }

    public Map<Country, Integer> aggregateScoresByCountry() {
        Map<Country, Integer> countryScores = new HashMap<>();

        for (LeaderboardEntry entry : entries) {
            Country country = entry.getCountry();
            int score = entry.getTournamentScore();

            // Update the score for the country
            countryScores.put(country, countryScores.getOrDefault(country, 0) + score);
        }

        return countryScores;
    }
}
