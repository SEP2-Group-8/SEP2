package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

public interface EventServerModel extends Subject {
    ArrayList<Event> getEventList();

    boolean createEvent(Event event);

    boolean removeEvent(Event event);

    boolean editEvent(Event newEvent);
}
