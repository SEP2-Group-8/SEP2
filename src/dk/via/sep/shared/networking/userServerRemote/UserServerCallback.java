package dk.via.sep.shared.networking.userServerRemote;

import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

public interface UserServerCallback extends Remote {

    boolean registerAccount(User user) throws RemoteException;

    User login(String username, String password) throws RemoteException;

    void registerClient(UserClientCallback client) throws RemoteException;

    void logOut() throws RemoteException;

    ArrayList<User> getUserList() throws RemoteException;

    ArrayList<User> getActiveUsers() throws RemoteException;

    void deleteAccount(User user) throws RemoteException;
}