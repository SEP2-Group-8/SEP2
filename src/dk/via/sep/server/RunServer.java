package dk.via.sep.server;

import dk.via.sep.server.core.ServerDAOFactory;
import dk.via.sep.server.core.ServerFactory;
import dk.via.sep.server.core.ServerModelFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Class that starts the server. <b>Must start this class first, then the client.</b>
 */
public class RunServer {
    /**
     * Main method that initializes all the factories, and creates the registry for the client to access.
     *
     * @param args main method specific, built into java.
     * @throws RemoteException       if there is no internet connection.
     * @throws AlreadyBoundException if the server factory class has already been bound onto the registry.
     * @throws UnknownHostException  if the server cannot find the IP of localhost.
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, UnknownHostException {
        ServerDAOFactory serverDAOFactory = new ServerDAOFactory();
        ServerModelFactory serverModelFactory = new ServerModelFactory(serverDAOFactory);
        ServerFactory serverFactory = new ServerFactory(serverModelFactory);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("RMIServer", serverFactory);
        System.out.println("Server started on: " + InetAddress.getLocalHost().getHostAddress());
    }
}
