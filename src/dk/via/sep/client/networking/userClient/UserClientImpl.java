package dk.via.sep.client.networking.userClient;

import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.networking.userServerRemote.UserServerCallback;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Class responsible for all user related actions, such as registering, logging in, getting the user list, etc.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 1.0
 */
public class UserClientImpl implements UserClient, UserClientCallback {
    private final PropertyChangeSupport support;
    private UserServerCallback server;

    /**
     * Public constructor used to initialize the property change support so it can fire events.
     */
    public UserClientImpl() {
        support = new PropertyChangeSupport(this);
    }

    /**
     * Start method for this client, making this class remotely available for the server to communicate with it, whilst also initializing
     * the server callback interface, making use of the Connection class to get access to the server factory interface.
     */
    @Override
    public void startClient() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            server = Connection.getServerFactory().getUserServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to login the user into the server. It works by providing a username/password combo, and is expected to return
     * a user object that has that username/password combo from the database. If one is found, it will be returned to the user model, otherwise
     * a null object will be returned, meaning there is no user for the specified credentials.
     *
     * @param username The user's username
     * @param password The user's password
     * @return A user object, retrieved from the database, that matches the username/password combo
     */
    @Override
    public User loginUser(String username, String password) {
        try {
            User user = server.login(username, password);
            if (user != null) {
                server.registerClient(this, LoggedUser.getInstance().getClientID());
            }
            return user;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used to register an account. Takes as an argument a user object, created by the model, and sends it to the server, and is
     * expected to return true or false, depending if that user exists already or not. If true, it will fire the register success event,
     * if not, the register failed event.
     *
     * @param user The user to be registered.
     */
    @Override
    public void registerAccount(User user) {
        boolean success = false;
        try {
            success = server.registerAccount(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (success)
            support.firePropertyChange(UserAction.REGISTER_SUCCESS.toString(), null, null);
        else
            support.firePropertyChange(UserAction.REGISTER_FAILED.toString(), null, null);
    }

    /**
     * Method used to log out the client from the server.
     */
    @Override
    public void logOut() {
        try {
            server.logOut();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to get the list of users from the database.
     *
     * @return the list of registered users.
     */
    @Override
    public ArrayList<User> getUserList() {
        try {
            return server.getUserList();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method used to get the list of active users from the server.
     *
     * @return the list of active users.
     */
    @Override
    public ArrayList<User> getActiveUsers() {
        try {
            return server.getActiveUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method used to delete the currently logged in account from the database.
     */
    @Override
    public void deleteAccount() {
        try {
            server.deleteAccount(LoggedUser.getInstance().getUser());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to edit the currently logged user's credentials. Takes a user as an argument that contains the changes made.
     *
     * @param user object that contains all changes to be saved.
     */
    @Override
    public void editUserDetails(User user) {
        try {
            server.editUserDetails(user, LoggedUser.getInstance().getClientID());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method called by the server used to provide the new user object with all saved changes. Will only be called if the editUserDetails method
     * is called. Also fires an event to notify the model of this action.
     *
     * @param newUser updated user object with the changes specified.
     */
    @Override
    public void updateProfile(User newUser) {
        System.out.println(newUser.toString() + " back from server");
        support.firePropertyChange(UserAction.PROFILE_EDIT.toString(), null, newUser);
    }

    /**
     * Method used to add listeners to this class, making use of the observer pattern.
     *
     * @param eventName name of the event to listen for
     * @param listener  the listener of the event
     */
    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    /**
     * Method used to remove listeners of this class, making use of the observer pattern.
     *
     * @param eventName name of the event to listen for
     * @param listener  the listener of the event
     */
    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
