package dk.via.sep.client.networking;

import dk.via.sep.server.core.ServerFactoryInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class  Connection {
    private static ServerFactoryInterface serverFactory;

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
