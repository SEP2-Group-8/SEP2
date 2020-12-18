package dk.via.sep.client.networking;

import dk.via.sep.server.core.ServerFactoryInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * A class responsible for getting the correct server stub from the registry, so the clients can have access to their respective servers
 *
 * @author Bogdan-Florin Cirstoiu
 */
public class Connection {
    private static ServerFactoryInterface serverFactory;

    /**
     * Private constructor, no need for one
     */
    private Connection() {
    }

    /**
     * A static method used to retrieve the server factory stub.
     *
     * @return the server factory stub.
     */
    public static ServerFactoryInterface getServerFactory() {
        if (serverFactory == null) {
            try {
                serverFactory = (ServerFactoryInterface) LocateRegistry.getRegistry("localhost", 1099).lookup("RMIServer");
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        return serverFactory;
    }
}
