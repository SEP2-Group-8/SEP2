package dk.via.sep.server.core;

import dk.via.sep.server.persistence.eventServer.EventDAO;
import dk.via.sep.server.persistence.eventServer.EventDAOManager;
import dk.via.sep.server.persistence.userServer.UserDAO;
import dk.via.sep.server.persistence.userServer.UserDAOManager;


public class ServerDAOFactory {

    private UserDAO userDAO;
    private EventDAO eventDAO;

    public ServerDAOFactory() {
        userDAO = new UserDAOManager();
        eventDAO = new EventDAOManager();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }
}
