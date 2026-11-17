package data;

import java.util.ArrayList;
import java.util.Scanner;

public class GlobalData {

    public static ArrayList<String> credentials = new ArrayList<>();
    public static String loggedInCustomerID;

    public static int getIntegerInput(Scanner input) {

        while (!input.hasNextInt()) {
            System.out.println("Only accepts a number, please try again");
            input.next(); // Remove invalid input
        }

        int choice = input.nextInt();
        input.nextLine(); // Clear leftover newline

        return choice;
    }
}
