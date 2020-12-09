package dk.via.sep.server.model.userList;

import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.transfer.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoggedUsers {
    private static LoggedUsers loggedUsers;
    private final ArrayList<User> activeUsers;
    private static final Lock lock = new ReentrantLock();

    private LoggedUsers() {
        activeUsers = new ArrayList<>();
    }

    public static LoggedUsers getInstance() {
        if (loggedUsers == null)
            synchronized (lock) {
                if (loggedUsers == null)
                    loggedUsers = new LoggedUsers();
            }
        return loggedUsers;
    }

    public void addUser(User user) {
        activeUsers.add(user);
    }

    public void removeUser(User user) {
        activeUsers.remove(user);
    }

    public ArrayList<User> getActiveUsers() {
        return activeUsers;
    }
}
