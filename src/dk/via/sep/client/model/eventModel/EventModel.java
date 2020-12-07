package dk.via.sep.client.model.eventModel;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.Subject;

public interface EventModel extends Subject {
    void removeEvent(Event selectedEvent);

    void createEvent(Event event);

    void editEvent(Event selectedEvent);
}
