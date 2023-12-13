import java.util.Scanner;
import java.util.Random;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class SecureGen implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Map<String, String> passwordMap = new LinkedHashMap<>();
    private static final String SAVE_FILE_NAME = "passwords.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager.loadPasswordMap();

        String username, password;
        System.out.println("Welcome to SecureGen - Secure Password Generator");

        // Login
        while (true) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            if (username.equals("Admin") && password.equals("12345")) {
                System.out.println("Login successful!");
                break;
            } else {
                System.out.println("Incorrect username or password. Please try again.");
            }
        }

        // Main menu
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1) Generate new password");
            System.out.println("2) View generated passwords");
            System.out.println("3) Delete a specific password");
            System.out.println("4) Generate pronounceable password"); // New option
            System.out.println("5) Exit"); // Adjusted exit option number
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    PasswordManager.generateNewPassword(scanner);
                    break;
                case 2:
                    PasswordManager.viewGeneratedPasswords();
                    break;
                case 3:
                    PasswordManager.deleteSpecificPassword(scanner);
                    break;
                case 4:
                    PasswordManager.generatePronounceablePassword(scanner); // New functionality
                    break;
                case 5:
                    DataManager.savePasswordMap(PasswordManager.getPasswordMap());
                    System.out.println("Exiting SecureGen.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Additional methods (generateNewPassword, viewGeneratedPasswords, deleteSpecificPassword, generatePassword) go here
    // If these methods are moved to PasswordManager, remove them from here
}

