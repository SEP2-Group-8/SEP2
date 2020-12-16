package dk.via.sep.shared.networking.userServerRemote;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserClientCallback extends Remote {

    void updateProfile(User newUser) throws RemoteException;
}
