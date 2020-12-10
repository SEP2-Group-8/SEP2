package dk.via.sep.client.networking.eventClient;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

public interface EventClient extends Subject {
    void startClient();

    void createEvent(Event event);

    void removeEvent(Event event);

    void editEvent(Event oldEvent);

    ArrayList<Event> getEventList();

    void getEventListAsync();
}
