package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.server.persistence.eventServer.EventDAO;
import dk.via.sep.shared.transfer.Event;

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
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
