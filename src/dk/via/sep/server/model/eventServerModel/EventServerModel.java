package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

public interface EventServerModel extends Subject {
    ArrayList<Event> getEventList();

    boolean createEvent(Event event);

    boolean removeEvent(Event event);

    boolean editEvent(Event newEvent);

    boolean joinEvent(User user, Event event, boolean b);

    boolean leaveEvent(User user, Event event);

    ArrayList<User> getUserList(Event event);
}
