package dk.via.sep.server.networking.userServerHandler;

import dk.via.sep.server.model.userServerModel.UserServerModel;
import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The user server implementation, that handles all user related actions, such as logging in, registering, etc.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 2.1
 */
public class UserServerHandler implements UserServer {

    private final UserServerModel userServerModel;
    private PropertyChangeListener profileUpdateListener;

    /**
     * Public constructor that initializes the server model and makes this object remotely available for the client to use
     * through the callback interface.
     *
     * @param userServerModel the user server model created by the model factory.
     */
    public UserServerHandler(UserServerModel userServerModel) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.userServerModel = userServerModel;
    }

    /**
     * Method used to register the client for <b>callbacks</b>.
     * Each <b>callback</b> <b><i>will need</i></b> it's own PropertyChangeListener created as a field variable, and it's own UserAction enum,
     * and follow the structure shown below.
     *
     * @param client   the client callback interface.
     * @param clientID the client's UUID.
     */
    @Override
    public void registerClient(UserClientCallback client, UUID clientID) {
        profileUpdateListener = (event) -> {
            try {
                client.updateProfile((User) event.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
                userServerModel.removeListener(UserAction.PROFILE_EDIT.toString() + clientID, profileUpdateListener);
            }
        };

        userServerModel.addListener(UserAction.PROFILE_EDIT.toString() + clientID, profileUpdateListener);
    }

    /**
     * Method used to log in a user into the app. It works by calling the server model and requesting a user with the specified credentials.
     * If there is a user with such credentials, it will be returned, otherwise, a null user will be returned, notifying the client that
     * either the username or the password are not correct.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return the user that matches the credentials from the parameters.
     */
    @Override
    public User login(String username, String password) {
        return userServerModel.login(username, password);
    }

    /**
     * Method used to register a user into the database. The user object needs to be created by the client and passed down as an argument.
     *
     * @param user the user to be registered.
     * @return boolean if the register process was a success or failed.
     */
    @Override
    public boolean registerAccount(User user) {
        return userServerModel.addUser(user);
    }

    /**
     * Method used to log out the client. Currently, not necessary, but the event listeners will remain even after a client is disconnected.
     * If the server tries to call a client that is no longer available, the error will be caught and the listener will be removed.
     * <b>That is not a permanent solution.</b>
     */
    @Override
    public void logOut() {
        userServerModel.logOut();
    }

    /**
     * Method used to get the list of users that exist currently in the database.
     *
     * @return the list of users.
     */
    @Override
    public ArrayList<User> getUserList() {
        return userServerModel.getUserList();
    }

    /**
     * Method used to get the list of currently active users.
     * <b><i>Warning:</i></b> this method will return an empty list, as it has not been finished. It will be usable in a future release.
     *
     * @return the list of currently active users.
     */
    @Override
    public ArrayList<User> getActiveUsers() {
        return userServerModel.getActiveUsers();
    }

    /**
     * Method used to delete a user's account from the database.
     *
     * @param user the user to be deleted.
     */
    @Override
    public void deleteAccount(User user) {
        userServerModel.deleteAccount(user);
    }

    /**
     * Method used to edit a user's credentials. You <b>need</b> to provide the client's UUID so the profile tab updates correctly on
     * the client's side.
     *
     * @param user     the user to be edited, along with the new details.
     * @param clientID the client's UUID
     */
    @Override
    public void editUserDetails(User user, UUID clientID) {
        userServerModel.editUserDetails(user, clientID);
    }
}
