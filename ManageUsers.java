package banksystem;

import java.io.*;
import java.util.*;

public class ManageUsers {
    private final String FILE_PATH = "users.txt";

    public boolean registerClient(String username, String password, int balance) throws IOException {
        if (findUser(username) != null) {
            System.out.println("User already exists!");
            return false;
        }

        ClientUser user = new ClientUser(username, password, balance);
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(user.toString() + "\n");
        fw.close();

        System.out.println("Client registered successfully!");
        return true;
    }

    public boolean registerAdmin(String username, String password) throws IOException {
        if (findUser(username) != null) {
            System.out.println("Admin already exists!");
            return false;
        }

        AdminUser user = new AdminUser(username, password);
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(user.toString() + "\n");
        fw.close();

        System.out.println("Admin registered successfully!");
        return true;
    }

    // LOGIN
    public User login(String username, String password) throws IOException {
        User user = findUser(username);
        if (user == null) {
            System.out.println("User not found!");
            return null;
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password!");
            return null;
        }
        System.out.println("Login successful!");
        return user;
    }

    // FIND USER
    public User findUser(String username) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return null;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 4 && data[0].equals(username)) {
                String role = data[3];

                if (role.equals("client"))
                    return new ClientUser(data[0], data[1], Integer.parseInt(data[2]));

                if (role.equals("admin"))
                    return new AdminUser(data[0], data[1]);
            }
        }
        br.close();
        return null;
    }

    public List<User> getAllUsers() throws IOException {
        List<User> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            if (data.length == 4) {
                if (data[3].equals("client"))
                    list.add(new ClientUser(data[0], data[1], Integer.parseInt(data[2])));
                else
                    list.add(new AdminUser(data[0], data[1]));
            }
        }
        br.close();
        return list;
    }

    // UPDATE CLIENT BALANCE
    public void updateUser(User updatedUser) throws IOException {
        List<User> all = getAllUsers();
        FileWriter fw = new FileWriter(FILE_PATH);

        for (User u : all) {
            if (u.getUsername().equals(updatedUser.getUsername())) {
                fw.write(updatedUser.toString() + "\n");
            } else {
                fw.write(u.toString() + "\n");
            }
        }
        fw.close();
    }
}
