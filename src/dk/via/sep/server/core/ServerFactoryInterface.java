package dk.via.sep.server.core;

import dk.via.sep.server.networking.eventServerHandler.EventServer;
import dk.via.sep.server.networking.userServerHandler.UserServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Main interface used to provide access to the servers that the client needs to access.
 * Extends remote so it can be accessed over the network.
 *
 * @author Bogdan-Florin Cirstoiu
 */
public interface ServerFactoryInterface extends Remote {
    /**
     * Method used to instantiate and return the user server.
     *
     * @return the user server implementation.
     * @throws RemoteException if the connection cannot be established.
     */
    UserServer getUserServer() throws RemoteException;

    /**
     * Method used to instantiate and return the event server.
     *
     * @return the event server implementation.
     * @throws RemoteException if the connection cannot be established.
     */
    EventServer getEventServer() throws RemoteException;
}
