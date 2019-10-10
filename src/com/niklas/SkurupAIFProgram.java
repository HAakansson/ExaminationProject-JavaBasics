package com.niklas;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class SkurupAIFProgram {

    ArrayList<Player> playersInClub = new ArrayList<>();
    ArrayList<Coach> coachesInClub = new ArrayList<>();
    private View view;
    public final static int MAX_PLAYERS = 30;
    public final static int MAX_COACHES = 8;

    public SkurupAIFProgram() {
        view = View.getInstance();
    }


    public void start() {

        boolean keepGoing = true;
        EmployeeFactory.EmployeeType employeeType;

        do {

            View.MainMenuChoice choice = View.getInstance().showMenu();

            switch (choice) {
                case EXIT_PROGRAM:
                    keepGoing = false;
                    view.showMessage("We bid you farewell, with hope to see you again!");
                    break;
                case ADD_PLAYER:
                    addPlayer(EmployeeFactory.EmployeeType.PLAYER, view.addInfoToCreationOfEmployee());
                    view.showMessage("Player added!\n");
                    addStatisticsToPlayer();
                    break;
                case ADD_COACH:
                    addCoach(EmployeeFactory.EmployeeType.COACH, view.addInfoToCreationOfEmployee());
                    view.showMessage("Coach added!\n");
                    break;
                case FIRE_EMPLOYEE:
                    fireEmployee();
                    break;
                case SHOW_EMPLOYEES:
                    view.showMessage("The following employees work for the club:\n");
                    if (playersInClub.size() == 0) {
                        view.showMessage("\nNo players play for the club at the moment.\n");
                    }
                    if (coachesInClub.size() == 0) {
                        view.showMessage("No coaches are employed by the club at the moment.");
                    }
                    view.showEmployees(playersInClub, coachesInClub);
                    break;
                case SHOW_A_SPECIFIC_EMPLOYEE:
                    showASpecificEmployee();
                    break;
                case SHOW_STATISTICS:
                    showStatistics();
                    break;
                case SAVE_TO_FILE:
                    HelpUtility.saveObject(playersInClub, "src/com/files/players.ser", StandardOpenOption.CREATE);
                    HelpUtility.saveObject(coachesInClub, "src/com/files/coaches.ser", StandardOpenOption.CREATE);
                    view.showMessage("Employees have been saved to system.\n");
                    break;
                case LOAD_FROM_FILE:
                    playersInClub = (ArrayList<Player>) HelpUtility.loadObject("src/com/files/players.ser");
                    coachesInClub = (ArrayList<Coach>) HelpUtility.loadObject("src/com/files/coaches.ser");
                    view.showMessage("The employees have been loaded from the system.\n");
                    break;
                case SHOW_HELP_PAGE:
                    HelpUtility.helpText();
                    break;
                default:
                    view.errorMessage("Invalid choice. You may only enter an integer between 0-8.");
            }
        } while (keepGoing);

    }

    public void addPlayer(EmployeeFactory.EmployeeType employeeType, String info) {

        if (playersInClub.size() == MAX_PLAYERS) {
            view.showMessage("I am sorry, the squad is full.");
        } else {
            Player player = (Player) EmployeeFactory.createEmployee(employeeType, info);
            if (player != null) {
                playersInClub.add(player);
            } else {
                view.errorMessage("Could not add the player. Try again.");
            }
        }

    }

    public void addCoach(EmployeeFactory.EmployeeType employeeType, String info) {

        if (coachesInClub.size() == MAX_COACHES) {
            view.showMessage("I am sorry, the coach team is full.");
        } else {
            Coach coach = (Coach) EmployeeFactory.createEmployee(employeeType, info);
            if (coach != null) {
                coachesInClub.add(coach);
            } else {
                view.errorMessage("Could not add the coach. Try again.");
            }
        }

    }

    public void addStatisticsToPlayer() {

        view.showMessage("\n");

        boolean addMoreStatistics = true;
        String choiceAdd;
        String choiceAddMore;

        view.showMessage("Do you want to add statistics (y/n)?\n" +
                "Your choice: ");

        choiceAdd = HelpUtility.onlyYesOrNo();


        if (choiceAdd.equalsIgnoreCase("y")) {

            do {

                view.showMessage("\n");
                Statistics statistics = StatisticsFactory.createStatistics(view.addInfoToCreationOfStatistics());
                playersInClub.get(playersInClub.size() - 1).getPlayerStats().add(statistics);
                view.showMessage("Statistics added!\n" +
                        "\nDo you want to add more (y/n)?\n" +
                        "Your choice: ");

                choiceAddMore = HelpUtility.onlyYesOrNo();

                if (choiceAddMore.equalsIgnoreCase("n"))
                    addMoreStatistics = false;

            } while (addMoreStatistics);

        } else {

            view.showMessage("Okay, back to main menu...\n");
        }

    }

    public void fireEmployee() {

        int choice = decideWhichEmployeeDepartment();
        EmployeeFactory.EmployeeType employeeType = EmployeeFactory.EmployeeType.values()[choice];
        String[] nameToRemoveParts;
        String firstName;
        String lastName;
        int indexReturned;

        switch (employeeType) {
            case PLAYER:
                nameToRemoveParts = view.getNameOfEmployee();
                firstName = nameToRemoveParts[0];
                lastName = nameToRemoveParts[1];
                indexReturned = checkIfPlayerPlaysForClub(firstName, lastName);

                if (indexReturned == -1) {
                    view.showMessage("The player with that name does not play for the club.\n");
                } else {
                    playersInClub.remove(indexReturned);
                    view.showMessage(String.format("The player %1$s %2$s has been fired from the squad!\n", firstName, lastName));
                }
                break;
            case COACH:
                nameToRemoveParts = view.getNameOfEmployee();
                firstName = nameToRemoveParts[0];
                lastName = nameToRemoveParts[1];
                indexReturned = checkIfCoachCoachesForClub(firstName, lastName);

                if (indexReturned == -1) {
                    view.showMessage("The coach with that name does not play for the club.\n");
                } else {
                    playersInClub.remove(indexReturned);
                    view.showMessage(String.format("The coach %1$s %2$s has been fired from the squad!\n", firstName, lastName));
                }
                break;
            case NONE:
                view.showMessage("Back to main menu...\n");
                break;
        }

    }

    public int decideWhichEmployeeDepartment() {

        int choice;
        int i = 0;

        view.showMessage("From which employee department?\n");
        for (EmployeeFactory.EmployeeType type : EmployeeFactory.EmployeeType.values()) {
            view.showMessage(String.format("%d: %s\n", i++, type.menuString));
        }
        view.showMessage("\nYour choice: ");

        return HelpUtility.intWithTryCatch();

    }

    public int checkIfPlayerPlaysForClub(String firstName, String lastName) {

        int i = 0;
        for (Player player : playersInClub) {
            if (firstName.equalsIgnoreCase(player.getFirstName()) && lastName.equalsIgnoreCase(player.getLastName())) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    public int checkIfCoachCoachesForClub(String firstName, String lastName) {

        int i = 0;
        for (Coach coach : coachesInClub) {
            if (firstName.equalsIgnoreCase(coach.getFirstName()) && lastName.equalsIgnoreCase(coach.getLastName())) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    public void showASpecificEmployee() {

        int choice = decideWhichEmployeeDepartment();
        EmployeeFactory.EmployeeType employeeType = EmployeeFactory.EmployeeType.values()[choice];

        String[] nameParts = view.getNameOfEmployee();
        String firstName = nameParts[0];
        String lastName = nameParts[1];
        int indexReturned;

        switch (employeeType) {
            case PLAYER:
                indexReturned = checkIfPlayerPlaysForClub(firstName, lastName);
                playersInClub.get(indexReturned).presentYourself();
                break;
            case COACH:
                indexReturned = checkIfCoachCoachesForClub(firstName, lastName);
                coachesInClub.get(indexReturned).presentYourself();
                break;
            case NONE:
                view.showMessage("Back to main menu...\n");
                break;
        }
    }

    public void showStatistics() {

        String[] nameParts = view.getNameOfEmployee();

        if (nameParts.length == 2) {

            String firstName = nameParts[0];
            String lastName = nameParts[1];

            int indexReturned = checkIfPlayerPlaysForClub(firstName, lastName);
            view.showMessage(String.format("\nPlayer: %s %s:\n", firstName, lastName));
            for (Statistics stats : playersInClub.get(indexReturned).getPlayerStats()) {
                view.showMessage(stats.toString() + "\n");
            }
        } else {
            view.showMessage("Wrong input. Try again.\n");
        }
    }

}
