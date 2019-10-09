package com.Niklas;

import java.util.ArrayList;

public class View {

    private static View instance = null;

    private View(){

    }

    public static View getInstance(){
        if (instance == null)
            instance = new View();
        return instance;
    }

    public enum MainMenuChoice {

        EXIT_PROGRAM("Exit program"),
        ADD_PLAYER("Add player"),
        ADD_COACH("Add coach"),
        FIRE_EMPLOYEE("Fire employee"),
        SHOW_EMPLOYEES("Show employees"),
        SHOW_A_SPECIFIC_EMPLOYEE("Show a specific employee"),
        SHOW_STATISTICS("Show statistics"),
        SHOW_HELP_PAGE("Show help page");

        private String description;

        MainMenuChoice(String description) {
            this.description = description;
        }
    }

    public MainMenuChoice showMenu() {

        System.out.println();

        int i = 0;
        int menuChoice;

        for (MainMenuChoice choice : MainMenuChoice.values()) {
            System.out.printf("%d: %s\n",i++,choice.description);
        }
        System.out.print("\nYour choice: ");
        menuChoice = HelpUtility.intWithTryCatch();
        System.out.println();
        return MainMenuChoice.values()[menuChoice];
    }

    public void showMessage(String message) {
        System.out.printf("%s", message);
    }

    public void errorMessage(String errorMessage){
        System.out.printf("Error: %s\n",errorMessage);
    }

    public String addInfoToCreationOfEmployee(){

        String info;

        System.out.print("Enter the following:" +
                " first name, last name, age, and (depending on if it's a player/coach) " +
                "position/typeOfCoach. Everything separated by comma (\",\"). Nothing else!\n" +
                "Your input: ");

        info = HelpUtility.stringInputWithErrorCheck();
        return info;
    }

    public String addInfoToCreationOfStatistics(){
        String info;

        System.out.printn("Enter the correct integer of the following:\n" +
                "season, goals, assists, yellow cards, red cards, games, goals ratio. Separated everything by comma (\",\"). Nothing else!\n" +
                "Your input: ");

        info = HelpUtility.stringInputWithErrorCheck();
        return info;
    }

    public String [] getNameOfEmployeeToRemove(){
        System.out.print("Enter the first name and the last name of the employee you want to fire (separated by comma): ");
        String name = HelpUtility.stringInputWithErrorCheck();
        return name.split(",");
    }

    public void showEmployees(ArrayList<Player> players, ArrayList<Coach> coaches){

        for (Player player : players){
            System.out.println(player);
        }
        for (Coach coach : coaches){
            System.out.println(coach);
        }
    }

}
