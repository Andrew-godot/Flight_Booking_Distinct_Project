import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement {

    ArrayList<String> credentials = new ArrayList<>();

    private String username;
    private String password;
    private String confirmPassword;

    public AccountManagement() {

    }

    public String accountRegister() {

        Scanner input = new Scanner(System.in);

        System.out.println("<---------- CUSTOMER REGISTER SYSTEM ---------->");
        System.out.println("register code");

        System.out.print("Username: ");
        username = input.nextLine();

        System.out.print("Password: ");
        password = input.nextLine();

        System.out.print("Re-enter password: ");
        confirmPassword = input.nextLine();

        if (password.equals(confirmPassword)) {
            System.out.println("Registration finished!");

            credentials.add(username);
            credentials.add(password);
        }

        else {
            System.out.println("Passwords do not match");
        }

        return "test return";
    }

    public boolean accountLogin() {

        Scanner input = new Scanner(System.in);

        System.out.println("<---------- CUSTOMER LOGIN SYSTEM ---------->");

        System.out.print("Username: ");
        username = input.nextLine();

        System.out.print("Password: ");
        password = input.nextLine();

        boolean login = false;

        if (username.equals(credentials.get(0)) && password.equals(credentials.get(1))) {

            System.out.println("Credentials match");
            login = true;

        } else {
            System.out.println("Credentials do not match");
            login = false;
        }

        return login;
    }

    public String getUsername() {
        return username;
    }
}
