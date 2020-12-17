package userDAOTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import dk.via.sep.server.persistence.userServer.UserDAO;
import dk.via.sep.server.persistence.userServer.UserDAOManager;
import dk.via.sep.shared.transfer.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class userDAOTest {

    User user;
    UserDAO userDAO;


    @BeforeEach
    public void setup()
    {
        userDAO = new UserDAOManager();
        String username= "test";
        String password= "1234";
        String email= "gmail@gmail.com";
        user = new User(email,password,username);
        if(userDAO.getUser(username,password) !=null)
            user.setUser_id(userDAO.getUser(username,password).getUser_id());

    }

    @Test
    public void create_valid_user_test()
    {
        assertTrue(userDAO.addUser(user));
    }

    @Test
    public void create_failed_user_test()
    {
        assertFalse(userDAO.addUser(user));
    }

    @Test
    public void get_user_list()
    {
        ArrayList<User> userList = userDAO.getAllUsers();
        assertNotNull(userList);
    }

    @Test
    public void get_admin_list()
    {
        ArrayList<User> adminList = userDAO.getAllUsers();
        assertNotNull(adminList);
    }

    @Test
    public void edit_test_user_test1()
    {
        User placeholder = new User("placeholher","placeholder","placeholder");
        placeholder.setUsername(user.getUsername());
        placeholder.setPassword(user.getPassword());
        placeholder.setEmail(user.getEmail());
        User newUser = user;
        newUser.setUsername("test1");
        userDAO.editUser(newUser);
        assertNull(userDAO.getUser(placeholder.getUsername(), placeholder.getPassword()));
    }

    @Test
    public void remove_user_test1()
    {
        User newUser = user;
        newUser.setUsername("test1");
        userDAO.removeUser(newUser);
        assertNull(userDAO.getUser(newUser.getUsername(),newUser.getPassword()));
    }
}
