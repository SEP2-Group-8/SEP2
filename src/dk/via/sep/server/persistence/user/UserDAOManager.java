package dk.via.sep.server.persistence.user;

import dk.via.sep.server.Connection;
import dk.via.sep.shared.transfer.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOManager extends Connection implements UserDAO {

    public java.sql.Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void addUser(User user) {
        try (java.sql.Connection connection = getConnection())
        {
            //The command that is ran after the update is executed.
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO viaclub.useraccount(email,password,username) VALUES(?,?,?);",
            Statement.RETURN_GENERATED_KEYS);
            //This is from where the ? values are assigned from
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getUsername());
            //Execute the update
            statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            if(resultset.next())
            {
                //Gets the ID for the introduced user and prints it out
                System.out.println("INSERTED with ID " + resultset.getInt("user_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUser(String username, String password) {
        try(java.sql.Connection connection = getConnection())
        {
            //The select statement is gotten here
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM viaclub.useraccount WHERE username= ? AND password = ?;");
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                //Since we will only search for that one specific user and there won't be more than one,
                //this only returns one user, even if the select command can return more than one row.
                int user_id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password_name = resultSet.getString("password");
                String username_name = resultSet.getString("username");
                User user = new User( email,password_name,username_name);
                user.setUser_id(user_id);
                return user;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
