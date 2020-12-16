package dk.via.sep.server.networking.userServerHandler;

import dk.via.sep.server.model.userServerModel.UserServerModel;
import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

public class UserServerHandler implements UserServer {

    private final UserServerModel userServerModel;
    private PropertyChangeListener profileUpdateListener;

    public UserServerHandler(UserServerModel userServerModel) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.userServerModel = userServerModel;

    }

    @Override
    public void registerClient(UserClientCallback clientCallback, UUID clientID) {
        profileUpdateListener = (event) -> {
            try {
                clientCallback.updateProfile((User) event.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
                userServerModel.removeListener(UserAction.PROFILE_EDIT.toString() + clientID, profileUpdateListener);
            }
        };

        userServerModel.addListener(UserAction.PROFILE_EDIT.toString() + clientID, profileUpdateListener);
    }

    @Override
    public User login(String username, String password) {
        return userServerModel.login(username, password);
    }

    @Override
    public boolean registerAccount(User user) {
        return userServerModel.addUser(user);
    }

    @Override
    public void logOut() {
        userServerModel.logOut();
    }

    @Override
    public ArrayList<User> getUserList() {
        return userServerModel.getUserList();
    }

    @Override
    public ArrayList<User> getActiveUsers() {
        return userServerModel.getActiveUsers();
    }

    @Override
    public void deleteAccount(User user) {
        userServerModel.deleteAccount(user);
    }

    @Override
    public void editUserDetails(User user, UUID clientID) {
        userServerModel.editUserDetails(user, clientID);
    }
}
