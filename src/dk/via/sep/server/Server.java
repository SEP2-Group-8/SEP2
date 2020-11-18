package dk.via.sep.server;

import dk.via.sep.server.model.ServerModel;
import dk.via.sep.server.networking.ServerImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, UnknownHostException {
        ServerModel serverModel = new ServerModel();
        ServerImpl server = new ServerImpl(serverModel);
        server.startServer();
        System.out.println("Server started on: "+ InetAddress.getLocalHost().getHostAddress());
    }
}
