package dk.via.sep.server.networking.userServerHandler;

import dk.via.sep.shared.networking.userServerRemote.UserServerCallback;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserServer extends UserServerCallback, Remote {

}
