package dk.via.sep.server.networking.userServerHandler;

import dk.via.sep.server.model.userServerModel.UserServerModel;
import dk.via.sep.server.model.userList.LoggedUsers;
import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeEvent;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class UserServerHandler implements UserServer {

    private final UserServerModel userServerModel;

    public UserServerHandler(UserServerModel userServerModel) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.userServerModel = userServerModel;
        userServerModel.addListener(UserAction.REGISTER.toString(), this::registerResult);
    }

    private void registerResult(PropertyChangeEvent propertyChangeEvent) {
        UUID user_id = (UUID) propertyChangeEvent.getOldValue();
        System.out.println("sending back login response to client id " + user_id);
        try {
            UserClientCallback client = LoggedUsers.getInstance().getClient(user_id);
            UserAction action = (UserAction) propertyChangeEvent.getNewValue();
            client.loginOrRegisterResult(action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startServer() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("RMIServer", this);
    }

    @Override
    public User login(String username, String password) throws RemoteException {
        return userServerModel.login(username, password);
    }

    @Override
    public void registerNewAccount(UserClientCallback client, User user) throws RemoteException {
        LoggedUsers.getInstance().addClient(user.getUUID(), client);
        userServerModel.addUser(user);
        System.out.println("Adding client with id " + user.getUUID());
    }

    @Override
    public void registerClient(UserClientCallback client, UUID id) throws RemoteException {
        LoggedUsers.getInstance().addClient(id, client);
    }
}
