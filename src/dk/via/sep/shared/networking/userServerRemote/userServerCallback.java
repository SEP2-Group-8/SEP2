package dk.via.sep.shared.networking.userServerRemote;

import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface userServerCallback extends Remote {

    void registerNewAccount(UserClientCallback client, User user) throws RemoteException;

    User login(String username, String password) throws RemoteException;

    void registerClient(UserClientCallback client, UUID id) throws RemoteException;

    void logOut(UUID uuid) throws RemoteException;
}
