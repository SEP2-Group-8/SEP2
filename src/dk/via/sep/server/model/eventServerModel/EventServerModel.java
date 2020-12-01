package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.shared.transfer.Event;

import java.util.ArrayList;

public interface EventServerModel {
    ArrayList<Event> getEventList();

    void createEvent(Event event);

    void removeEvent(Event event);

    void editEvent(Event oldEvent, Event newEvent);
}
