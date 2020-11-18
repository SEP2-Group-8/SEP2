package dk.via.sep.client.networking;

import dk.via.sep.shared.networking.ClientCallback;
import dk.via.sep.shared.networking.ServerCallback;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl implements Client, ClientCallback {
    PropertyChangeSupport support;
    ServerCallback server;

    public ClientImpl() {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void startClient() {
        Registry registry = null;
        try {
            UnicastRemoteObject.exportObject(this, 0);
            registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (ServerCallback) registry.lookup("RMIServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String username, String password) {
        try {
            server.login(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(User user) {
        try {
            server.registerClient(this, user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginResult(UserAction result) {
        support.firePropertyChange(result.toString(), null, result);
        System.out.println("Login result: " + result.toString());
    }

    @Override
    public void registerResult(UserAction result) throws RemoteException {
        System.out.println("Register result: " + result.toString());
        support.firePropertyChange(result.toString(), null, result);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
