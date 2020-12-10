package dk.via.sep.client.networking.eventClient;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

public interface EventClient extends Subject {
    void startClient();

    boolean createEvent(Event event);

    boolean removeEvent(Event event);

    boolean editEvent(Event oldEvent);

    ArrayList<Event> getEventList();

    boolean joinEvent(User user, Event event, boolean b);

    boolean leaveEvent(User user, Event event);

    ArrayList<User> getUserList(Event event);
}
