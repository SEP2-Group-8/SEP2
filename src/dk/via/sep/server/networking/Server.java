package dk.via.sep.server.networking;

import dk.via.sep.shared.networking.ServerCallback;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends ServerCallback, Remote {
    void startServer() throws RemoteException, AlreadyBoundException;
}
