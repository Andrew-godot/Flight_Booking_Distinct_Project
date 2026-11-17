package tables;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight {

    // Attributes
    private String flightID;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private int capacity;
    private int routeID;

    // Main Constructor
    public Flight(String flightID, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, int capacity, int routeID){

        this.flightID = flightID;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
        this.routeID = routeID;
    }

    // Secondary Constructor
    public Flight() {

        this.flightID = "null";
        this.departureDate = null;
        this.departureTime = null;
        this.arrivalDate = null;
        this.arrivalTime = null;
        this.capacity = 0;
        this.routeID = 0;
    }

    Scanner input = new Scanner(System.in);

    // Methods
    // Retrieves the flight data entered by the admin and adds it to the FlightData.csv file (ADMIN ONLY)
    public void addFlightData() {

        File file = new File("Output/FlightData.csv");

        System.out.println("Enter the flight information in the following format:");
        System.out.println("flightID,departureDate,departureTime,arrivalDate,arrivalTime,capacity,routeID");

        String newData = input.nextLine();

        String[] storedData = newData.split(",");

        if (storedData.length != 7) {
            System.out.println("Invalid flight data entered");
            return;
        }

        // Store the flight ID
        this.flightID = storedData[0].trim();

        // Check whether the flight ID already exists
        if (checkExistingFlight()) {
            System.out.println("This flight ID already exists. Please try again.");
            return;
        }

        boolean writeHeader = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

            if (writeHeader) {
                writer.println("flightID,departureDate,departureTime,arrivalDate,arrivalTime,capacity,routeID");
            }

            writer.println(newData);
            System.out.println("Flight information added successfully!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Edits existing flight data (ADMIN ONLY)
    public void editFlightData() {

        ArrayList<String> flights = new ArrayList<>();

        try {
            File file = new File("Output/FlightData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                flights.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the flight ID: ");
            this.flightID = input.nextLine();

            boolean found = false;

            for (int i = 1; i < flights.size(); i++) {

                String[] flightData = flights.get(i).split(",");

                if (flightData.length >= 7 &&
                        flightData[0].trim().equalsIgnoreCase(this.flightID)) {

                    found = true;

                    System.out.println("Flight Data: " + flights.get(i));

                    System.out.println("Enter the new data in the following format:");
                    System.out.println("departureDate,departureTime,arrivalDate,arrivalTime,capacity,routeID");

                    String newData = input.nextLine();

                    String[] storedData = newData.split(",");

                    if (storedData.length != 6) {
                        System.out.println("Invalid flight data.");
                        return;
                    }

                    // Replace ONLY the matching flight row
                    flights.set(i, flightData[0] + "," +
                            storedData[0] + "," +
                            storedData[1] + "," +
                            storedData[2] + "," +
                            storedData[3] + "," +
                            storedData[4] + "," +
                            storedData[5]);

                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the flight ID");
            }

            if (found) {

                try (PrintWriter writer = new PrintWriter(
                        new FileWriter(file, false))) {

                    for (String flight : flights) {
                        writer.println(flight);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Flight information edited successfully");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Deletes existing flight data (ADMIN ONLY)
    public void deleteFlightData() {

        ArrayList<String> flights = new ArrayList<>();

        try {
            File file = new File("Output/FlightData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                flights.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the flight id: ");
            this.flightID = input.nextLine();

            boolean found = false;

            for (int i = 1; i < flights.size(); i++) {

                String[] flightData = flights.get(i).split(",");

                if (flightData[0].trim().equals(this.flightID)) {

                    found = true;

                    System.out.println("Flight entry found: " + flights.get(i));

                    flights.remove(i);

                    System.out.println("Flight entry successfully deleted!");

                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the flight id");
                return;
            }

            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(file, false))) {

                for (String flight : flights) {
                    writer.println(flight);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Displays the contents of the FlightData.csv file (ADMIN ONLY)
    public void viewTable() {
        File file = new File("Output/FlightData.csv");

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

    // Checks for an existing flight in FlightData.csv
    public boolean checkExistingFlight() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/FlightData.csv"))) {

            String line;

            reader.readLine(); // Skips the CSV Header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 7 &&
                        data[0].trim().equalsIgnoreCase(this.flightID)) {

                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
