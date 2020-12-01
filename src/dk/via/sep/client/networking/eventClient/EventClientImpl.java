package dk.via.sep.client.networking.eventClient;

import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.eventServerRemote.EventClientCallback;
import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;
import dk.via.sep.shared.transfer.Event;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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

    @Override
    public void createEvent(Event event) {
        try {
            server.createEvent(event);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEvent(Event event) {
        try {
            server.removeEvent(event);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editEvent(Event oldEvent, Event newEvent) {
        try {
            server.editEvent(oldEvent, newEvent);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Event> getEventList() {
        try {
            return server.getEventList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }


}
