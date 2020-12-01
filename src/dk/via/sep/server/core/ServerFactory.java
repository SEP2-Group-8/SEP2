package dk.via.sep.server.core;

import dk.via.sep.server.networking.eventServerHandler.EventServer;
import dk.via.sep.server.networking.eventServerHandler.EventServerHandler;
import dk.via.sep.server.networking.userServerHandler.UserServer;
import dk.via.sep.server.networking.userServerHandler.UserServerHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerFactory implements ServerFactoryInterface {
    private final ServerModelFactory serverModelFactory;
    private UserServer userServer;
    private EventServer eventServer;
    private final Lock lock;

    public ServerFactory(ServerModelFactory serverModelFactory) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.serverModelFactory = serverModelFactory;
        lock = new ReentrantLock();
    }

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
