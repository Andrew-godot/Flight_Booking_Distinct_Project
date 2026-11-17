package tables;
import data.GlobalData;
import static data.GlobalData.getIntegerInput;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {

    // Attributes
    private int bookingNo;
    private int adultTicket;
    private int childTicket;
    private int concessionTicket;
    private String customerID;
    private String flightID;

    // Main Constructor
    public Booking(int adultTicket, int childTicket, int concessionTicket, String customerID, String flightID) {

        this.bookingNo = generateBookingNo();
        this.adultTicket = adultTicket;
        this.childTicket = childTicket;
        this.concessionTicket = concessionTicket;
        this.customerID = customerID;
        this.flightID = flightID;
    }

    // Secondary Constructor
    public Booking() {

        this.bookingNo = generateBookingNo();
        this.adultTicket = adultTicket;
        this.childTicket = childTicket;
        this.concessionTicket = concessionTicket;
        this.customerID = customerID;
        this.flightID = flightID;
    }

    Scanner input = new Scanner(System.in);

    // Methods
    // Retrieves the booking data from the logged in customer
    public boolean getBookingData() {

        System.out.println("<--------- BOOKING INFORMATION SYSTEM --------->");

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/CustomerData.csv"))) {

            // Retrieve logged-in customer's ID
            this.customerID = GlobalData.loggedInCustomerID;

            if (this.customerID == null) {
                System.out.println("No customer is logged in.");
                return false;
            }

            System.out.println("Customer ID: " + this.customerID);

            boolean found = false;
            String line;

            reader.readLine(); // Skips the CSV Header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data[0].equals(this.customerID)) {

                    System.out.println("Customer ID was found");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Customer ID was not found");
                return false;
            }

            System.out.print("Enter amount of adult tickets: ");
            this.adultTicket = getIntegerInput(input);

            System.out.print("Enter amount of child tickets: ");
            this.childTicket = getIntegerInput(input);

            System.out.print("Enter amount of concession tickets: ");
            this.concessionTicket = getIntegerInput(input);

            System.out.print("Enter the flight ID for your booking: ");
            this.flightID = input.nextLine();

            if (availableFlight(input)) {

                if (checkExistingBooking()) {
                    System.out.println("Booking information already exists within table");
                    return false;
                }

                else {
                    return true;
                }
            }

            else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    // Retrieves the booking data entered by the customer and adds it to the BookingData.csv file
    public void addBookingData() {

        File file = new File("Output/BookingData.csv");

        System.out.println("File location: " + file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());
        System.out.println("File size: " + file.length());

        boolean writeHeader = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

            if (writeHeader) {
                System.out.println("Writing CSV header...");
                writer.println("bookingNo,adultTicket,childTicket,concessionTicket,customerID,flightID");
            }

            writer.printf("%d,%d,%d,%d,%s,%s%n", this.bookingNo, this.adultTicket, this.childTicket, this.concessionTicket, this.customerID, this.flightID);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Edits existing booking data (ADMIN ONLY)
    public void editBookingData() {

        ArrayList<String> bookings = new ArrayList<>();

        try {
            File file = new File("Output/BookingData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                bookings.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the booking number: ");
            this.bookingNo = getIntegerInput(input);

            boolean found = false;

            for (int i = 1; i < bookings.size(); i++) {

                String[] bookingData = bookings.get(i).split(",");

                if (Integer.parseInt(bookingData[0].trim()) == this.bookingNo) {

                    found = true;

                    System.out.println("Booking Data: " + bookings.get(i));

                    System.out.println("Enter the new data in the following format:");
                    System.out.println("adultTicket,childTicket,concessionTicket");

                    String newData = input.nextLine();

                    String[] storedData = newData.split(",");

                    if (storedData.length != 3) {
                        System.out.println("Invalid booking data.");
                        return;
                    }

                    // Replace ONLY the matching customer row
                    bookings.set(i, bookingData[0] + "," +
                            storedData[0] + "," +
                            storedData[1] + "," +
                            storedData[2] + "," +
                            bookingData[4] + "," +
                            bookingData[5]);
                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the booking number");
            }

            if (found) {

                try (PrintWriter writer = new PrintWriter(
                        new FileWriter("Output/BookingData.csv", false))) {

                    for (String booking : bookings) {
                        writer.println(booking);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("booking information edited successfully");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Deletes existing booking data (ADMIN ONLY)
    public void deleteBookingData() {

        ArrayList<String> bookings = new ArrayList<>();

        try {
            File file = new File("Output/BookingData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                bookings.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the booking number: ");
            this.bookingNo = getIntegerInput(input);

            boolean found = false;

            for (int i = 1; i < bookings.size(); i++) {

                String[] bookingData = bookings.get(i).split(",");

                if (Integer.parseInt(bookingData[0].trim()) == this.bookingNo) {

                    found = true;

                    System.out.println("Booking entry found: " + bookings.get(i));

                    // Remove the matching booking row
                    bookings.remove(i);

                    System.out.println("Booking entry successfully deleted!");

                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the booking number");
                return;
            }

            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(file, false))) {

                for (String booking : bookings) {
                    writer.println(booking);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    // Generates the unique booking number for each customer
    public static int generateBookingNo() {

        int bookingNo = 100;

        File file = new File("Output/BookingData.csv");

        if (!file.exists()) {
            return 101;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;
            reader.readLine(); // Skips the CSV Header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                // Ignores empty or invalid rows
                if (data.length == 0 || data[0].trim().isEmpty()) {
                    continue;
                }

                int id = Integer.parseInt(data[0].trim());

                if (id > bookingNo) {
                    bookingNo = id;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bookingNo + 1;
    }

    // Displays the contents of the BookingData.csv file (ADMIN ONLY)
    public void viewTable() {
        File file = new File("Output/BookingData.csv");

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading data.");
        }
    }

    // Displays bookings only the customer has made
    public void viewBookings() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Output/BookingData.csv"))) {

            // Retrieve logged-in customer's ID
            this.customerID = GlobalData.loggedInCustomerID;

            if (this.customerID == null) {
                System.out.println("No customer is logged in.");
            }

            boolean found = false;

            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                // Check whether the customer ID matches
                if (data[4].equals(customerID)) {

                    found = true;

                    // Display the booking details
                    for (String value : data) {
                        System.out.print(value + " | ");
                    }

                    System.out.println();
                }
            }

            // If no bookings were found
            if (!found) {
                System.out.println("No bookings found for this customer ID.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Checks for an existing booking in BookingData.csv
    public boolean checkExistingBooking() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/BookingData.csv"))) {

            String line;

            reader.readLine(); // Skips the CSV Header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                // Check all five booking attributes
                if (data.length >= 6 &&
                        Integer.parseInt(data[1].trim()) == this.adultTicket &&
                        Integer.parseInt(data[2].trim()) == this.childTicket &&
                        Integer.parseInt(data[3].trim()) == this.concessionTicket &&
                        data[4].trim().equalsIgnoreCase(this.customerID) &&
                        data[5].trim().equalsIgnoreCase(this.flightID)) {

                    return true;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    // Checks if the flightID entered is in the FlightData.csv file
    public boolean availableFlight(Scanner input) {

        ArrayList<String> flightList = new ArrayList<>();

        System.out.println("Searching for: " + this.flightID);

        try {
            File file = new File("Output/FlightData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                flightList.add(reader.nextLine());
            }

            reader.close();

            for (int i = 0; i < flightList.size(); i++) {

                String[] flightData = flightList.get(i).split(",");

                String flightID = flightData[0].trim();

                if (flightID.equals(this.flightID.trim())) {
                    System.out.printf("Flight ID %s found\n", this.flightID);
                    return true;
                }
            }

            System.out.printf("Flight ID %s not found\n", this.flightID);
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}