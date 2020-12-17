package dk.via.sep.server.core;

import dk.via.sep.server.persistence.eventServer.EventDAO;
import dk.via.sep.server.persistence.eventServer.EventDAOManager;
import dk.via.sep.server.persistence.userServer.UserDAO;
import dk.via.sep.server.persistence.userServer.UserDAOManager;

/**
 * @author Constantin Mihail
 * @version 1.0.0
 * Factory for the DAO
 *
 */
public class ServerDAOFactory {

    private final UserDAO userDAO;
    private final EventDAO eventDAO;

    /**
     * Constructor that specifies what type of implementation of the interfaces are the fiels..
     */
    public ServerDAOFactory() {
        userDAO = new UserDAOManager();
        eventDAO = new EventDAOManager();
    }

    /**
     * Getter that returns the userDAO
     * @return userDAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Getter that returns the EventDAO
     * @return eventDAO
     */
    public EventDAO getEventDAO() {
        return eventDAO;
    }
}
