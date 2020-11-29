package dk.via.sep.client.networking.eventClient;

import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.eventServerRemote.EventClientCallback;
import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EventClientImpl implements EventClient, EventClientCallback {
    private EventServerCallback server;

    public EventClientImpl() {

    }

    @Override
    public void startClient() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            server = Connection.getServerFactory().getEventServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
