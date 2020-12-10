package dk.via.sep.server.core;

import dk.via.sep.server.networking.chatServerHandler.ChatServer;
import dk.via.sep.server.networking.eventServerHandler.EventServer;
import dk.via.sep.server.networking.userServerHandler.UserServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerFactoryInterface extends Remote {
    UserServer getUserServer() throws RemoteException;

    EventServer getEventServer() throws RemoteException;

    ChatServer getChatServer() throws RemoteException;
}
