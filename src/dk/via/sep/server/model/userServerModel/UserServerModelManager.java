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
    private UserDAO userDAO;
    private final Lock lock;

    public UserServerModelManager(UserDAO userDAO) {
        support = new PropertyChangeSupport(this);
        this.userDAO = userDAO;
        lock = new ReentrantLock();
    }

    @Override
    public void addUser(User user) {
        boolean user_exists = false;
        ArrayList<User> currentUsers;

        currentUsers = userDAO.getAllUsers();
        System.out.println(currentUsers);
        for (User user1 : currentUsers) {
            if (user.equals(user1)) {
                user_exists = true;
                break;
            }
        }
        if (!user_exists) {
            synchronized (lock) {
                userDAO.addUser(user);
            }
            user.setUser_id(userDAO.getUser(user.getUsername(), user.getPassword()).getUser_id());
            LoggedUsers.getInstance().addUser(user);
            support.firePropertyChange(UserAction.REGISTER.toString(), user.getUUID(), UserAction.REGISTER_SUCCESS);
            support.firePropertyChange(UserAction.USER_LIST.toString(),user.getUUID(), UserAction.USER_LIST);
        } else {
            support.firePropertyChange(UserAction.REGISTER.toString(), user.getUUID(), UserAction.REGISTER_FAILED);
        }
    }

    @Override
    public ArrayList<User> getUserList() {
        return userDAO.getAllUsers();
    }

    @Override
    public void logOut(UUID uuid, User user) {
        LoggedUsers.getInstance().removeClient(uuid);
        LoggedUsers.getInstance().removeUser(user);
        System.out.println("Removed id: "+uuid+"\n Removed user: "+user.getUsername());
    }

    @Override
    public ArrayList<User> getActiveUsers() {
        return LoggedUsers.getInstance().getActiveUsers();
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
