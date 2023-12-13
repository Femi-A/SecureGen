import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class PasswordManager {
    private static Map<String, String> passwordMap = new LinkedHashMap<>();
    public static Map<String, String> getPasswordMap() {
        return passwordMap;
    }
        // Generate new password with specific criteria
    public static void generateNewPassword(Scanner scanner) {
        System.out.print("Enter the desired password length: ");
        int length = Integer.parseInt(scanner.nextLine());

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecial = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("yes");
        int numberOfNumbers = 0;
        if (includeNumbers) {
            System.out.print("How many numbers? (Enter 0 for random): ");
            numberOfNumbers = Integer.parseInt(scanner.nextLine());
        }

        String newPassword = generatePassword(length, includeSpecial, includeNumbers, numberOfNumbers);
        System.out.print("Enter a name for your new password: ");
        String name = scanner.nextLine();
        passwordMap.put(name, newPassword);
        System.out.println("New secure password generated and saved as '" + name + "'.");
    }

    // View Generated Passwords
    public static void viewGeneratedPasswords() {
        if (passwordMap.isEmpty()) {
            System.out.println("No passwords saved yet.");
        } else {
            System.out.println("Saved Passwords:");
            for (Map.Entry<String, String> entry : passwordMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    // Method to delete a specific password
    public static void deleteSpecificPassword(Scanner scanner) {
        if (passwordMap.isEmpty()) {
            System.out.println("No passwords to delete.");
            return;
        }

        List<String> keys = new ArrayList<>(passwordMap.keySet());
        System.out.println("Select the password to delete:");
        for (int i = 0; i < keys.size(); i++) {
            System.out.println((i + 1) + ") " + keys.get(i));
        }
        System.out.print("Enter the number of the password to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < keys.size()) {
            passwordMap.remove(keys.get(index));
            System.out.println("Password deleted successfully.");
        } else {
            System.out.println("Invalid index. No password deleted.");
        }
    }

    // Password Generator Method
private static String generatePassword(int length, boolean includeSpecial, boolean includeNumbers, int numberOfNumbers) {
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String specialCharacters = "!@#$%&*";
    
    StringBuilder password = new StringBuilder();
    Random random = new Random();

    int numbersCount = 0;
    int specialCount = 0;

    while (password.length() < length) {
        String charactersToUse = letters;

        if (includeNumbers && (numbersCount < numberOfNumbers || (numberOfNumbers == 0 && random.nextBoolean()))) {
            charactersToUse = numbers;
            numbersCount++;
        } else if (includeSpecial && random.nextBoolean()) {
            charactersToUse = specialCharacters;
            specialCount++;
        }

        char selectedChar = charactersToUse.charAt(random.nextInt(charactersToUse.length()));
        password.append(selectedChar);

        // Fill the remaining length with letters if numbers and special characters have met their count
        if (password.length() + (length - (numbersCount + specialCount)) > length) {
            charactersToUse = letters;
        }
    }

    return password.toString();
}

 public static void generatePronounceablePassword(Scanner scanner) {
        System.out.print("Enter the number of syllables: ");
        int syllableCount = Integer.parseInt(scanner.nextLine());

        String newPassword = createPronounceablePassword(syllableCount);
        System.out.print("Enter a name for your new password: ");
        String name = scanner.nextLine();
        passwordMap.put(name, newPassword);
        System.out.println("New pronounceable password generated and saved as '" + name + "'.");
    }

    private static String createPronounceablePassword(int syllableCount) {
        String[] syllables = {"ah", "boo", "uh", "ee", "oom", "ay", "ow", "oy", "foo", "bar", "brring", "fizz", "clip clop", "zzz", "boo hoo"};
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < syllableCount; i++) {
            if (i > 0) {
                password.append("-");
            }
            password.append(syllables[random.nextInt(syllables.length)]);
        }

        return password.toString();
    }

    public static void setPasswordMap(Map<String, String> map) {
        passwordMap = map;
    }
}


    
    