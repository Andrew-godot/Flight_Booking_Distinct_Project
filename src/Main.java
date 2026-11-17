import tables.Booking;
import tables.Customer;
import tables.Flight;
import tables.Route;

import java.util.Scanner;

import static data.GlobalData.getIntegerInput;

public class Main {

    static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String adminPassword = "admin1234";

        int choice = 0;

        boolean registered = false;
        boolean login = false;

        while (choice != 3) {

            //ADMIN MENU SYSTEM
            System.out.println("<--------- FLIGHT BOOKING SYSTEM --------->");
            System.out.println("1: Admin Login\n2: User Area\n3: Exit");

            choice = getIntegerInput(input);

            while (choice < 1 || choice > 3) {
                System.out.println("Invalid input, please try again");
                System.out.println("1: Admin Login\n2: User Area\n3: Exit");
                choice = getIntegerInput(input);
            }

            if (choice == 1) {

                System.out.print("Enter the admin password: ");
                String enteredPassword = input.nextLine();

                if (enteredPassword.equals(adminPassword)) {

                    System.out.println("Admin password correct");

                    int selection = 0;

                    Customer customerAdmin = new Customer();
                    Booking bookingAdmin = new Booking();
                    Flight flightAdmin = new Flight();
                    Route routeAdmin = new Route();

                    while (selection != 5) {

                        System.out.println("<--------- ADMIN TABLE MENU --------->");
                        System.out.println("1: Customer Table\n2: Booking Table\n3: Flight Table\n4: Route Table\n5: Exit");

                        selection = getIntegerInput(input);

                        if (selection == 1) {

                            int adminSelection = 0;

                            while (adminSelection != 4) {

                                System.out.println("<--------- ADMIN CUSTOMER TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Customer\n3: Delete Customer\n4: Back");

                                adminSelection = getIntegerInput(input);

                                if (adminSelection == 1) {
                                    customerAdmin.viewTable();

                                } else if (adminSelection == 2) {
                                    customerAdmin.editCustomerData();

                                } else if (adminSelection == 3) {
                                    customerAdmin.deleteCustomerData();
                                }
                            }

                        } else if (selection == 2) {

                            int adminSelection = 0;

                            while (adminSelection != 4) {

                                System.out.println("<--------- ADMIN BOOKING TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Booking\n3: Delete Booking\n4: Back");

                                adminSelection = getIntegerInput(input);

                                if (adminSelection == 1) {
                                    bookingAdmin.viewTable();

                                } else if (adminSelection == 2) {
                                    bookingAdmin.editBookingData();

                                } else if (adminSelection == 3) {
                                    bookingAdmin.deleteBookingData();
                                }
                            }

                        } else if (selection == 3) {

                            int adminSelection = 0;

                            while (adminSelection != 5) {

                                System.out.println("<--------- ADMIN FLIGHT TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Flight\n3: Delete Flight\n4: Add Flight\n5: Back");

                                adminSelection = getIntegerInput(input);

                                if (adminSelection == 1) {
                                    flightAdmin.viewTable();

                                } else if (adminSelection == 2) {
                                    flightAdmin.editFlightData();

                                } else if (adminSelection == 3) {
                                    flightAdmin.deleteFlightData();

                                } else if (adminSelection == 4) {
                                    flightAdmin.addFlightData();
                                }
                            }

                        } else if (selection == 4) {

                            int adminSelection = 0;

                            while (adminSelection != 5) {

                                System.out.println("<--------- ADMIN ROUTE TABLE --------->");
                                System.out.println("1: View Table\n2: Edit Route\n3: Delete Route\n4: Add Route\n5: Back");

                                adminSelection = getIntegerInput(input);

                                if (adminSelection == 1) {
                                    routeAdmin.viewTable();

                                } else if (adminSelection == 2) {
                                    routeAdmin.editRouteData();

                                } else if (adminSelection == 3) {
                                    routeAdmin.deleteRouteData();

                                } else if (adminSelection == 4) {
                                    routeAdmin.addRouteData();
                                }
                            }
                        }
                    }

                } else {
                    System.out.println("Admin password invalid");
                }

                break;
            } else if (choice == 2) {

                int selection = 0;

                while (selection != 3 && !login) {

                    Customer cust1 = new Customer();

                    //CUSTOMER MENU SYSTEM
                    System.out.println("<--------- CUSTOMER SYSTEM --------->");
                    System.out.println("1: Register\n2: Login\n3: Exit");
                    selection = getIntegerInput(input);

                    while (selection < 1 || selection > 3) {
                        System.out.println("Input not valid, please try again");
                        System.out.println("1: Register\n2: Login\n3: Exit");
                        selection = getIntegerInput(input);
                    }

                    if (selection == 1) {

                        if (!registered) {

                            cust1 = new Customer();

                            boolean customerRegistered = cust1.customerRegister();

                            if (!customerRegistered) {

                                registered = cust1.getCustomerData();

                                if (registered) {
                                    cust1.addCustomerData();
                                }
                            }
                        }

                    } else if (selection == 2) {
                        login = cust1.customerLogin();

                    } else {
                        System.out.println("Exiting system...");
                        System.exit(0);
                    }
                }

                while (login) {

                    Booking book1 = new Booking();

                    //BOOKING MENU SYSTEM
                    System.out.println("<--------- BOOKING SYSTEM --------->");
                    System.out.println("1: Create Booking(s)\n2: View Booking(s)\n3: Exit\n");
                    selection = getIntegerInput(input);

                    while (selection < 1 || selection > 3) {
                        System.out.println("Input not valid, please try again");
                        System.out.println("1: Create Booking(s)\n2: View Booking(s)\n3: Exit\n");
                        selection = getIntegerInput(input);
                    }

                    if (selection == 1) {

                        book1 = new Booking();

                        boolean bookingValid = book1.getBookingData();

                        if (bookingValid) {
                            book1.addBookingData();
                            System.out.println("Booking successfully created!");
                        }

                    } else if (selection == 2) {

                        book1.viewBookings();

                    } else {
                        System.out.println("Exiting system...");
                        System.exit(0);
                    }
                }
            }
        }
    }
}