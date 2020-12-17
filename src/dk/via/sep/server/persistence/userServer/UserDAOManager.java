package dk.via.sep.server.persistence.userServer;

import dk.via.sep.server.persistence.Connection;
import dk.via.sep.shared.transfer.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Constantin Mihail
 * @version 1.0.0
 * Implementation of the UserDAO that works on a database hosted on cloud.
 */
public class UserDAOManager extends Connection implements UserDAO {
    /**
     * Method that gets the connection to the database
     * @return a connection to the database.
     * @throws SQLException
     */
    public java.sql.Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    /**
     * This method adds the user to the database after he made his register request. The username, password and email will be stored in the
     * database.
     * @param user this contains the data needed for introducing the user in the database.
     * @return boolean that represents if the action was successful or not.
     */
    @Override
    public boolean addUser(User user) {
        try (java.sql.Connection connection = getConnection()) {
            //The command that is ran after the update is executed.
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO viaclub.useraccount(email,password,username) VALUES(?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
            //This is from where the ? values are assigned from
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUsername());
            //Execute the update
            statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            if (resultset.next()) {
                //Gets the ID for the introduced user and prints it out
                System.out.println("INSERTED with ID " + resultset.getInt("user_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This returns the user based on the two parameters given, his username and his password.
     * @param username the username of the user
     * @param password the password of the user
     * @return
     */
    @Override
    public User getUser(String username, String password) {
        try (java.sql.Connection connection = getConnection()) {
            //The select statement is gotten here
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM viaclub.useraccount WHERE username= ? AND password = ?;");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //Since we will only search for that one specific user and there won't be more than one,
                //this only returns one user, even if the select command can return more than one row.
                int user_id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password_name = resultSet.getString("password");
                String username_name = resultSet.getString("username");
                boolean adminCon = resultSet.getBoolean("admincondition");
                User user = new User(email, password_name, username_name);
                user.setUser_id(user_id);
                user.setAdminCon(adminCon);
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     *This removes the user from the database, searching the user by his username, as the username is an unique attribute in the table.
     * @param user contains the information of the user.
     * @return boolean that represents if the action was successful or not.
     */
    @Override
    public boolean removeUser(User user) {
        try(java.sql.Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM viaclub.useraccount WHERE username = ?");
            statement.setString(1,user.getUsername());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This edits the user information with the new information coming the the parameter. The parameter contains whatever new information
     * was added, while having the old ID
     * @param user this contains the new information of the user with his normal ID.
     */
    @Override
    public void editUser(User user) {
        try(java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE viaclub.useraccount SET username=?,password=?,email=? WHERE user_id = ?");
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getEmail());
            statement.setInt(4,user.getUser_id());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This return an ArrayList of all the users from the database without any condition on selecting them.
     * @return an arraylist of the users that are saved in the database.
     */
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (java.sql.Connection connection = getConnection()) {
            //The select statement is gotten here
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM viaclub.useraccount;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //This searches for all the results that are getting returned by the query and adds the users, one by one
                //to a new arraylist of users. The array list is later returned to the ones asking
                int user_id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password_name = resultSet.getString("password");
                String username_name = resultSet.getString("username");
                boolean adminCon = resultSet.getBoolean("admincondition");
                User user = new User(email, password_name, username_name);
                user.setUser_id(user_id);
                users.add(user);

            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    /**
     * Method that returns all admins from the database. An admin is an user that has his admin condition set as true.
     * @return ArrayList of users with admin condition as true.
     */
    @Override
    public ArrayList<User> getAllAdmins() {
        ArrayList<User> admins = new ArrayList<>();

        try (java.sql.Connection connection = getConnection()) {
            //The select statement is gotten here
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM viaclub.useraccount WHERE admincondition='true';");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //This searches for all the results that are getting returned by the query and adds the users, one by one
                //if they are admins to the arraylist of admins. The array list is later returned to the ones asking.
                int user_id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password_name = resultSet.getString("password");
                String username_name = resultSet.getString("username");
                User user = new User(email, password_name, username_name);
                user.setUser_id(user_id);
                admins.add(user);

            }
            return admins;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
