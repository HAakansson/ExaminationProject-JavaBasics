package com.Niklas;

public class EmployeeFactory {

    public enum EmployeeType {

        NONE("Back to Main menu"),
        PLAYER("Player"),
        COACH("Coach");

        public String menuString;

        EmployeeType(String menuString) {
            this.menuString = menuString;
        }
    }

    public static Employee createEmployee(EmployeeType employeeType, String info){

        String [] infoParts = info.split(",");
        String firstName = infoParts[0];
        String lastName = infoParts[1];
        int age = Integer.parseInt(infoParts[2]);
        String positionOrTypeOfCoach = infoParts[3];

        switch (employeeType){

            case PLAYER:
                return new Player(firstName,lastName,age,positionOrTypeOfCoach);
            case COACH:
                return new Coach(firstName,lastName,age,positionOrTypeOfCoach);
            case NONE:
            default:
                return null;
        }
    }

}
