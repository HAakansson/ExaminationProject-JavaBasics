package com.niklas;

import java.io.Serializable;

public class Statistics implements Serializable, Comparable<Statistics> {

    private int season;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int games;
    private double goalsRatio;

    private static int sortingChoice;

    public Statistics(int season, int goals, int assists, int yellowCards, int redCards, int games) {
        this.season = season;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.games = games;
        this.goalsRatio = (double) goals / (double) games;
    }

    public static void setSortingChoice(int sortingChoice) {
        Statistics.sortingChoice = sortingChoice;
    }

    @Override
    public String toString() {
        return String.format("Season: %d. Goals: %d. Assists: %d. Yellow Cards: %d. Red Cards: %d. Games: %d. Goals Ratio: %f",season,goals,assists,yellowCards,redCards,games,goalsRatio);
    }

    @Override
    public int compareTo(Statistics o) {
        return 0;
    }
}
