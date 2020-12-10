package dk.via.sep.server.networking.chatServerHandler;

import dk.via.sep.server.model.chatServerModel.ChatServerModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatServerHandler implements ChatServer {
    private ChatServerModel chatServerModel;

    public ChatServerHandler(ChatServerModel chatServerModel){
        this.chatServerModel = chatServerModel;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //methods go here


}
