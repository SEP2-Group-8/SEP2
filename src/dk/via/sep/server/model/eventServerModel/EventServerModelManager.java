package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.server.persistence.eventServer.EventDAO;
import dk.via.sep.shared.transfer.Event;

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
    public void createEvent(Event event) {
        eventDAO.createEvent(event);
    }

    @Override
    public void removeEvent(Event event) {
        eventDAO.removeEvent(event);
    }

    @Override
    public void editEvent(Event oldEvent, Event newEvent) {
        eventDAO.editEvent(oldEvent, newEvent);
    }
}
