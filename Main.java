package banksystem;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ManageUsers manager = new ManageUsers();

        while (true) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Username: ");
                    String u = sc.next();
                    System.out.print("Password: ");
                    String p = sc.next();

                    User logged = manager.login(u, p);

                    if (logged != null) {
                        if (logged.getRole().equals("client"))
                            clientMenu((ClientUser) logged, manager, sc);
                        else
                            adminMenu((AdminUser) logged, manager, sc);
                    }
                }

                case 2 -> System.exit(0);
            }
        }
    }

    // CLIENT MENU
    public static void clientMenu(ClientUser user, ManageUsers manager, Scanner sc) throws IOException {
        while (true) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> System.out.println("Balance: " + user.getBalance());

                case 2 -> {
                    System.out.print("Amount: ");
                    int amt = sc.nextInt();
                    user.setBalance(user.getBalance() + amt);
                    manager.updateUser(user);
                }

                case 3 -> {
                    System.out.print("Amount: ");
                    int amt = sc.nextInt();
                    if (amt > user.getBalance()) System.out.println("Insufficient funds!");
                    else {
                        user.setBalance(user.getBalance() - amt);
                        manager.updateUser(user);
                    }
                }

                case 4 -> { return; }
            }
        }
    }

    // ADMIN MENU
    public static void adminMenu(AdminUser admin, ManageUsers manager, Scanner sc) throws IOException {
        while (true) {
            System.out.println("\n--- Admin Menu (Admin Verified) ---");
            System.out.println("1. Register Client");
            System.out.println("2. Register Admin");
            System.out.println("3. View All Users");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {   // Register Client
                    System.out.print("Client username: ");
                    String u = sc.next();
                    System.out.print("Password: ");
                    String p = sc.next();
                    System.out.print("Initial balance: ");
                    int b = sc.nextInt();
                    manager.registerClient(u, p, b);
                }

                case 2 -> {  // Register Admin
                    System.out.print("Admin username: ");
                    String u = sc.next();
                    System.out.print("Password: ");
                    String p = sc.next();
                    manager.registerAdmin(u, p);
                }

                case 3 -> {  // View all users
                    for (User u : manager.getAllUsers())
                        System.out.println(u);
                }

                case 4 -> { return; } // Logout
            }
        }
    }
}
