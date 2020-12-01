package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.shared.transfer.Event;

import java.util.ArrayList;

public interface EventDAO {
    ArrayList<Event> getEventList();

    void createEvent(Event event);

    void removeEvent(Event event);

    void editEvent(Event oldEvent, Event newEvent);
}
