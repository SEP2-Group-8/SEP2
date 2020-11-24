package dk.via.sep.server.core;

import dk.via.sep.server.persistence.userServer.UserDAO;
import dk.via.sep.server.persistence.userServer.UserDAOManager;


public class ServerDAOFactory {

    private UserDAO userDAO;

    public ServerDAOFactory() {
        userDAO = new UserDAOManager();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
