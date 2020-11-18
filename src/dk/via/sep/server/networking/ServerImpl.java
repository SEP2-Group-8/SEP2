package dk.via.sep.server.networking;

import dk.via.sep.server.model.ServerModel;
import dk.via.sep.shared.networking.ClientCallback;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeEvent;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerImpl implements Server {

    private ServerModel serverModel;
    private Map<Integer, ClientCallback> clientCallbackMap;

    public ServerImpl(ServerModel serverModel) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.serverModel = serverModel;
        clientCallbackMap = new HashMap<>();
        serverModel.addListener(UserAction.LOGIN.toString(), this::loginResult);
        serverModel.addListener(UserAction.REGISTER.toString(), this::registerResult);
    }

    private void registerResult(PropertyChangeEvent propertyChangeEvent) {
        int user_id = (Integer) propertyChangeEvent.getOldValue();
        System.out.println("sending back register response to client");
        try {
            clientCallbackMap.get(user_id).registerResult((UserAction) propertyChangeEvent.getNewValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void loginResult(PropertyChangeEvent propertyChangeEvent) {
        int user_id = (Integer) propertyChangeEvent.getOldValue();
        System.out.println("sending back login response to client");
        try {
            clientCallbackMap.get(user_id).loginResult((UserAction) propertyChangeEvent.getNewValue());
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
    public void registerClient(ClientCallback client, User user) throws RemoteException {
        clientCallbackMap.put(user.getUser_id(), client);
        serverModel.addUser(user);
        System.out.println("registered client");
    }

    @Override
    public void login(String username, String password) throws RemoteException {
        serverModel.login(username, password);
    }
}
