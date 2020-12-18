package dk.via.sep.client.networking.eventClient;

import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.networking.Connection;
import dk.via.sep.shared.networking.eventServerRemote.EventClientCallback;
import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class EventClientImpl implements EventClient, EventClientCallback {
    private EventServerCallback server;
    private final PropertyChangeSupport support;

    /**
     * Public constructor used to initialize the property change support so it can fire events.
     */
    public EventClientImpl() {
        support = new PropertyChangeSupport(this);
    }

    /**
     * Start method used to make this class remotely available for the server to call methods on it.
     * It also initializes the connection to the server by making use of the Connection class to get access to the server factory interface,
     * whilst also registering this client for general and specific callbacks.
     */
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

    /**
     * Method used to create an event and add it into the database.
     *
     * @param event the event to be created.
     */
    @Override
    public void createEvent(Event event) {
        try {
            server.createEvent(event);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to remove an event from the database.
     *
     * @param event the event to be removed.
     */
    @Override
    public void removeEvent(Event event) {
        try {
            server.removeEvent(event);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to edit an event. You <b>must</b> have the changes already applied, after which this event will be compared with the full
     * list in the database, and the event that you edited will be replaced by the parameter.
     *
     * @param oldEvent the event to be edited, with the changes already applied to it.
     */
    @Override
    public void editEvent(Event oldEvent) {
        try {
            server.editEvent(oldEvent);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to get the list of events from the server.
     *
     * @return the list of events.
     * @deprecated use the async version instead.
     */
    @Override
    public ArrayList<Event> getEventList() {
        try {
            return server.getEventList();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method used to join the currently logged in user to an event.
     *
     * @param user  the user to be joined.
     * @param event the event to join to.
     * @param b     boolean that specifies if the user wants a bus seat or not.
     * @return boolean if the user was successfully joined or not to the event.
     */
    @Override
    public boolean joinEvent(User user, Event event, boolean b) {
        try {
            System.out.println("I got here -> client");
            return server.joinEvent(user, event, b);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method used to remove the currently logged in user from an event.
     *
     * @param user  the user to be removed.
     * @param event the event to be removed from.
     * @return boolean if the user was successfully removed or not.
     */
    @Override
    public boolean leaveEvent(User user, Event event) {
        try {
            return server.leaveEvent(user, event);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method used to ge the list of users from the database.
     *
     * @param event the event to get the list from.
     * @return the list of users.
     */
    @Override
    public ArrayList<User> getUserList(Event event) {
        try {
            return server.getUserList(event);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method used to get the list of events asynchronous from the server. Calls a method on the server that fetches the event list,
     * then calls the retrieveEventList method below to provide the list as a parameter.
     */
    @Override
    public void getEventListAsync() {
        try {
            server.getEventListASync(LoggedUser.getInstance().getClientID());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to notify the user that an event has been created.
     *
     * @param event the event that has been created.
     */
    @Override
    public void eventCreated(Event event) {
        support.firePropertyChange(UserAction.EVENT_CREATE.toString(), null, event);
    }

    /**
     * Method used to notify the user that an event has been edited.
     *
     * @param event the edited event.
     */
    @Override
    public void eventEdited(Event event) {
        support.firePropertyChange(UserAction.EVENT_EDIT.toString(), null, event);
    }

    /**
     * Method used to notify the user that an event has been removed.
     *
     * @param event the removed event.
     */
    @Override
    public void eventRemoved(Event event) {
        support.firePropertyChange(UserAction.EVENT_REMOVE.toString(), null, event);
    }

    /**
     * Method called remotely by the server to provide the event list. This method will only be called if the
     * getEventListAsync() method is called first.
     *
     * @param events the list of events.
     */
    @Override
    public void retrieveEventList(ArrayList<Event> events) {
        support.firePropertyChange(UserAction.EVENT_LIST.toString(), null, events);
    }

    /**
     * Method used to add a listener to this class.
     *
     * @param eventName name of the event to listen for
     * @param listener  the listener of the event
     */
    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    /**
     * Method used to remove a listener of this class.
     *
     * @param eventName name of the event to listen for
     * @param listener  the listener of the event
     */
    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
