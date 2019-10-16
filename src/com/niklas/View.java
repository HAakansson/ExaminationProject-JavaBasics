package com.niklas;

import java.util.ArrayList;

public class View {

    private static View instance = null;

    private View() {

    }

    public static View getInstance() {
        if (instance == null)
            instance = new View();
        return instance;
    }

    public enum MainMenuChoice implements Describable {

        EXIT_PROGRAM("Exit program"),
        ADD_PLAYER("Add player"),
        ADD_COACH("Add coach"),
        FIRE_EMPLOYEE("Fire employee"),
        SHOW_EMPLOYEES("Show employees"),
        SHOW_A_SPECIFIC_EMPLOYEE("Show a specific employee"),
        SHOW_STATISTICS("Show statistics"),
        SAVE_TO_FILE("Save employees"),
        SHOW_HELP_PAGE("Show help page");

        private String description;

        MainMenuChoice(String description) {
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    public <E extends Describable> E showMenuAndGetChoice(E[] menuItems) {

        int i = 0;
        int menuChoice;
        System.out.println();

        for (E e : menuItems) {
            System.out.printf("%d: %s\n", i++, e.getDescription());
        }

        System.out.print("\nYour choice: ");
        while (true) {
            menuChoice = HelpUtility.returnsIntAfterErrorCheck();
            if (menuChoice > MainMenuChoice.values().length - 1 || menuChoice < 0) {
                errorMessage("Invalid menu choice. Try again: ");
            } else {
                break;
            }
        }
        System.out.println();
        return menuItems[menuChoice];
    }

    public void showMessage(String message) {
        System.out.printf("%s", message);
    }

    public void errorMessage(String errorMessage) {
        System.out.printf("Error: %s", errorMessage);
    }

    public String[] addInfoToCreationOfEmployee() {

        String info;

        while (true) {
            System.out.print("Enter the following:" +
                    " first name, last name, age, and (depending on if it's a player/coach) " +
                    "position/typeOfCoach. Everything separated by comma (\",\"). Nothing else!\n" +
                    "Your input: ");

            info = HelpUtility.returnsStringAfterErrorCheck();
            String[] infoParts = info.split(",");
            if (infoParts.length != 4) {
                errorMessage("Something went wrong with your input, try again.\n");
            } else {
                return infoParts;
            }
        }
    }

    public String[] addInfoToCreationOfStatistics() {

        String info;

        while (true) {
            System.out.print("Enter the correct integer of the following:\n" +
                    "season, goals, assists, yellow cards, red cards, games. Separated everything by comma (\",\"). Nothing else!\n" +
                    "Your input: ");

            info = HelpUtility.returnsStringAfterErrorCheck();
            String[] infoParts = info.split(",");
            if (infoParts.length != 6) {
                errorMessage("Something went wrong with you input, try again.\n");
            } else {
                return infoParts;
            }
        }
    }

    public String[] getNameOfEmployee() {
        System.out.print("Enter the first name and the last name of the employee (separated by comma): ");
        String name = HelpUtility.returnsStringAfterErrorCheck();
        return name.split(",");
    }

    public void showEmployees(ArrayList<Player> players, ArrayList<Coach> coaches) {

        System.out.println("");

        for (Player player : players) {
            System.out.println(player);
        }
        for (Coach coach : coaches) {
            System.out.println(coach);
        }
    }

}
