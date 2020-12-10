package dk.via.sep.client.networking.eventClient;

import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.eventServerRemote.EventClientCallback;
import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.beans.PropertyChangeListener;
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
    public boolean createEvent(Event event) {
        try {
            return server.createEvent(event);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeEvent(Event event) {
        try {
            return server.removeEvent(event);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editEvent(Event newEvent) {
        try {
            return server.editEvent(newEvent);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Event> getEventList() {
        try {
            return server.getEventList();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean joinEvent(User user, Event event, boolean b) {
        try{
            System.out.println("I got here -> client");
            return server.joinEvent(user, event,b);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean leaveEvent(User user, Event event) {
        try{
            return server.leaveEvent(user,event);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<User> getUserList(Event event) {
        try{
            return server.getUserList(event);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
