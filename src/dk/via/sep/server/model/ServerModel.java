package dk.via.sep.server.model;

import dk.via.sep.server.persistence.user.UserDAO;
import dk.via.sep.server.persistence.user.UserDAOManager;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServerModel implements Subject {
    private final PropertyChangeSupport support;
    private final ArrayList<User> userList;
    private final UserDAO userDAO;

    public ServerModel(){
        support = new PropertyChangeSupport(this);
        userList = new ArrayList<>();
        userDAO = new UserDAOManager();
    }

    public void addUser(User user){
        boolean user_exists = false;
        for (User user1 : userList) {
            if(user.equals(user1)) {
                user_exists = true;
                break;
            }
        }
        if(!user_exists) {
            userList.add(user);
            //userDAO.addUser(user);
            support.firePropertyChange(UserAction.REGISTER.toString(), user.getUser_id(), UserAction.REGISTER_SUCCESS);
            System.out.println("client does not already exist");
        } else {
            support.firePropertyChange(UserAction.REGISTER.toString(), user.getUser_id(), UserAction.REGISTER_FAILED);
            System.out.println("client already exists");
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    public void login(String username, String password) {
        System.out.println("user successfully logged in");
    }
}
