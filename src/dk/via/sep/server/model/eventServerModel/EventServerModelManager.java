package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.server.persistence.eventServer.EventDAO;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class EventServerModelManager implements EventServerModel {

    private final EventDAO eventDAO;


    public EventServerModelManager(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public ArrayList<Event> getEventList() {
        return eventDAO.getEventList();
    }

    @Override
    public boolean createEvent(Event event) {
        return eventDAO.createEvent(event);
    }

    @Override
    public boolean removeEvent(Event event) {
        return eventDAO.removeEvent(event);
    }

    @Override
    public boolean editEvent(Event newEvent) {
        return eventDAO.editEvent(newEvent);
    }

    @Override
    public boolean joinEvent(User user, Event event, boolean b) {
        System.out.println("I got here -> server model");
        return eventDAO.joinEvent(user,event,b);
    }

    @Override
    public boolean leaveEvent(User user, Event event) { return eventDAO.leaveEvent(user,event); }

    @Override
    public ArrayList<User> getUserList(Event event) {
        return eventDAO.getUserList(event);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
