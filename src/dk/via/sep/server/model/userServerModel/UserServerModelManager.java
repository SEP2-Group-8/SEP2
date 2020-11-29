package dk.via.sep.server.model.userServerModel;

import dk.via.sep.server.persistence.userServer.UserDAO;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserServerModelManager implements UserServerModel {
    private final PropertyChangeSupport support;
    //private final ArrayList<User> userList;
    private UserDAO userDAO;
    private final Lock lock;

    public UserServerModelManager(UserDAO userDAO) {
        support = new PropertyChangeSupport(this);
        //userList = new ArrayList<>();
        this.userDAO = userDAO;
        lock = new ReentrantLock();
    }

    @Override
    public void addUser(User user) {
        //works, but client errors out because it doesn't handle well the event, needs fixing
        boolean user_exists = false;
        ArrayList<User> currentUsers = new ArrayList<>();

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
            //userList.add(user);
            support.firePropertyChange(UserAction.REGISTER.toString(), user.getUUID(), UserAction.REGISTER_SUCCESS);
        } else {
            support.firePropertyChange(UserAction.REGISTER.toString(), user.getUUID(), UserAction.REGISTER_FAILED);
        }
    }

    @Override
    public User login(String username, String password) {
        User user;
        synchronized (lock) {
            user = userDAO.getUser(username, password);
        }
//        if (user != null) {
//            userList.add(user);
//        }
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
