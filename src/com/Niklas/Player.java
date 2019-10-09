package com.Niklas;

import java.util.ArrayList;

public class Player extends Employee {

    private String position;
    private ArrayList<Statistics> playerStats = new ArrayList<>();

    public Player(String firstName, String lastName, int age, String position) {
        super(firstName, lastName, age);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public ArrayList<Statistics> getPlayerStats() {
        return playerStats;
    }

    @Override
    public void presentYourself() {
        System.out.printf("Hi! My name is %s %s and I am %d years old and currently I play as a %s!",getFirstName(),getLastName(),getAge(),getPosition());
    }

    @Override
    public String toString() {
        return String.format("Player - Name: %s %s. Position: %s",getFirstName(),getLastName(),getPosition());
    }
}
