import java.util.Scanner;

public class Main {

    static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String adminPassword = "admin1234";

        int choice = 0;

        AccountSystem user = null;

        boolean registered = false;
        boolean login = false;

        Customer cust1 = new Customer();
        Booking book1 = new Booking();

        while (choice != 3) {

            System.out.println("<--------- FLIGHT BOOKING SYSTEM --------->");
            System.out.println("1: Admin Login\n2: User Area\n3: Exit");

            choice = Integer.parseInt(input.nextLine());

            while (choice < 1 || choice > 3) {
                System.out.println("Invalid input, please try again");
                System.out.println("1: Admin Login\n2: User Area\n3: Exit");
                choice = Integer.parseInt(input.nextLine());
            }

            if (choice == 1) {

                System.out.print("Enter the admin password: ");
                String enteredPassword = input.nextLine();

                if (enteredPassword.equals(adminPassword)) {

                    System.out.println("Admin password correct");

                    System.out.println("1: Customer Table\n2: Booking Table\n3: Flight Table\n4: Route Table");
                    int selection = Integer.parseInt(input.nextLine());

                    if (selection == 1) {
                        System.out.println("1: Edit customer\n2: Delete Customer");
                        int adminSelection = Integer.parseInt(input.nextLine());

                        if (adminSelection == 1) {
                            cust1.editCustomerData();
                        } else {
                            cust1.deleteCustomerData();
                        }
                    } else if (selection == 2) {
                        System.out.println("Option 2 Coming Soon");
                    } else if (selection == 3) {
                        System.out.println("Option 3 Coming Soon");
                    } else {
                        System.out.println("Option 4 Coming Soon");
                    }
                } else {
                    System.out.println("Admin password invalid");
                }

                break;
            } else if (choice == 2) {

                int selection = 0;

                while (selection != 3 && !login) {

                    System.out.println("1: Register\n2: Login\n3: Exit");
                    selection = Integer.parseInt(input.nextLine());

                    while (selection < 1 || selection > 3) {
                        System.out.println("Input not valid, please try again");
                        System.out.println("1: Register\n2: Login\n3: Exit");
                        selection = Integer.parseInt(input.nextLine());
                    }

                    if (selection == 1) {

                        user = new AccountSystem();

                        user.accountRegister();
                        registered = true;

                    } else if (selection == 2) {

                        if (registered) {
                            user.accountLogin();
                            login = true;
                        } else {
                            System.out.println("User has not registered yet");
                        }

                    } else {
                        System.out.println("Exiting system...");
                        System.exit(0);
                    }
                }

                // Customer / Booking / Exit menu
                while (login) {

                    System.out.println("1: Customer\n2: Booking\n3: Exit");
                    selection = Integer.parseInt(input.nextLine());

                    while (selection < 1 || selection > 3) {
                        System.out.println("Input not valid, please try again");
                        System.out.println("1: Customer\n2: Booking\n3: Exit");
                        selection = Integer.parseInt(input.nextLine());
                    }

                    if (selection == 1) {

                        System.out.println("Customer selected");

                        cust1.getCustomerData();
                        cust1.addCustomerData();

                    } else if (selection == 2) {

                        System.out.println("Booking selected");

                        book1.getBookingData();
                        book1.addBookingData();

                    } else {

                        System.out.println("Exiting system...");
                        System.exit(0);
                    }
                }
            }
        }
    }
}