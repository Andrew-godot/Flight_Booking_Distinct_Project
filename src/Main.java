// Imports the four classes from directory tables
import tables.Booking;
import tables.Customer;
import tables.Flight;
import tables.Route;

import java.util.Scanner; // Imports the Scanner class from java utilities

import static data.GlobalData.getIntegerInput; // Imports the static getIntegerInput method from class GlobalData.java

// MAIN CLASS
public class Main {

    static void main(String[] args) {

        Scanner input = new Scanner(System.in); // Creates a scanner object named input

        String adminPassword = "admin1234"; // Sets the adminPassword as admin1234

        int choice = 0; // Initializes variable choice as 0

        // Initializes boolean variables
        boolean registered = false; // Used for checking if the user has registered or not
        boolean login = false; // Used for checking if the user has logged in or not

        // ----- MENU SYSTEMS -----

        while (choice != 3) { // While loop will run while the choice variable is not 3

            // MAIN MENU SYSTEM
            System.out.println("<--------- FLIGHT BOOKING SYSTEM --------->");
            System.out.println("1: Admin Login\n2: User Area\n3: Exit");

            choice = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

            // Runs this code if the user input is greater than 1 or less than 3
            while (choice < 1 || choice > 3) {
                System.out.println("Invalid input, please try again");
                System.out.println("1: Admin Login\n2: User Area\n3: Exit");
                choice = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter
            }

            // Admin Login
            if (choice == 1) {

                System.out.print("Enter the admin password: ");
                String enteredPassword = input.nextLine();

                if (enteredPassword.equals(adminPassword)) { // Checks if enteredPassword is equal to the adminPassword

                    System.out.println("Admin password correct");

                    int selection = 0;

                    // Creates an admin object for each class for the database
                    Customer customerAdmin = new Customer();
                    Booking bookingAdmin = new Booking();
                    Flight flightAdmin = new Flight();
                    Route routeAdmin = new Route();

                    // ----- ADMIN -----

                    while (selection != 5) { // While loop will run while the selection variable is not 5

                        // ADMIN MENU SYSTEM
                        System.out.println("<--------- ADMIN TABLE MENU --------->");
                        System.out.println("1: Customer Table\n2: Booking Table\n3: Flight Table\n4: Route Table\n5: Exit");

                        selection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

                        if (selection == 1) {

                            int adminSelection = 0; // Initializes variable adminSelection as 0

                            while (adminSelection != 4) { // While loop will run while the adminSelection variable is not 4

                                // ADMIN CUSTOMER SYSTEM
                                System.out.println("<--------- ADMIN CUSTOMER TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Customer\n3: Delete Customer\n4: Back");

                                adminSelection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

                                // ADMIN CUSTOMER ACTIONS
                                if (adminSelection == 1) {
                                    customerAdmin.viewTable(); // Calls method viewTable from the customer class

                                } else if (adminSelection == 2) {
                                    customerAdmin.editCustomerData(); // Calls method editCustomerData from the customer class

                                } else if (adminSelection == 3) {
                                    customerAdmin.deleteCustomerData(); // Calls method deleteCustomerData from the customer class
                                }
                            }

                        } else if (selection == 2) {

                            int adminSelection = 0;

                            while (adminSelection != 4) {

                                // ADMIN BOOKING SYSTEM
                                System.out.println("<--------- ADMIN BOOKING TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Booking\n3: Delete Booking\n4: Back");

                                adminSelection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

                                // ADMIN BOOKING ACTIONS
                                if (adminSelection == 1) {
                                    bookingAdmin.viewTable(); // Calls method viewTable from the booking class

                                } else if (adminSelection == 2) {
                                    bookingAdmin.editBookingData(); // Calls method editBookingData from the booking class

                                } else if (adminSelection == 3) {
                                    bookingAdmin.deleteBookingData(); // Calls method deleteBookingData from the booking class
                                }
                            }

                        } else if (selection == 3) {

                            int adminSelection = 0;

                            while (adminSelection != 5) {

                                // ADMIN FLIGHT SYSTEM
                                System.out.println("<--------- ADMIN FLIGHT TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Flight\n3: Delete Flight\n4: Add Flight\n5: Back");

                                adminSelection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

                                // ADMIN FLIGHT ACTIONS
                                if (adminSelection == 1) {
                                    flightAdmin.viewTable(); // Calls method viewTable from the flight class

                                } else if (adminSelection == 2) {
                                    flightAdmin.editFlightData(); // Calls method editFlightData from the flight class

                                } else if (adminSelection == 3) {
                                    flightAdmin.deleteFlightData(); // Calls method deleteFlightData from the flight class

                                } else if (adminSelection == 4) {
                                    flightAdmin.addFlightData(); // Calls method addFlightData from the flight class
                                }
                            }

                        } else if (selection == 4) {

                            int adminSelection = 0;

                            while (adminSelection != 5) {

                                // ADMIN ROUTE SYSTEM
                                System.out.println("<--------- ADMIN ROUTE TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Route\n3: Delete Route\n4: Add Route\n5: Back");

                                adminSelection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

                                // ADMIN ROUTE ACTIONS
                                if (adminSelection == 1) {
                                    routeAdmin.viewTable(); // Calls method viewTable from the route class

                                } else if (adminSelection == 2) {
                                    routeAdmin.editRouteData(); // Calls method editRouteData from the route class

                                } else if (adminSelection == 3) {
                                    routeAdmin.deleteRouteData(); // Calls method deleteRouteData from the route class

                                } else if (adminSelection == 4) {
                                    routeAdmin.addRouteData(); // Calls method addRouteData from the route class
                                }
                            }
                        }
                    }

                } else {
                    System.out.println("Admin password invalid"); // Displays only if admin password entered does not match adminPassword variable
                }

                break;

                // ----- CUSTOMER -----

            } else if (choice == 2) {

                int selection = 0;

                while (selection != 3 && !login) {

                    Customer cust = new Customer();

                    // CUSTOMER MENU SYSTEM
                    System.out.println("<--------- CUSTOMER SYSTEM --------->");
                    System.out.println("1: Register\n2: Login\n3: Exit");
                    selection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

                    while (selection < 1 || selection > 3) {
                        System.out.println("Input not valid, please try again");
                        System.out.println("1: Register\n2: Login\n3: Exit");
                        selection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter
                    }

                    if (selection == 1) {

                        if (!registered) {

                            cust = new Customer();

                            boolean customerRegistered = cust.customerRegister();

                            if (!customerRegistered) {

                                registered = cust.getCustomerData();

                                if (registered) {
                                    cust.addCustomerData();
                                }
                            }
                        }

                    } else if (selection == 2) {
                        login = cust.customerLogin();

                    } else {

                        // Exits the system with status 0
                        System.out.println("Exiting system...");
                        System.exit(0);
                    }
                }

                // ----- BOOKING -----

                while (login) {

                    Booking book = new Booking();

                    // BOOKING MENU SYSTEM
                    System.out.println("<--------- BOOKING SYSTEM --------->");
                    System.out.println("1: Create Booking(s)\n2: View Booking(s)\n3: Exit\n");
                    selection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter

                    while (selection < 1 || selection > 3) {
                        System.out.println("Input not valid, please try again");
                        System.out.println("1: Create Booking(s)\n2: View Booking(s)\n3: Exit\n");
                        selection = getIntegerInput(input); // Calls method getIntegerInput with input being passed in as parameter
                    }

                    if (selection == 1) {

                        book = new Booking();

                        boolean bookingValid = book.getBookingData();

                        if (bookingValid) {
                            book.addBookingData();
                            System.out.println("Booking successfully created!");
                        }

                    } else if (selection == 2) {

                        book.viewBookings();

                    } else {

                        // Exits the system with status 0
                        System.out.println("Exiting system...");
                        System.exit(0);
                    }
                }
            }
        }

        // Exits the system with status 0
        System.out.println("Exiting system...");
        System.exit(0);

    }
}