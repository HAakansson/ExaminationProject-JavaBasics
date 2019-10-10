package com.niklas;

public class StatisticsFactory {

    public static Statistics createStatistics(String info){

        String [] infoParts = info.split(",");
        int season = Integer.parseInt(infoParts[0]);
        int goals = Integer.parseInt(infoParts[1]);
        int assists = Integer.parseInt(infoParts[2]);
        int yellowCards = Integer.parseInt(infoParts[3]);
        int redCards = Integer.parseInt(infoParts[4]);
        int games = Integer.parseInt(infoParts[5]);
        double goalsRatio = (double)(goals/games);

        return new Statistics(season,goals,assists,yellowCards,redCards,games,goalsRatio);
    }
}
