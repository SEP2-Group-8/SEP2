package dk.via.sep.client.networking.userClient;

import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.networking.userServerRemote.userServerCallback;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class UserClientImpl implements UserClient, UserClientCallback {
    private final PropertyChangeSupport support;
    private userServerCallback server;
    private final UUID CLIENT_ID;

    public UserClientImpl() {
        support = new PropertyChangeSupport(this);
        CLIENT_ID = UUID.randomUUID();
    }

    @Override
    public void startClient() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            server = Connection.getServerFactory().getUserServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User loginUser(String username, String password) {
        try {
            User user = server.login(username, password);
            if (user != null) {
                System.out.println("User id: " + user.getUUID());
                server.registerClient(this, user.getUUID());
            }
            return user;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void registerAccount(User user) {
        try {
            server.registerNewAccount(this, user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    @Override
    public void loginOrRegisterResult(UserAction result) throws RemoteException {
        System.out.println("Register result: " + result.toString());
        support.firePropertyChange(result.toString(), null, null);
    }
}
