package dk.via.sep.client.networking.eventClient;

import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.eventServerRemote.EventClientCallback;
import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.UserAction;
import dk.via.sep.shared.transfer.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

public class EventClientImpl implements EventClient, EventClientCallback {
    private EventServerCallback server;
    private PropertyChangeSupport support;

    public EventClientImpl() {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void startClient() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            server = Connection.getServerFactory().getEventServer();
            server.registerGeneralClientCallback(this);
            server.registerClientCallback(this, LoggedUser.getInstance().getClientID());
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
    public void editEvent(Event newEvent) {
        try {
            server.editEvent(newEvent);
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
    public void getEventListAsync() {
        try {
            server.getEventListASync(LoggedUser.getInstance().getClientID());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eventCreated(Event event) {
        support.firePropertyChange(UserAction.EVENT_CREATE.toString(), null, event);
    }

    @Override
    public void eventEdited(Event event) {
        support.firePropertyChange(UserAction.EVENT_EDIT.toString(), null, event);
    }

    @Override
    public void eventRemoved(Event event) {
        support.firePropertyChange(UserAction.EVENT_REMOVE.toString(), null, event);
    }

    @Override
    public void retrieveEventList(ArrayList<Event> events) {
        support.firePropertyChange(UserAction.EVENT_LIST.toString(), null, events);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
