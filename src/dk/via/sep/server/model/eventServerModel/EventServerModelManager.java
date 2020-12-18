package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.server.persistence.eventServer.EventDAO;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.UUID;

public class EventServerModelManager implements EventServerModel {

    private final EventDAO eventDAO;
    private final PropertyChangeSupport support;

    public EventServerModelManager(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
        support = new PropertyChangeSupport(this);
    }

    @Override
    public ArrayList<Event> getEventList() {
        return eventDAO.getEventList();
    }

    @Override
    public void createEvent(Event event) {
        boolean success = eventDAO.createEvent(event);
        if (success) {
            support.firePropertyChange(UserAction.EVENT_CREATE.toString(), null, event);
        }
    }

    @Override
    public void removeEvent(Event event) {
        boolean success = eventDAO.removeEvent(event);
        if (success) {
            support.firePropertyChange(UserAction.EVENT_REMOVE.toString(), null, event);
        }
    }

    @Override
    public void editEvent(Event event) {
        boolean success = eventDAO.editEvent(event);
        if (success) {
            support.firePropertyChange(UserAction.EVENT_EDIT.toString(), null, event);
        }
    }

    @Override
    public void getEventListASync(UUID clientID) {
        ArrayList<Event> events = eventDAO.getEventList();
        System.out.println("called method");
        support.firePropertyChange(UserAction.EVENT_LIST.toString() + clientID, null, events);
    }

    @Override
    public boolean joinEvent(User user, Event event, boolean b) {
        System.out.println("I got here -> server model");
        return eventDAO.joinEvent(user, event, b);
    }

    @Override
    public boolean leaveEvent(User user, Event event) {
        return eventDAO.leaveEvent(user, event);
    }

    @Override
    public ArrayList<User> getUserList(Event event) {
        return eventDAO.getUserList(event);
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
