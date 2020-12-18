package dk.via.sep.server.networking.eventServerHandler;

import dk.via.sep.server.model.eventServerModel.EventServerModel;
import dk.via.sep.shared.networking.eventServerRemote.EventClientCallback;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The event server that handles all the event related actions, such as adding, editing, removing, etc.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 2.4
 */
public class EventServerHandler implements EventServer {

    private final EventServerModel eventServerModel;
    private PropertyChangeListener eventCreateListener;
    private PropertyChangeListener eventEditListener;
    private PropertyChangeListener eventRemoveListener;
    private PropertyChangeListener eventListListener;

    /**
     * Public constructor that initializes the event model and also makes this class remotely available for the client.
     *
     * @param eventServerModel the event model.
     */
    public EventServerHandler(EventServerModel eventServerModel) {
        this.eventServerModel = eventServerModel;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to register a client to a <b>general callback</b>. A general callback will notify every active user of an event.
     * Each <b>callback</b> <b><i>will need</i></b> it's own PropertyChangeListener created as a field variable, and it's own UserAction enum,
     * and follow the structure shown below.
     *
     * @param clientCallback the client's callback interface, so it can be accessed by the server.
     */
    @Override
    public void registerGeneralClientCallback(EventClientCallback clientCallback) {
        eventCreateListener = (event) -> {
            try {
                clientCallback.eventCreated((Event) event.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
                eventServerModel.removeListener(UserAction.EVENT_CREATE.toString(), eventCreateListener);
            }
        };
        eventEditListener = (event) -> {
            try {
                clientCallback.eventEdited((Event) event.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
                eventServerModel.removeListener(UserAction.EVENT_EDIT.toString(), eventEditListener);
            }
        };
        eventRemoveListener = (event) -> {
            try {
                clientCallback.eventRemoved((Event) event.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
                eventServerModel.removeListener(UserAction.EVENT_REMOVE.toString(), eventRemoveListener);
            }
        };

        eventServerModel.addListener(UserAction.EVENT_CREATE.toString(), eventCreateListener);
        eventServerModel.addListener(UserAction.EVENT_REMOVE.toString(), eventRemoveListener);
        eventServerModel.addListener(UserAction.EVENT_EDIT.toString(), eventEditListener);
    }

    /**
     * Method used to register a client to a <b>private callback</b>. A private callback will notify only the specified user, and no one else.
     * If you need to send a private set of data, you need to provide the client's UUID which is created at start-up and changes every time the
     * program is opened.
     *
     * @param clientCallback the client's callback interface
     * @param uuid           the client's UUID
     */
    @Override
    public void registerClientCallback(EventClientCallback clientCallback, UUID uuid) {
        eventListListener = (event) -> {
            try {
                clientCallback.retrieveEventList((ArrayList<Event>) event.getNewValue());
            } catch (RemoteException e) {
                e.printStackTrace();
                eventServerModel.removeListener(UserAction.EVENT_LIST.toString() + uuid, eventListListener);
            }
        };

        eventServerModel.addListener(UserAction.EVENT_LIST.toString() + uuid, eventListListener);
    }

    /**
     * Method used to add an event into the database.
     *
     * @param event the event to be added.
     */
    @Override
    public void createEvent(Event event) {
        eventServerModel.createEvent(event);
    }

    /**
     * Method used to remove an event from the database.
     * <b>Note:</b> the specified event needs to have attached to it an ID that has been assigned by the database, otherwise the DAO will
     * throw an exception.
     *
     * @param event the event to be removed.
     */
    @Override
    public void removeEvent(Event event) {
        eventServerModel.removeEvent(event);
    }

    /**
     * Method used to edit an event from the database.
     *
     * @param newEvent the event to be edited, along with the changes made to it.
     */
    @Override
    public void editEvent(Event newEvent) {
        eventServerModel.editEvent(newEvent);
    }

    /**
     * Method used to retrieve the event list from the database.
     *
     * @return the event list.
     * @deprecated <b>Use the async version instead.</b>
     */
    @Override
    public ArrayList<Event> getEventList() {
        return eventServerModel.getEventList();
    }

    /**
     * Method used to get the event list asynchronously. This method is called by the client, it will fetch the list from the database,
     * then call another method on the client using a listener.
     *
     * @param clientID the client's UUID, necessary to send back the list to the client that requested it.
     */
    @Override
    public void getEventListASync(UUID clientID) {
        eventServerModel.getEventListASync(clientID);
    }

    /**
     * Method used to join a user to an event.
     *
     * @param user  the user to be added to an event.
     * @param event the event to add the user to.
     * @param b     boolean if the user decides to ride the bus to the event or not.
     * @return boolean if the operation was successful or not.
     */
    @Override
    public boolean joinEvent(User user, Event event, boolean b) {
        return eventServerModel.joinEvent(user, event, b);
    }

    /**
     * Method used to remove a user from an event.
     *
     * @param user  the user to be removed from an event.
     * @param event the event to be removed from.
     * @return boolean if the operation was successful or not.
     */
    @Override
    public boolean leaveEvent(User user, Event event) {
        return eventServerModel.leaveEvent(user, event);
    }

    /**
     * Method used to retrieve the list of users from an event.
     *
     * @param event the event to get the list from.
     * @return boolean if the operation was successful or not.
     */
    @Override
    public ArrayList<User> getUserList(Event event) {
        return eventServerModel.getUserList(event);
    }
}
