import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;

public class DataManager {
    private static final String SAVE_FILE_NAME = "passwords.ser";

    // Method to save (serialize) the passwordMap to a file
    public static void savePasswordMap(Map<String, String> passwordMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME))) {
            oos.writeObject(passwordMap);
            System.out.println("Password data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving password data: " + e.getMessage());
        }
    }

    // Method to load (deserialize) the passwordMap from a file
    @SuppressWarnings("unchecked")
    public static Map<String, String> loadPasswordMap() {
        File file = new File(SAVE_FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Map<String, String>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading password data: " + e.getMessage());
                return new LinkedHashMap<>();
            }
        }
        return new LinkedHashMap<>();
    }
}
