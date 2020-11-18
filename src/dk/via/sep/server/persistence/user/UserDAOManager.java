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
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO viaclub.useraccount(email,password,username) VALUES(?,?,?);",
            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getUsername());
            statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            if(resultset.next())
            {
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
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM viaclub.useraccount WHERE username= ? AND password = ?;");
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
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
