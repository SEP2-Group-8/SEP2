package dk.via.sep.shared.networking;

import dk.via.sep.shared.utils.UserAction;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void loginResult(UserAction result) throws RemoteException;
    void registerResult(UserAction result) throws RemoteException;
}
