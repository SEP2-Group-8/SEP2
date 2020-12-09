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
    public void registerClient(UserClientCallback client) throws RemoteException {
        //depends on what else we add if we need this method or not
    }

    @Override
    public User login(String username, String password) throws RemoteException {
        return userServerModel.login(username, password);
    }

    @Override
    public boolean registerAccount(User user) throws RemoteException {
        return userServerModel.addUser(user);
    }

    @Override
    public void logOut() throws RemoteException {
        userServerModel.logOut();
    }

    @Override
    public ArrayList<User> getUserList() throws RemoteException {
        return userServerModel.getUserList();
    }

    @Override
    public ArrayList<User> getActiveUsers() throws RemoteException {
        return userServerModel.getActiveUsers();
    }
}
