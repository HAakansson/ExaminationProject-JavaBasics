package com.niklas;

import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SkurupAIFProgram {

    private ArrayList<Player> playersInClub = new ArrayList<>();
    private ArrayList<Coach> coachesInClub = new ArrayList<>();
    private View view;
    public final static int MAX_PLAYERS = 30;
    public final static int MAX_COACHES = 8;

    public ArrayList<Player> getPlayersInClub() {
        return playersInClub;
    }

    public ArrayList<Coach> getCoachesInClub() {
        return coachesInClub;
    }

    public SkurupAIFProgram() {
        view = View.getInstance();
        loadFromFile();
    }

    public void start() {

        boolean keepGoing = true;
        EmployeeFactory.EmployeeType employeeType;

        do {

            View.MainMenuChoice choice = view.showMenuAndGetChoice(View.MainMenuChoice.values());

            switch (choice) {
                case EXIT_PROGRAM:
                    keepGoing = false;
                    view.showMessage("We bid you farewell, with hope to see you again!");
                    break;
                case ADD_PLAYER:
                    addEmployee(playersInClub, EmployeeFactory.EmployeeType.PLAYER, view.addInfoToCreationOfEmployee(), MAX_PLAYERS);
                    view.showMessage("Player added!\n");
                    addStatisticsToPlayer();
                    break;
                case ADD_COACH:
                    addEmployee(coachesInClub, EmployeeFactory.EmployeeType.COACH, view.addInfoToCreationOfEmployee(), MAX_COACHES);
                    view.showMessage("Coach added!\n");
                    break;
                case FIRE_EMPLOYEE:
                    fireEmployee();
                    break;
                case SHOW_EMPLOYEES:
                    view.showEmployees(playersInClub, coachesInClub);
                    break;
                case SHOW_A_SPECIFIC_EMPLOYEE:
                    showASpecificEmployee();
                    break;
                case SHOW_STATISTICS:
                    showStatistics();
                    break;
                case SAVE_TO_FILE:
                    saveToFile();
                    break;
                case SHOW_HELP_PAGE:
                    HelpUtility.helpText();
                    break;
                default:
                    view.errorMessage("Invalid choice. You may only enter an integer between 0-8.");
            }
        } while (keepGoing);

    }

    public <E extends Collection, E1> void addEmployee(E employeeList, E1 employeeType, String[] info, int maxEmployees) {

        if (employeeList.size() == maxEmployees) {
            view.errorMessage("Sorry, there are no more room for additional signings. Come back next season.");
        } else {
            employeeList.add(EmployeeFactory.createEmployee((EmployeeFactory.EmployeeType) employeeType, info));
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

        view.showMessage("Do you want to fire an employee from the players or the coaches?");
        EmployeeFactory.EmployeeType employeeType = view.showMenuAndGetChoice(EmployeeFactory.EmployeeType.values());

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

        EmployeeFactory.EmployeeType employeeType = view.showMenuAndGetChoice(EmployeeFactory.EmployeeType.values());
        String[] nameParts = view.getNameOfEmployee();
        String firstName = nameParts[0];
        String lastName = nameParts[1];
        int indexReturned;

        switch (employeeType) {
            case PLAYER:
                if (playersInClub != null) {
                    indexReturned = checkIfPlayerPlaysForClub(firstName, lastName);
                    if (indexReturned != -1) {
                        playersInClub.get(indexReturned).presentYourself();
                    } else {
                        view.errorMessage("The player with that name does not play for the club. Back to main menu..\n");
                    }
                } else {
                    view.errorMessage("There are no players playing for the club at the moment. Back to main menu..\n");
                }
                break;
            case COACH:
                if (coachesInClub != null) {
                    indexReturned = checkIfCoachCoachesForClub(firstName, lastName);
                    if (indexReturned != -1) {
                        coachesInClub.get(indexReturned).presentYourself();
                    } else {
                        view.errorMessage("The coach with that name does not coach for the club. Back to main menu..\n");
                    }
                } else {
                    view.errorMessage("There are no coaches coaching for the club at the moment. Back to main menu..\n");
                }
                break;
            case NONE:
                view.showMessage("Back to main menu...\n");
                break;
        }
    }

    public void showStatistics() {

        if (playersInClub != null) {

            String[] nameParts = view.getNameOfEmployee();

            if (nameParts.length == 2) {

                String firstName = nameParts[0];
                String lastName = nameParts[1];

                int indexReturned = checkIfPlayerPlaysForClub(firstName, lastName);

                if (indexReturned != -1) {
                    ArrayList<Statistics> temporaryStatsListForPlayer = playersInClub.get(indexReturned).getPlayerStats();

                    do {
                        Collections.sort(temporaryStatsListForPlayer);

                        view.showMessage(String.format("\nPlayer - %s %s:\n", firstName, lastName));
                        for (Statistics stats : playersInClub.get(indexReturned).getPlayerStats()) {
                            view.showMessage(stats.toString() + "\n");
                        }

                        view.showMessage("\nSort by: (1) Season, (2) Goals, (3) Assists, (4) Yellow Cards, (5) Red Cards, (6) Games, (7) Goals Ratio, (0) Exit back to Main Menu.\n" +
                                "Your choice: ");

                        Statistics.sortingChoice = HelpUtility.returnsIntAfterErrorCheck();

                    } while (Statistics.sortingChoice != 0);

                    view.showMessage("Okay, back to main menu...\n");
                } else {
                    view.errorMessage("The player does not play for the club.");
                }

            } else {
                view.showMessage("Wrong input. Try again.\n");
            }
        } else {
            view.errorMessage("There are no players playing for the club at the moment. Back to main menu..\n");
        }
    }

    public void loadFromFile() {

        playersInClub = (ArrayList<Player>) HelpUtility.loadObject("src/com/niklas/players.ser");
        coachesInClub = (ArrayList<Coach>) HelpUtility.loadObject("src/com/niklas/coaches.ser");

        if (playersInClub == null) {
            view.errorMessage("Files with players could not be loaded. Report to nearest awesome hacker!\n");
            playersInClub = new ArrayList<>();
        } else {
            view.showMessage("The players have been loaded from the system.\n");
        }
        if (coachesInClub == null) {
            view.errorMessage("Files with coaches could not be loaded. Report to nearest awesome hacker!\n");
            coachesInClub = new ArrayList<>();
        } else {
            view.showMessage("The coaches have been loaded from the system.\n");
        }

    }

    public void saveToFile() {

        File filePlayers = new File("src/com/niklas/players.ser");
        File fileCoaches = new File("src/com/niklas/coaches.ser");

        if (filePlayers.canWrite()) {
            HelpUtility.saveObject(playersInClub, "src/com/niklas/players.ser", StandardOpenOption.CREATE);
            view.showMessage("The players have been saved to system.\n");
        } else {
            view.errorMessage("The file of players is ReadOnly, you can not save to this file. Back to main menu..");
        }

        if(fileCoaches.canWrite()){
            HelpUtility.saveObject(coachesInClub, "src/com/niklas/coaches.ser", StandardOpenOption.CREATE);
            view.showMessage("The coaches have been saved to system.\n");
        } else {
            view.errorMessage("The file of coaches is ReadOnly, you can not save to this file. Back to main menu..\n");
        }

    }

}
