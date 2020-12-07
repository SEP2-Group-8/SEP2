package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public interface EventDAO {
    ArrayList<Event> getEventList();

    void createEvent(Event event);

    void removeEvent(Event event);

    void editEvent(Event newEvent);

    Event getEvent(String eventName, Date startDate);


}
