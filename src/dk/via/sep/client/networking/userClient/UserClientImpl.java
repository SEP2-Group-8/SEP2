package dk.via.sep.client.networking.userClient;

import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.networking.userServerRemote.UserServerCallback;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

public class UserClientImpl implements UserClient, UserClientCallback {
    private final PropertyChangeSupport support;
    private UserServerCallback server;

    public UserClientImpl() {
        support = new PropertyChangeSupport(this);
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
                server.registerClient(this, LoggedUser.getInstance().getClientID());
            }
            return user;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void registerAccount(User user) {
        boolean success = false;
        try {
            success = server.registerAccount(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(success)
            support.firePropertyChange(UserAction.REGISTER_SUCCESS.toString(),null, null);
        else
            support.firePropertyChange(UserAction.REGISTER_FAILED.toString(), null, null);
    }

    @Override
    public void logOut() {
        try {
            server.logOut();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getUserList() {
        try {
            return server.getUserList();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> getActiveUsers() {
        try {
            return server.getActiveUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAccount() {
        try {
            server.deleteAccount(LoggedUser.getInstance().getUser());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUserDetails(User user) {
        try {
            server.editUserDetails(user, LoggedUser.getInstance().getClientID());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(User newUser) {
        System.out.println(newUser.toString()+" back from server");
        support.firePropertyChange(UserAction.PROFILE_EDIT.toString(), null, newUser);
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
