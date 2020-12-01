package dk.via.sep.client.networking.eventClient;

import dk.via.sep.shared.transfer.Event;

import java.util.ArrayList;

public interface EventClient {
    void startClient();

    void createEvent(Event event);

    void removeEvent(Event event);

    void editEvent(Event oldEvent, Event newEvent);

    ArrayList<Event> getEventList();
}
