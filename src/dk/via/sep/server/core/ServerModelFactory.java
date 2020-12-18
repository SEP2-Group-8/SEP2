package dk.via.sep.server.core;

import dk.via.sep.server.model.eventServerModel.EventServerModel;
import dk.via.sep.server.model.eventServerModel.EventServerModelManager;
import dk.via.sep.server.model.userServerModel.UserServerModel;
import dk.via.sep.server.model.userServerModel.UserServerModelManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class used to create and instantiate all the necessary server models.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 1.2
 */
public class ServerModelFactory {
    private final ServerDAOFactory DAOFactory;
    private UserServerModel userServerModel;
    private EventServerModel eventServerModel;
    private final Lock lock;

    /**
     * Public constructor used to pass the DAO factory and to initialize the lock used for synchronization.
     *
     * @param DAOFactory the factory for all data access objects.
     */
    public ServerModelFactory(ServerDAOFactory DAOFactory) {
        this.DAOFactory = DAOFactory;
        lock = new ReentrantLock();
    }

    /**
     * Method used to get the user server model.
     *
     * @return the user server model.
     */
    public UserServerModel getUserServerModel() {
        if (userServerModel == null) {
            synchronized (lock) {
                if (userServerModel == null)
                    userServerModel = new UserServerModelManager(DAOFactory.getUserDAO());
            }
        }
        return userServerModel;
    }

    /**
     * Method used to get the event server model.
     *
     * @return the event server model.
     */
    public EventServerModel getEventServerModel() {
        if (eventServerModel == null) {
            synchronized (lock) {
                if (eventServerModel == null)
                    eventServerModel = new EventServerModelManager(DAOFactory.getEventDAO());
            }
        }
        return eventServerModel;
    }
}
