package dk.via.sep.server.model.userServerModel;

import dk.via.sep.server.model.userList.LoggedUsers;
import dk.via.sep.server.persistence.userServer.UserDAO;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserServerModelManager implements UserServerModel {
    private final PropertyChangeSupport support;
    private final UserDAO userDAO;
    private final Lock lock;

    public UserServerModelManager(UserDAO userDAO) {
        support = new PropertyChangeSupport(this);
        this.userDAO = userDAO;
        lock = new ReentrantLock();
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public ArrayList<User> getUserList() {
        return userDAO.getAllUsers();
    }

    @Override
    public void logOut() {

    }

    @Override
    public ArrayList<User> getActiveUsers() {
        return LoggedUsers.getInstance().getActiveUsers();
    }

    @Override
    public void deleteAccount(User user) {
        userDAO.removeUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user;
        synchronized (lock) {
            user = userDAO.getUser(username, password);
        }
        if (user != null) {
            LoggedUsers.getInstance().addUser(user);
        }
        return user;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
