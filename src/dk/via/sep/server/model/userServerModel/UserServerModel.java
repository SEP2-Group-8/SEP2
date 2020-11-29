package dk.via.sep.server.model.userServerModel;

import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;
import java.util.UUID;

public interface UserServerModel extends Subject {
    User login(String username, String password);

    void addUser(User user);

    ArrayList<User> getUserList();

    void logOut(UUID uuid, User user);

    ArrayList<User> getActiveUsers();
}
