package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;

import java.sql.Date;
import java.util.ArrayList;

public interface EventDAO {
    ArrayList<Event> getEventList();

    void createEvent(Event event);

    void removeEvent(Event event);

    void editEvent(Event oldEvent, Event newEvent);

    void createBus(Bus bus);

    Event getEvent(String eventName, Date startDate);

    Bus getBus(Date departDate, Date arriveDate, String departLocation, String arriveLocation);


}
