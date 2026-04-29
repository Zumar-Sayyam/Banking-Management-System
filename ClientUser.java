package banksystem;

public class ClientUser extends User {

    public ClientUser(String username, String password, int balance) {
        super(username, password, balance, "client");
    }
}
