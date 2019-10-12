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

    public static int sortingChoice;

    public Statistics(int season, int goals, int assists, int yellowCards, int redCards, int games) {
        this.season = season;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.games = games;
        this.goalsRatio = (double) goals / (double) games;
    }

    @Override
    public String toString() {
        return String.format("Season: %d. Goals: %d. Assists: %d. Yellow Cards: %d. Red Cards: %d. Games: %d. Goals Ratio: %f",season,goals,assists,yellowCards,redCards,games,goalsRatio);
    }

    @Override
    public int compareTo(Statistics stats) {

        switch(sortingChoice){

            case 2:
                return -(goals - stats.goals);
            case 3:
                return -(assists - stats.assists);
            case 4:
                return -(yellowCards - stats.yellowCards);
            case 5:
                return -(redCards - stats.redCards);
            case 6:
                return -(games - stats.games);
            case 7:
                double temp = goalsRatio - stats.goalsRatio;

                if(temp < 0){
                    return 1;
                } else if (temp > 0){
                    return -1;
                } else {
                    return 0;
                }

            default:
                return -(season - stats.season);
        }
    }

}
