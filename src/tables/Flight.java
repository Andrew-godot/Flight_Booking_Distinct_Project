package tables;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public Flight() {

        this.flightID = "null";
        this.departureDate = null;
        this.departureTime = null;
        this.arrivalDate = null;
        this.arrivalTime = null;
        this.capacity = 0;
        this.routeID = 0;
    }

    public void addFlightData() {

        File file = new File("Output/FlightData.csv");

        boolean writeHeader = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

            if (writeHeader) {
                System.out.println("Writing CSV header...");
                writer.println("flightID,departureDate,departureTime,arrivalDate,arrivalTime,capacity,routeID");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
}
