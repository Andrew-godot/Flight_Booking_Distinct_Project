package tables;

import java.io.*;

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

    public Route() {
        this.routeID = 0;
        this.departFrom = "null";
        this.arriveAt = "null";
        this.midStopOne = "null";
        this.midStopTwo = "null";
    }

    public void addRouteData() {

        File file = new File("Output/RouteData.csv");

        boolean writeHeader = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

            if (writeHeader) {
                System.out.println("Writing CSV header...");
                writer.println("routeID,departFrom,arriveAt,midStopOne,midStopTwo");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
}