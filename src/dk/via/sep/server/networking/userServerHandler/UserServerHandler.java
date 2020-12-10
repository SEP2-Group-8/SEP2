package dk.via.sep.server.networking.userServerHandler;

import dk.via.sep.server.model.userServerModel.UserServerModel;
import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.transfer.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UserServerHandler implements UserServer {

    private final UserServerModel userServerModel;

    public UserServerHandler(UserServerModel userServerModel) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.userServerModel = userServerModel;
    }

    @Override
    public void registerClient(UserClientCallback client) {
        //depends on what else we add if we need this method or not
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
}
