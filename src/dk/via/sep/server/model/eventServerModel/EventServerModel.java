package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;
import java.util.UUID;

public interface EventServerModel extends Subject {
    ArrayList<Event> getEventList();

    void createEvent(Event event);

    void removeEvent(Event event);

    boolean joinEvent(User user, Event event, boolean b);

    boolean leaveEvent(User user, Event event);

    ArrayList<User> getUserList(Event event);

    void editEvent(Event newEvent);

    void getEventListASync(UUID clientID);
}
