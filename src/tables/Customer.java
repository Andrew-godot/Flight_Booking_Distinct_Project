package tables;

import data.GlobalData;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    // Attributes
    private String customerID;
    private String forename;
    private String surname;
    private String street;
    private String town;
    private String postcode;

    // Main Constructor
    public Customer(String forename, String surname, String street, String town, String postcode) {

        this.customerID = generateCustomerID();
        this.forename = forename;
        this.surname = surname;
        this.street = street;
        this.town = town;
        this.postcode = postcode;
    }

    // Secondary Constructor
    public Customer() {

        this.customerID = generateCustomerID();
        this.forename = "Unknown";
        this.surname = "Unknown";
        this.street = "Unknown";
        this.town = "Unknown";
        this.postcode = "Unknown";
    }

    Scanner input = new Scanner(System.in);

    // Methods
    // Retrieves the customer information from the registered customer
    public boolean getCustomerData() {

        // This method asks the user for their information
        System.out.println("<--------- CUSTOMER INFORMATION SYSTEM --------->");

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

        if (checkExistingCustomer()) {
            return false;
        } else {
            return true;
        }
    }

    // Retrieves the customer data entered by the customer and adds it to the CustomerData.csv file
    public void addCustomerData() {

        File file = new File("Output/CustomerData.csv");

        System.out.println("File location: " + file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());
        System.out.println("File size: " + file.length());

        boolean writeHeader = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {

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

    // Edits existing customer data (ADMIN ONLY)
    public void editCustomerData() {

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

                System.out.println("tables.Customer information edited successfully");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Deletes existing customer data (ADMIN ONLY)
    public void deleteCustomerData() {

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

                    System.out.println("tables.Customer found: " + customers.get(i));

                    // Remove the matching customer row
                    customers.remove(i);

                    System.out.println("tables.Customer successfully deleted!");

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

    // Generates the unique customer ID for each customer
    public static String generateCustomerID() {

        int customerID = 0;

        File customerFile = new File("Output/CustomerData.csv");
        File credentialsFile = new File("Output/userCredentials.csv");

        File[] files = {customerFile, credentialsFile};

        for (File file : files) {

            if (file.exists()) {

                try (BufferedReader reader = new BufferedReader(
                        new FileReader(file))) {

                    String line;

                    if (file.equals(customerFile)) {
                        reader.readLine();
                    }

                    while ((line = reader.readLine()) != null) {

                        String[] data = line.split(",");

                        if (data.length > 0 && data[0].startsWith("GR")) {

                            int id = Integer.parseInt(data[0].substring(2));

                            if (id > customerID) {
                                customerID = id;
                            }
                        }
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return String.format("GR%03d", customerID + 1);
    }

    // Displays the contents of the CustomerData.csv file (ADMIN ONLY)
    public void viewTable() {
        File file = new File("Output/CustomerData.csv");

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

    // Checks for an existing customer in CustomerData.csv
    public boolean checkExistingCustomer() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/CustomerData.csv"))) {

            String line;

            reader.readLine(); // Skip CSV header

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                // Check all five customer attributes
                if (data.length >= 6 &&
                        data[1].equalsIgnoreCase(this.forename) &&
                        data[2].equalsIgnoreCase(this.surname) &&
                        data[3].equalsIgnoreCase(this.street) &&
                        data[4].equalsIgnoreCase(this.town) &&
                        data[5].equalsIgnoreCase(this.postcode)) {

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

    // Customer registration system
    public boolean customerRegister() {

        String username;
        String password;
        String confirmPassword;

        System.out.println("<---------- CUSTOMER REGISTER SYSTEM ---------->");

        System.out.print("Username: ");
        username = input.nextLine();

        System.out.print("Password: ");
        password = input.nextLine();

        System.out.print("Re-enter password: ");
        confirmPassword = input.nextLine();

        boolean customerRegistered = false;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/userCredentials.csv"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 3 &&
                        data[1].equals(username)) {

                    customerRegistered = true;
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            // Credentials file does not exist yet.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (customerRegistered) {
            System.out.println("User has already registered, please log in.");
            return true;
        }

        if (password.equals(confirmPassword)) {

            System.out.println("Registration finished!");

            GlobalData.credentials.add(customerID + "," + username + "," + password);

            try (PrintWriter writer = new PrintWriter(
                    new FileWriter("Output/userCredentials.csv", true))) {

                writer.printf("%s,%s,%s%n", customerID, username, password);

            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }

            return false;

        } else {
            System.out.println("Passwords do not match");
            return true;
        }
    }

    // Customer login system (only after registration)
    public boolean customerLogin() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("Output/userCredentials.csv"))) {

            System.out.println("<---------- CUSTOMER LOGIN SYSTEM ---------->");

            System.out.print("Enter username: ");
            String username = input.nextLine();

            System.out.print("Enter password: ");
            String password = input.nextLine();

            String line;
            boolean login = false;
            boolean userFound = false;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 3) {

                    if (data[1].equals(username)) {

                        userFound = true;

                        if (data[2].equals(password)) {

                            login = true;

                            // Store the logged-in customer's ID
                            GlobalData.loggedInCustomerID = data[0];
                        }

                        break;
                    }
                }
            }

            if (login) {
                System.out.printf("Login successful! Welcome %s%n", username);

            } else if (!userFound) {
                System.out.println("Account not found. Please register first.");

            } else {
                System.out.println("Incorrect password.");
            }

            return login;

        } catch (FileNotFoundException e) {
            System.out.println("No accounts found. Please register first.");
            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}