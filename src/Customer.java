import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    // Attributes
    private String customerID = String.valueOf(generateCustomerID());
    private String forename;
    private String surname;
    private String street;
    private String town;
    private String postcode;

    // Main Constructor
    public Customer(String forename, String surname, String street, String town, String postcode) {

        this.customerID = String.valueOf(generateCustomerID());
        this.forename = forename;
        this.surname = surname;
        this.street = street;
        this.town = town;
        this.postcode = postcode;
    }

    // Secondary Constructor
    public Customer(){

        this.customerID = String.valueOf(generateCustomerID());
        this.forename = "Unknown";
        this.surname = "Unknown";
        this.street = "Unknown";
        this.town = "Unknown";
        this.postcode = "Unknown";
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void printInfo() {
        System.out.printf("CustomerID: %s, Forename: %s, Surname: %s, Street: %s, Town: %s, Postcode: %s", customerID, forename, surname, street, town, postcode);
    }

    // Methods
    public void getCustomerData() {

        // This method asks the user for their information
        System.out.println("<--------- CUSTOMER INFORMATION SYSTEM --------->");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your forename and surname (separated by a space): ");
        String fullname = input.nextLine();

        String[] names = fullname.trim().split("\\s+");

        this.forename = names[0];
        this.surname = names[1];

        System.out.print("Enter your street name: ");
        this.street = input.nextLine();

        System.out.print("Enter the name of your town: ");
        this.town = input.nextLine();

        System.out.print("Enter your postcode: ");
        this.postcode = input.nextLine();
    }

    public void addCustomerData() {

        // This method adds the customer information to the csv file called 'CustomerData.csv'
        File file = new File("Output/CustomerData.csv");

        System.out.println("File location: " + file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());
        System.out.println("File size: " + file.length());

        boolean writeHeader = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(file, true))) {

            if (writeHeader) {
                writer.println("customerID,forename,surname,street,town,postcode");
            }

            writer.printf("%s,%s,%s,%s,%s,%s%n",
                    this.customerID,
                    this.forename,
                    this.surname,
                    this.street,
                    this.town,
                    this.postcode);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editCustomerData() {

        Scanner input = new Scanner(System.in);
        ArrayList<String> customers = new ArrayList<>();

        try {
            File file = new File("Output/CustomerData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                customers.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the customer ID: ");
            this.customerID = input.nextLine();

            boolean found = false;

            for (int i = 1; i < customers.size(); i++) {

                String[] customerData = customers.get(i).split(",");

                if (customerData[0].equals(this.customerID)) {

                    found = true;

                    System.out.println("Customer Data: " + customers.get(i));

                    System.out.println("Enter the new data in the following format:");
                    System.out.println("(forename),(surname),(street),(town),(postcode)");

                    String newData = input.nextLine();

                    String[] storedData = newData.split(",");

                    if (storedData.length != 5) {
                        System.out.println("Invalid customer data.");
                        return;
                    }

                    // Replace ONLY the matching customer row
                    customers.set(i, customerData[0] + "," +
                            storedData[0] + "," +
                            storedData[1] + "," +
                            storedData[2] + "," +
                            storedData[3] + "," +
                            storedData[4]);

                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the customerID");
            }

            if (found) {

                try (PrintWriter writer = new PrintWriter(
                        new FileWriter("Output/CustomerData.csv", false))) {

                    for (String customer : customers) {
                        writer.println(customer);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Customer information edited successfully");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomerData() {

        Scanner input = new Scanner(System.in);
        ArrayList<String> customers = new ArrayList<>();

        try {
            File file = new File("Output/CustomerData.csv");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                customers.add(reader.nextLine());
            }

            reader.close();

            System.out.print("Enter the customer ID: ");
            this.customerID = input.nextLine();

            boolean found = false;

            for (int i = 1; i < customers.size(); i++) {

                String[] customerData = customers.get(i).split(",");

                if (customerData[0].equals(this.customerID)) {

                    found = true;

                    System.out.println("Customer found: " + customers.get(i));

                    // Remove the matching customer row
                    customers.remove(i);

                    System.out.println("Customer successfully deleted!");

                    break;
                }
            }

            if (!found) {
                System.out.println("Could not find the customer ID.");
                return;
            }

            // Overwrite the CSV file with remaining records
            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(file, false))) {

                for (String customer : customers) {
                    writer.println(customer);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public static String generateCustomerID() {

        // This method generates the incrementing integer customerID
        int customerID = 0;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/CustomerData.csv"))) {

            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].substring(3));

                customerID = Math.max(customerID, id);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("GR0%d", customerID + 1);
    }
}

