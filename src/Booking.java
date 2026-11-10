import java.io.*;
import java.util.Scanner;

public class Booking {

    // Attributes
    private static int bookingNo = generateBookingNo();
    private int adultTicket;
    private int childTicket;
    private int concessionTicket;
    private int customerID;
    private int flightID;

    // Main Constructor
    public Booking(int adultTicket, int childTicket, int concessionTicket, int customerID, int flightID) {

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
    }

    public void getBookingData() {
        System.out.println("<--------- BOOKING INFORMATION SYSTEM --------->");

        Scanner input = new Scanner(System.in);

        System.out.print("How many adult tickets do you need?: ");
        this.adultTicket = Integer.parseInt(input.nextLine());

        System.out.print("How many child tickets do you need?: ");
        this.childTicket = Integer.parseInt(input.nextLine());

        System.out.print("How many concession tickets do you need?: ");
        this.concessionTicket = Integer.parseInt(input.nextLine());
    }

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

            writer.printf("%d,%d,%d,%d%n", this.bookingNo,this.adultTicket, this.childTicket, this.concessionTicket);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateBookingNo() {

        int bookingNo = 100;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/CustomerData.csv"))) {

            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (bookingNo + 1);

    }

}
