package com.dreamgames.backendengineeringcasestudy;

import com.dreamgames.Country;

public class CountryScore {
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private int totalScore;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public CountryScore(Country country, int totalScore) {
        this.country = country;
        this.totalScore = totalScore;
    }

}