package dk.via.sep.client.networking.userClient;

import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

/**
 * Local User client interface.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 1.0
 */
public interface UserClient extends Subject {
    /**
     * Start method for this client, making this class remotely available for the server to communicate with it, whilst also initializing
     * the server callback interface, making use of the Connection class to get access to the server factory interface.
     */
    void startClient();

    /**
     * Method used to login the user into the server. It works by providing a username/password combo, and is expected to return
     * a user object that has that username/password combo from the database. If one is found, it will be returned to the user model, otherwise
     * a null object will be returned, meaning there is no user for the specified credentials.
     *
     * @param username user's username
     * @param password user's password
     * @return the user object retrieved from the server (can be null if no user was found)
     */
    User loginUser(String username, String password);

    /**
     * Method used to register an account. Takes as an argument a user object, created by the model, and sends it to the server, and is
     * expected to return true or false, depending if that user exists already or not. If true, it will fire the register success event,
     * if not, the register failed event.
     *
     * @param user the user to be registered.
     */
    void registerAccount(User user);

    /**
     * Method used to log out the client from the server.
     */
    void logOut();

    /**
     * Method used to get the list of users from the database.
     *
     * @return the list of registered users.
     */
    ArrayList<User> getUserList();

    /**
     * Method used to get the list of active users from the server.
     *
     * @return the list of active users.
     */
    ArrayList<User> getActiveUsers();

    /**
     * Method used to delete the currently logged in account from the database.
     */
    void deleteAccount();

    /**
     * Method used to edit the currently logged user's credentials. Takes a user as an argument that contains the changes made.
     *
     * @param user object that contains all changes to be saved.
     */
    void editUserDetails(User user);
}
