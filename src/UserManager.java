import java.io.*;
import java.security.MessageDigest;
import java.util.*;

public class UserManager {
    private List<User> users = new ArrayList<>();
    private final String filePath = "data/users.txt";

    public UserManager() {
        loadUsers();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 2) users.add(new User(p[0], p[1]));
            }
        } catch (Exception e) {
            System.out.println("No users yet.");
        }
    }

    private void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (User u : users)
                pw.println(u.getUsername() + "," + u.getPassword());
        } catch (Exception e) { e.printStackTrace(); }
    }

    public boolean register(String user, String pass) {
        for (User u : users)
            if (u.getUsername().equals(user)) return false;

        users.add(new User(user, hashPassword(pass)));
        saveUsers();
        return true;
    }

    public boolean login(String user, String pass) {
        String hashed = hashPassword(pass);
        for (User u : users)
            if (u.getUsername().equals(user) && u.getPassword().equals(hashed))
                return true;
        return false;
    }

    public List<User> getUsers() {
        return users;
    }

    public void deleteUser(String username) {
        users.removeIf(u -> u.getUsername().equals(username));
        saveUsers();
    }
}