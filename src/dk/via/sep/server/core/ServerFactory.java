package dk.via.sep.server.core;

import dk.via.sep.server.networking.eventServerHandler.EventServer;
import dk.via.sep.server.networking.eventServerHandler.EventServerHandler;
import dk.via.sep.server.networking.userServerHandler.UserServer;
import dk.via.sep.server.networking.userServerHandler.UserServerHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class used to create, instantiate, and get the server classes.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 1.3
 */
public class ServerFactory implements ServerFactoryInterface {
    private final ServerModelFactory serverModelFactory;
    private UserServer userServer;
    private EventServer eventServer;
    private final Lock lock;

    /**
     * Public constructor user to make this class remotely available, so the client can use its interface.
     *
     * @param serverModelFactory model factory needed to instantiate the servers.
     * @throws RemoteException if the connection cannot be established.
     */
    public ServerFactory(ServerModelFactory serverModelFactory) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.serverModelFactory = serverModelFactory;
        lock = new ReentrantLock();
    }

    /**
     * Method used to create and instantiate the user server.
     *
     * @return the user server.
     */
    @Override
    public UserServer getUserServer() {
        if (userServer == null) {
            synchronized (lock) {
                if (userServer == null) {
                    userServer = new UserServerHandler(serverModelFactory.getUserServerModel());
                }
            }
        }
        return userServer;
    }

    /**
     * Method used to create and instantiate the event server.
     *
     * @return the event server.
     */
    @Override
    public EventServer getEventServer() {
        if (eventServer == null) {
            synchronized (lock) {
                if (eventServer == null) {
                    eventServer = new EventServerHandler(serverModelFactory.getEventServerModel());
                }
            }
        }
        return eventServer;
    }
}
