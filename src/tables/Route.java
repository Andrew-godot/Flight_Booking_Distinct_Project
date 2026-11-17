package tables;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static data.GlobalData.getIntegerInput;

public class Route {

    // Attributes
    private int routeID;
    private String departFrom;
    private String arriveAt;
    private String midStopOne;
    private String midStopTwo;

    // Main Constructor
    public Route(int routeID, String departFrom, String arriveAt, String midStopOne, String midStopTwo){

        this.routeID = routeID;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.midStopOne = midStopOne;
        this.midStopTwo = midStopTwo;
    }

    // Secondary Constructor
    public Route() {
        this.routeID = 0;
        this.departFrom = "null";
        this.arriveAt = "null";
        this.midStopOne = "null";
        this.midStopTwo = "null";
    }

    Scanner input = new Scanner(System.in);

    // Methods
    // Retrieves the route data entered by the admin and adds it to the RouteData.csv file (ADMIN ONLY)
    public void addRouteData() {

        File file = new File("Output/RouteData.csv");

        System.out.println("Enter the route information in the following format:");
        System.out.println("routeID,departFrom,arriveAt,midStopOne,midStopTwo");

        String newData = input.nextLine();

        String[] storedData = newData.split(",", -1);

        if (storedData.length != 5) {
            System.out.println("Invalid route data entered");
            return;
        }

        // Store the route ID
        this.routeID = Integer.parseInt(storedData[0].trim());

        // Check whether the route ID already exists
        if (checkExistingRoute()) {
            System.out.println("This route ID already exists. Please try again.");
            return;
        }

        boolean writeHeader = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

            if (writeHeader) {
                System.out.println("Writing CSV header...");
                writer.println("routeID,departFrom,arriveAt,midStopOne,midStopTwo");
            }

            writer.println(newData);
            System.out.println("Route information added successfully!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Edits existing route data (ADMIN ONLY)
    public void editRouteData() {

        ArrayList<String> routes = new ArrayList<>();

        try {
            File file = new File("Output/RouteData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                routes.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the route ID: ");
            this.routeID = getIntegerInput(input);

            boolean found = false;

            for (int i = 1; i < routes.size(); i++) {

                String[] routeData = routes.get(i).split(",", -1);

                if (routeData.length >= 5 && Integer.parseInt(routeData[0].trim()) == (this.routeID)) {

                    found = true;

                    System.out.println("Route Data: " + routes.get(i));

                    System.out.println("Enter the new data in the following format:");
                    System.out.println("routeID,departFrom,arriveAt,midStopOne,midStopTwo");

                    String newData = input.nextLine();

                    String[] storedData = newData.split(",", -1);

                    if (storedData.length != 5) {
                        System.out.println("Invalid route data entered");
                        return;
                    }

                    // Replace ONLY the matching route row
                    routes.set(i, routeData[0] + "," +
                            storedData[0] + "," +
                            storedData[1] + "," +
                            storedData[2] + "," +
                            storedData[3] + "," +
                            storedData[4]);

                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the route ID");
            }

            if (found) {

                try (PrintWriter writer = new PrintWriter(
                        new FileWriter(file, false))) {

                    for (String route : routes) {
                        writer.println(route);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Route information edited successfully");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    // Deletes existing route data (ADMIN ONLY)
    public void deleteRouteData() {

        ArrayList<String> routes = new ArrayList<>();

        try {
            File file = new File("Output/RouteData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                routes.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the route id: ");
            this.routeID = getIntegerInput(input);

            boolean found = false;

            for (int i = 1; i < routes.size(); i++) {

                String[] routeData = routes.get(i).split(",", -1);

                if (Integer.parseInt(routeData[0].trim()) == (this.routeID)) {

                    found = true;

                    System.out.println("Route entry found: " + routes.get(i));

                    // Remove the matching route row
                    routes.remove(i);

                    System.out.println("Route entry successfully deleted!");

                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the route id");
                return;
            }

            // Overwrite the CSV file with remaining records
            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(file, false))) {

                for (String route : routes) {
                    writer.println(route);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Displays the contents of the RouteData.csv file (ADMIN ONLY)
    public void viewTable() {
        File file = new File("Output/RouteData.csv");

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

    // Checks for an existing route in the RouteData.csv file (ADMIN ONLY)
    public boolean checkExistingRoute() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/RouteData.csv"))) {

            String line;

            reader.readLine(); // Skips the CSV Header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length >= 5 && Integer.parseInt(data[0].trim()) == (this.routeID)) {

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