import java.util.Scanner;

public class Main {

    static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String adminPassword = "admin1234";

        int choice = 0;

        AccountManagement user = null;

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

                    System.out.println("1: Customer Table\n2: Booking Table\n3: Flight Table\n4: Route Table\n5: Exit");
                    int selection = Integer.parseInt(input.nextLine());

                    if (selection == 1) {
                        System.out.println("1: View Table\n2: Edit Customer\n3: Delete Customer");
                        int adminSelection = Integer.parseInt(input.nextLine());

                        if (adminSelection == 1) {
                            cust1.viewTable();
                        }

                        else if (adminSelection == 2) {
                            cust1.editCustomerData();
                        } else {
                            cust1.deleteCustomerData();
                        }

                    } else if (selection == 2) {
                        System.out.println("1: View Table\n2: Edit Booking\n3: Delete Booking");
                        int adminSelection = Integer.parseInt(input.nextLine());

                    } else if (selection == 3) {
                        System.out.println("1: View Table\n2: Edit Flight\n3: Delete Flight");
                        int adminSelection = Integer.parseInt(input.nextLine());

                    } else if (selection == 4) {
                        System.out.println("1: View Table\n2: Edit Route\n3: Delete Route");
                        int adminSelection = Integer.parseInt(input.nextLine());
                    }

                    else {
                        System.out.println("Exiting system...");
                        System.exit(0);
                    }

                } else {
                    System.out.println("Admin password invalid");
                }

                break;
            } else if (choice == 2) {

                int selection = 0;

                while (selection != 3 && !login) {

                    System.out.println("<--------- CUSTOMER SYSTEM --------->");
                    System.out.println("1: Register\n2: Login\n3: Exit");
                    selection = Integer.parseInt(input.nextLine());

                    while (selection < 1 || selection > 3) {
                        System.out.println("Input not valid, please try again");
                        System.out.println("1: Register\n2: Login\n3: Exit");
                        selection = Integer.parseInt(input.nextLine());
                    }

                    if (selection == 1) {

                        if (!registered) {
                            user = new AccountManagement();

                            user.accountRegister();
                            registered = true;
                        }

                        else {
                            System.out.println("User has already registered, please log in");
                        }

                    } else if (selection == 2) {

                    if (registered) {

                        login = user.accountLogin();

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

                        cust1 = new Customer();

                        boolean customerValid = cust1.getCustomerData();

                        if (customerValid) {
                            cust1.addCustomerData();
                            System.out.println("Customer successfully created!");
                        }

                        else {
                            System.out.println("Customer already exists in file");
                        }

                    } else if (selection == 2) {

                        System.out.println("Booking selected");

                        book1 = new Booking();

                        boolean bookingValid = book1.getBookingData();

                        if (bookingValid) {
                            book1.addBookingData();
                            System.out.println("Booking successfully created!");
                        }

                    } else {

                        System.out.println("Exiting system...");
                        System.exit(0);
                    }
                }
            }
        }
    }
}