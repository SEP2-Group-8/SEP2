package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.sql.Date;
import java.util.ArrayList;

public interface EventDAO {
    ArrayList<Event> getEventList();

    boolean createEvent(Event event);

    boolean removeEvent(Event event);

    boolean editEvent(Event newEvent);

    Event getEvent(String eventName, Date startDate);

    boolean joinEvent(User user, Event event, boolean joinBus);

    boolean leaveEvent(User user, Event event);

    ArrayList<User> getUserList(Event event);
}
