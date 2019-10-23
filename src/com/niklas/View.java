package com.niklas;

import java.util.ArrayList;

/**
 * <h1 style="color:goldenrod;">My View Class</h1>
 * <p>
 * The view class is central to the program itself since it handles all the inputs from the
 * user as well as all the print-outs. What is also special about this class, is that it is a
 * "Singleton Class".
 * </p>
 *
 * @author Niklas Håkansson
 */
public class View {

    private static View instance = null;

    private View() {

    }

    /**
     * <h2 style="color:goldenrod;">The getInstance-Method</h2>
     * <p>
     * The method that instantiates the View Class. If the programs tries to create a new instance of the view class,
     * the new variable will also point to the already existing one.
     * </p>
     *
     * @return The instance of the View Class or the already existing one.
     */
    public static View getInstance() {
        if (instance == null)
            instance = new View();
        return instance;
    }

    /**
     * <h2 style="color:goldenrod;">The Enum Class "MainMenuChoice".</h2>
     * <p>
     * Contains the menu choices in the form af enums that I use through out the program. The enums also
     * have a string variable connected to them in order to make the menu choices more pleasant to read.
     * </p>
     */
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

    /**
     * <h2 style="color:goldenrod;">The show menu method.</h2>
     * <p>
     * A very important method that is generic which means it can show any type of menu, which is also the
     * purpose here. It also handles the input from the user to deduce which menu choice is to be executed.
     * </p>
     *
     * @param menuItems An Array of type E[].
     * @param <E>       The unknown type of menu choices.
     * @return The menu choice of type <E>.
     */
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
        return menuItems[menuChoice];
    }

    /**
     * <h2 style="colore:goldenrod;">Show message method</h2>
     * <p>
     * The view class handles all the print outs via this method. If a method in another class wants to make a
     * print out, this method is always called.
     * </p>
     *
     * @param message type String
     */
    public void showMessage(String message) {
        System.out.printf("%s", message);
    }

    /**
     * <h2 style="color:goldenrod;">Error show message method</h2>
     * <p>
     * This method is basically the same as the method above, it just adds an error to the message.
     * </p>
     *
     * @param errorMessage type String
     */
    public void errorMessage(String errorMessage) {
        System.out.printf("Error: %s", errorMessage);
    }

    /**
     * <h2 style="color:goldenrod;">Add info to the creation of employees.</h2>
     * <p>
     * This method is crucial to creation process of employees. In order for the employee factory
     * to create a player or a coach, it needs the additional info regarding name, age and so on. Since the view
     * class handles all the input from the user, the method is placed here and called upon to return a String
     * array with the essential info.
     * </p>
     *
     * @return type String []
     */
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

    /**
     * <h2 style="color:goldenrod;">Add info to the creation of statistics.</h2>
     * <p>
     *     This method works similarly to the method above. When you want to add statistics to a player, you need
     *     info regarding the amount af goals, games an so on. This method takes care of that and returns the information
     *     as a String [] to the statistics factory.
     * </p>
     *
     * @return type String []
     */
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

    /**
     * <h2 style="color:goldenrod;">Get the name of employee.</h2>
     * <p>
     *      In order to show a specific employee or statistics, you need the name of the employee.
     *      This tiny method handles that and gets input from the user and sends it back.
     * </p>
     *
     * @return type String []
     */
    public String[] getNameOfEmployee() {
        System.out.print("Enter the first name and the last name of the employee (separated by comma): ");
        String name = HelpUtility.returnsStringAfterErrorCheck();
        return name.split(",");
    }

    /**
     * <h2 style="color:goldenrod;">Show employees</h2>
     * <p>
     *      A method that prints out either the players or the coaches tp the console depending on the choice of
     *      the user.
     * </p>
     *
     * @param players type ArrayList<Players>
     * @param coaches type ArrayList<Coaches>
     */
    public void showEmployees(ArrayList<Player> players, ArrayList<Coach> coaches) {

        System.out.print("Do you want to show players or coaches?");
        EmployeeFactory.EmployeeType type = showMenuAndGetChoice(EmployeeFactory.EmployeeType.values());

        switch (type) {
            case PLAYER:
                if (players != null) {
                    if (players.size() != 0) {
                        System.out.println("The following players play for the club:\n");
                        for (Player player : players) {
                            System.out.println(player);
                        }
                    } else {
                        System.out.println("No players play for the club at the moment.");
                    }
                } else {
                    System.out.println("No players play for the club at the moment.");
                }
                break;

            case COACH:
                if (coaches != null) {
                    if(coaches.size() != 0) {
                        System.out.println("The following coaches coach for the club:\n");
                        for (Coach coach : coaches) {
                            System.out.println(coach);
                        }
                    } else {
                        System.out.println("No coaches coach for the club at the moment.");
                    }
                } else {
                    System.out.println("No coaches coach for the club at the moment.");
                }
                break;

            case NONE:
                System.out.println("Back to main menu...");
                break;
        }
    }

}
