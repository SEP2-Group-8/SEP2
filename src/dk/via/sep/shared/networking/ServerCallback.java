package dk.via.sep.shared.networking;

import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerCallback extends Remote {
    void registerClient(ClientCallback client, User user) throws RemoteException;
    void login(String username, String password) throws RemoteException;
}
