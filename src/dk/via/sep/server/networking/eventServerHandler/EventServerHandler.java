package dk.via.sep.server.networking.eventServerHandler;

import dk.via.sep.server.model.eventServerModel.EventServerModel;
import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EventServerHandler implements EventServer {

    private final EventServerModel eventServerModel;

    public EventServerHandler(EventServerModel eventServerModel) {
        this.eventServerModel = eventServerModel;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }




}
