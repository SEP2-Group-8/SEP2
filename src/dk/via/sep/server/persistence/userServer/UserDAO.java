package dk.via.sep.server.persistence.userServer;

import dk.via.sep.shared.transfer.User;

import java.util.ArrayList;

public interface UserDAO {
    void addUser(User user);
    ArrayList<User> getAllUsers();
    ArrayList<User> getAllAdmins();
    User getUser(String username, String password);
}
