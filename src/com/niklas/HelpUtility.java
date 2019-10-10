package com.niklas;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class HelpUtility {

    static Scanner input = new Scanner(System.in);

    public static void saveObject(Object o, String fileName, StandardOpenOption... option) {
        Path path = Paths.get(fileName);
        try (ObjectOutputStream out = new ObjectOutputStream((Files.newOutputStream(path, option)))) {
            out.writeObject(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String fileName) {
        Path path = Paths.get(fileName);
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            return in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int intWithTryCatch() {

        int integer;
        while (true) {
            try {
                integer = Integer.parseInt(input.nextLine());
                return integer;
            } catch (Exception e) {
                System.out.print("Wrong input. You may only enter an integer. Try again: ");
            }
        }
    }

    public static String stringInputWithErrorCheck() {

        String str;
        while (true) {
            str = input.nextLine();
            if (isOnlyLettersNumbersComasAndSpaces(str)) {
                break;
            } else {
                System.out.print("Wrong input. You  may only enter alphabetic letters (a-z and å,ä,ö) or commas. Try again: ");
            }
        }
        return str;

    }

    public static boolean isOnlyLettersNumbersComasAndSpaces(String str) {

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch) || Character.isDigit(ch) || ch == ',' || ch == ' ') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static String onlyYesOrNo(){

        String str;

        while(true){

            str = input.nextLine();

            if(str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n")){
                return str;
            } else{
                View.getInstance().showMessage("Wrong input, you may only enter y/n. Try again: ");
            }
        }

    }

    public static void helpText(){
        System.out.println();
        System.out.println("Here it is supposed to be some help text, but no..");
        System.out.println();
    }

}
