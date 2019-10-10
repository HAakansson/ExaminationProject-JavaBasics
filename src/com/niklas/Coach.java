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

        System.out.printf("Hi! My name is %s %s and I am %d years old and currently I work as %s!\n",getFirstName(),getLastName(),getAge(),getTypeOfCoach());

    }

    @Override
    public String toString() {
        return String.format("Coach - Name: %s %s. Typ of Coach: %s",getFirstName(),getLastName(),getTypeOfCoach());
    }
}
