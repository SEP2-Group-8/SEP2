package dk.via.sep.server.persistence.userServer;

import dk.via.sep.shared.transfer.User;

import java.util.ArrayList;

/**
 * @author Constantin Mihail
 * @version 1.0.0
 */
public interface UserDAO {
    /**
     * This method adds the user to the database after he made his register request. The username, password and email will be stored in the
     * database.
     *
     * @param user this contains the data needed for introducing the user in the database.
     * @return boolean that represents if the action was successful or not.
     */
    boolean addUser(User user);

    /**
     * This returns the user based on the two parameters given, his username and his password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return
     */
    User getUser(String username, String password);

    /**
     * This removes the user from the database, searching the user by his username, as the username is an unique attribute in the table.
     *
     * @param user contains the information of the user.
     * @return boolean that represents if the action was successful or not.
     */
    boolean removeUser(User user);

    /**
     * This edits the user information with the new information coming the the parameter. The parameter contains whatever new information
     * was added, while having the old ID
     *
     * @param user this contains the new information of the user with his normal ID.
     */
    void editUser(User user);

    /**
     * This return an ArrayList of all the users from the database without any condition on selecting them.
     *
     * @return an arraylist of the users that are saved in the database.
     */
    ArrayList<User> getAllUsers();

    /**
     * Method that returns all admins from the database. An admin is an user that has his admin condition set as true.
     *
     * @return ArrayList of users with admin condition as true.
     */
    ArrayList<User> getAllAdmins();
}
