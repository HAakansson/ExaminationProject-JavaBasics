package com.niklas;

public class StatisticsFactory {

    public static Statistics createStatistics(String [] info){

        int season = Integer.parseInt(info[0]);
        int goals = Integer.parseInt(info[1]);
        int assists = Integer.parseInt(info[2]);
        int yellowCards = Integer.parseInt(info[3]);
        int redCards = Integer.parseInt(info[4]);
        int games = Integer.parseInt(info[5]);

        return new Statistics(season,goals,assists,yellowCards,redCards,games);
    }
}
