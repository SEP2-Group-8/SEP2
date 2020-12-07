package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

public interface EventServerModel extends Subject {
    ArrayList<Event> getEventList();

    void createEvent(Event event);

    void removeEvent(Event event);

    void editEvent(Event newEvent);
}
