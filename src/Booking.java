import java.io.*;
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

    public boolean getBookingData() {

        Scanner input = new Scanner(System.in);

        System.out.println("<--------- BOOKING INFORMATION SYSTEM --------->");

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/CustomerData.csv"))) {

            System.out.print("Enter the customer ID: ");
            this.customerID = input.nextLine();

            boolean found = false;

            String line;

            reader.readLine(); // Skip CSV header

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

            if (checkExistingBooking(this.customerID)) {

                System.out.println("This customer already has a booking.");
                return false;

            }

            System.out.println("Customer is eligible to book!");

            System.out.print("Enter amount of adult tickets: ");
            this.adultTicket = Integer.parseInt(input.nextLine());

            System.out.print("Enter amount of child tickets: ");
            this.childTicket = Integer.parseInt(input.nextLine());

            System.out.print("Enter amount of concession tickets: ");
            this.concessionTicket = Integer.parseInt(input.nextLine());

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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

            writer.printf("%d,%d,%d,%d,%s,%s%n", this.bookingNo, this.adultTicket, this.childTicket, this.concessionTicket, this.customerID, this.flightID);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateBookingNo() {

        int bookingNo = 100;

        File file = new File("Output/BookingData.csv");

        if (!file.exists()) {
            return 101;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);

                if (id > bookingNo) {
                    bookingNo = id;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bookingNo + 1;
    }

    public boolean checkExistingBooking(String customerID) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/BookingData.csv"))) {

            String line;

            reader.readLine(); // Skip CSV header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                // Check 5th column (index 4)
                if (data[4].equals(customerID)) {
                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            return false; // No bookings exist yet
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}