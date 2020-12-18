package dk.via.sep.shared.networking.userServerRemote;

import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Callback interface for the client to use remotely. All of them are called only by the client, through RMI.
 *
 * @author Bogdan-Florin Cirstoiu
 */
public interface UserServerCallback extends Remote {

    /**
     * Method used to register an account into the database.
     *
     * @param user the user to be registered.
     * @return boolean if the user has been successfully registered or not.
     * @throws RemoteException if the connection cannot be established.
     */
    boolean registerAccount(User user) throws RemoteException;

    /**
     * Method used to login in a client into the database.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return the user that has the credentials as specified in the parameters. Will be null if there is no such user.
     * @throws RemoteException if the connection cannot be established.
     */
    User login(String username, String password) throws RemoteException;

    /**
     * Method used to register a client interface to the server, so it can be used to send data back to the client.
     * This method is a <b>callback</b>.
     *
     * @param client   the client callback interface.
     * @param clientID the client's UUID.
     * @throws RemoteException if the connection cannot be established.
     */
    void registerClient(UserClientCallback client, UUID clientID) throws RemoteException;

    /**
     * Method used to log out the client from the server.
     *
     * @throws RemoteException if the connection cannot be established.
     */
    void logOut() throws RemoteException;

    /**
     * Method used to get the list of currently existing users.
     *
     * @return the list of currently existing users.
     * @throws RemoteException if the connection cannot be established.
     */
    ArrayList<User> getUserList() throws RemoteException;

    /**
     * Method used to get the list of currently active users.
     *
     * @return the method does not return anything, as it isn't necessary at the time of writing.
     * @throws RemoteException if the connection cannot be established.
     */
    ArrayList<User> getActiveUsers() throws RemoteException;

    /**
     * Method used to delete a user account from the database.
     * <b><i>Warning:</i></b> this user needs to have an ID, that has been attached to it from the database, otherwise, the DAO will throw
     * an exception.
     *
     * @param user the user to be deleted.
     * @throws RemoteException if the connection cannot be established.
     */
    void deleteAccount(User user) throws RemoteException;

    /**
     * Method used to edit a user's credentials.
     * <b><i>Warning:</i></b> the user needs to have the changes applied to it as well, and contain an ID that has been attached to it from
     * the database, otherwise, the DAO will throw an exception.
     *
     * @param user     the user to be edited, along with the new details.
     * @param clientID the client's UUID
     * @throws RemoteException if the connection cannot be established.
     */
    void editUserDetails(User user, UUID clientID) throws RemoteException;
}