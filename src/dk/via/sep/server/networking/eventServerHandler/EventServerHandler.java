package dk.via.sep.server.networking.eventServerHandler;

import dk.via.sep.server.model.eventServerModel.EventServerModel;
import dk.via.sep.shared.networking.eventServerRemote.EventClientCallback;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.UserAction;
import dk.via.sep.shared.transfer.User;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

public class EventServerHandler implements EventServer {

    private final EventServerModel eventServerModel;
    private PropertyChangeListener eventCreateListener;
    private PropertyChangeListener eventEditListener;
    private PropertyChangeListener eventRemoveListener;
    private PropertyChangeListener eventListListener;

    public EventServerHandler(EventServerModel eventServerModel) {
        this.eventServerModel = eventServerModel;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void createEvent(Event event) {
        eventServerModel.createEvent(event);
    }

    @Override
    public void removeEvent(Event event) {
        eventServerModel.removeEvent(event);
    }

    @Override
    public void editEvent(Event newEvent) {
        eventServerModel.editEvent(newEvent);
    }

    @Override
    public ArrayList<Event> getEventList() {
        return eventServerModel.getEventList();
    }

    @Override
    public void getEventListASync(UUID clientID) {
        eventServerModel.getEventListASync(clientID);
    }

    @Override
    public boolean joinEvent(User user, Event event, boolean b) {
        return eventServerModel.joinEvent(user,event,b);
    }

    @Override
    public boolean leaveEvent(User user, Event event) {
        return eventServerModel.leaveEvent(user,event);
    }

    @Override
    public ArrayList<User> getUserList(Event event) {
        return eventServerModel.getUserList(event);
    }
}
