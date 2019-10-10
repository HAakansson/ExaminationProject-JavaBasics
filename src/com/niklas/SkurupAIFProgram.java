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
                    break;
                case ADD_COACH:
                    addCoach(EmployeeFactory.EmployeeType.COACH, view.addInfoToCreationOfEmployee());
                    view.showMessage("Coach added!\n");
                    break;
                case FIRE_EMPLOYEE:
                    fireEmployee();
                    break;
                case SHOW_EMPLOYEES:
                    view.showEmployees(playersInClub,coachesInClub);
                    break;
                case SHOW_A_SPECIFIC_EMPLOYEE:
//                    showASpecificEmployee();
                    break;
                case SHOW_STATISTICS:
//                    showStatistics();
                    break;
                case SAVE_TO_FILE:
                    HelpUtility.saveObject(playersInClub,"src/com/files/players.ser", StandardOpenOption.CREATE);
                    HelpUtility.saveObject(coachesInClub,"src/com/files/coaches.ser", StandardOpenOption.CREATE);
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
        Player player = (Player) EmployeeFactory.createEmployee(employeeType, info);
        if (player != null) {
            playersInClub.add(player);
        } else {
            view.errorMessage("Could not add the player. Try again.");
        }
    }

    public void addCoach(EmployeeFactory.EmployeeType employeeType, String info) {
        Coach coach = (Coach) EmployeeFactory.createEmployee(employeeType, info);
        if (coach != null) {
            coachesInClub.add(coach);
        } else {
            view.errorMessage("Could not add the coach. Try again.");
        }
    }

    public void fireEmployee() {

        EmployeeFactory.EmployeeType employeeType;
        int choice = whichEmployeeDepartment();
        int i = 0;
        employeeType = EmployeeFactory.EmployeeType.values()[choice];

        String[] nameToRemoveParts = view.getNameOfEmployeeToRemove();
        String firstName = nameToRemoveParts[0];
        String lastName = nameToRemoveParts[1];

        switch (employeeType) {
            case PLAYER:
                for(i = 0; i < playersInClub.size();i++){
                    if(firstName.equalsIgnoreCase(playersInClub.get(i).getFirstName())){
                        playersInClub.remove(i);
                    }
                }
                view.showMessage(String.format("The player %1$s %2$s has been fired from the squad!\n",firstName,lastName));
                break;
            case COACH:
                for(i = 0; i < coachesInClub.size();i++){
                    if(firstName.equalsIgnoreCase(coachesInClub.get(i).getFirstName())){
                        coachesInClub.remove(i);
                    }
                }
                view.showMessage(String.format("The coach %1$s %2$s has been fired from the squad!\n",firstName,lastName));
                break;
            case NONE:
                view.showMessage("Back to main menu...\n");
                break;
        }
    }

    public int whichEmployeeDepartment(){

        int choice;
        int i = 0;

        view.showMessage("From which employee department?\n");
        for (EmployeeFactory.EmployeeType type : EmployeeFactory.EmployeeType.values()) {
            view.showMessage(String.format("%d: %s\n", i++, type.menuString));
        }
        view.showMessage("\nYour choice: ");

        return HelpUtility.intWithTryCatch();

    }


}
