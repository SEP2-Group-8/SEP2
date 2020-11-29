package dk.via.sep.server.core;

import dk.via.sep.server.model.eventServerModel.EventServerModel;
import dk.via.sep.server.model.eventServerModel.EventServerModelManager;
import dk.via.sep.server.model.userServerModel.UserServerModel;
import dk.via.sep.server.model.userServerModel.UserServerModelManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerModelFactory {
    private ServerDAOFactory DAOFactory;
    private UserServerModel userServerModel;
    private EventServerModel eventServerModel;
    private Lock lock;

    public ServerModelFactory(ServerDAOFactory DAOFactory) {
        this.DAOFactory = DAOFactory;
        lock = new ReentrantLock();
    }

    public UserServerModel getUserServerModel() {
        if (userServerModel == null) {
            synchronized (lock) {
                if (userServerModel == null)
                    userServerModel = new UserServerModelManager(DAOFactory.getUserDAO());
            }
        }
        return userServerModel;
    }
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
