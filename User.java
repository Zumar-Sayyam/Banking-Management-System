package banksystem;

public class User {
    protected String username;
    protected String password;
    protected int balance;
    protected String role; // "client" or "admin"

    public User() {}

    public User(String username, String password, int balance, String role) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getBalance() { return balance; }
    public String getRole() { return role; }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + balance + "," + role;
    }
}
