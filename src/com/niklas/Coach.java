package com.niklas;

public class Coach extends Employee {

    private String typeOfCoach;

    public Coach(String firstName, String lastName, int age, String typeOfCoach) {
        super(firstName, lastName, age);
        this.typeOfCoach = typeOfCoach;
    }

    public String getTypeOfCoach() {
        return typeOfCoach;
    }

    @Override
    public void presentYourself() {

    }

    @Override
    public String toString() {
        return String.format("Coach - Name: %s %s. Typ of Coach: %s",getFirstName(),getLastName(),getTypeOfCoach());
    }
}
