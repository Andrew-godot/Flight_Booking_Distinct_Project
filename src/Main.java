import tables.Booking;
import tables.Customer;
import tables.Flight;
import tables.Route;

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
        Flight flight1 = new Flight();
        Route route1 = new Route();

        flight1.addFlightData();
        route1.addRouteData();

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

                    int selection = 0;

                    while (selection != 5) {

                        System.out.println("\n<--------- ADMIN TABLE MENU --------->");
                        System.out.println("1: Customer Table");
                        System.out.println("2: Booking Table");
                        System.out.println("3: Flight Table");
                        System.out.println("4: Route Table");
                        System.out.println("5: Exit");

                        selection = Integer.parseInt(input.nextLine());

                        if (selection == 1) {

                            int adminSelection = 0;

                            while (adminSelection != 4) {

                                System.out.println("\n<--------- ADMIN CUSTOMER TABLE --------->");
                                System.out.println("1: View Table");
                                System.out.println("2: Edit Customer");
                                System.out.println("3: Delete Customer");
                                System.out.println("4: Back");

                                adminSelection = Integer.parseInt(input.nextLine());

                                if (adminSelection == 1) {
                                    cust1.viewTable();

                                } else if (adminSelection == 2) {
                                    cust1.editCustomerData();

                                } else if (adminSelection == 3) {
                                    cust1.deleteCustomerData();
                                }
                            }

                        } else if (selection == 2) {

                            int adminSelection = 0;

                            while (adminSelection != 4) {

                                System.out.println("\n<--------- ADMIN BOOKING TABLE --------->");
                                System.out.println("1: View Table");
                                System.out.println("2: Edit Booking");
                                System.out.println("3: Delete Booking");
                                System.out.println("4: Back");

                                adminSelection = Integer.parseInt(input.nextLine());

                                if (adminSelection == 1) {
                                    book1.viewTable();

                                } else if (adminSelection == 2) {
                                    System.out.println("Edit Booking coming soon");

                                } else if (adminSelection == 3) {
                                    System.out.println("Delete Booking coming soon");
                                }
                            }

                        } else if (selection == 3) {

                            int adminSelection = 0;

                            while (adminSelection != 4) {

                                System.out.println("\n<--------- ADMIN FLIGHT TABLE --------->");
                                System.out.println("1: View Table");
                                System.out.println("2: Edit Flight");
                                System.out.println("3: Delete Flight");
                                System.out.println("4: Back");

                                adminSelection = Integer.parseInt(input.nextLine());

                                if (adminSelection == 1) {
                                    flight1.viewTable();

                                } else if (adminSelection == 2) {
                                    System.out.println("Edit Flight coming soon");

                                } else if (adminSelection == 3) {
                                    System.out.println("Delete Flight coming soon");
                                }
                            }

                        } else if (selection == 4) {

                            int adminSelection = 0;

                            while (adminSelection != 4) {

                                System.out.println("\n<--------- ADMIN ROUTE TABLE --------->");
                                System.out.println("1: View Table");
                                System.out.println("2: Edit Route");
                                System.out.println("3: Delete Route");
                                System.out.println("4: Back");

                                adminSelection = Integer.parseInt(input.nextLine());

                                if (adminSelection == 1) {
                                    route1.viewTable();

                                } else if (adminSelection == 2) {
                                    System.out.println("Edit Route coming soon");

                                } else if (adminSelection == 3) {
                                    System.out.println("Delete Route coming soon");
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

                // tables.Customer / tables.Booking / Exit menu
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