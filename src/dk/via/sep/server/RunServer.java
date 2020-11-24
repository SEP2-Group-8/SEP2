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

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, UnknownHostException {
        ServerDAOFactory serverDAOFactory = new ServerDAOFactory();
        ServerModelFactory serverModelFactory = new ServerModelFactory(serverDAOFactory);
        ServerFactory serverFactory = new ServerFactory(serverModelFactory);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("RMIServer", serverFactory);
        System.out.println("Server started on: " + InetAddress.getLocalHost().getHostAddress());
    }
}
