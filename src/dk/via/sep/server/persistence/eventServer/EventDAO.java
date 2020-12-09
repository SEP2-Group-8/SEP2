package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;

import java.sql.Date;
import java.util.ArrayList;

public interface EventDAO {
    ArrayList<Event> getEventList();

    boolean createEvent(Event event);

    boolean removeEvent(Event event);

    boolean editEvent(Event newEvent);

    Event getEvent(String eventName, Date startDate);


}
