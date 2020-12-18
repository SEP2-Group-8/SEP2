package dk.via.sep.server.model.userList;

import dk.via.sep.shared.transfer.User;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class used to keep track of the active users, implemented as a singleton so it's available in all servers, if needed.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 2.0
 */
public class LoggedUsers {
    private static LoggedUsers loggedUsers;
    private final ArrayList<User> activeUsers;
    private static final Lock lock = new ReentrantLock();

    /**
     * Private constructor that initializes the list.
     */
    private LoggedUsers() {
        activeUsers = new ArrayList<>();
    }

    /**
     * Static getInstance method used to get access to this class. Also synchronized.
     *
     * @return the loggedUsers class instance.
     */
    public static LoggedUsers getInstance() {
        if (loggedUsers == null)
            synchronized (lock) {
                if (loggedUsers == null)
                    loggedUsers = new LoggedUsers();
            }
        return loggedUsers;
    }

    /**
     * Method used to add a user to the list.
     *
     * @param user the user to be added.
     */
    public void addUser(User user) {
        activeUsers.add(user);
    }

    /**
     * Method used to remove a user from the list.
     *
     * @param user the user to be removed.
     */
    public void removeUser(User user) {
        activeUsers.remove(user);
    }

    /**
     * Method used to retrieve the whole list of active users.
     *
     * @return the list of active users.
     */
    public ArrayList<User> getActiveUsers() {
        return activeUsers;
    }
}
