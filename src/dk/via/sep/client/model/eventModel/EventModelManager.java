package dk.via.sep.client.model.eventModel;

import dk.via.sep.client.core.ClientFactory;
import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.shared.transfer.Event;

public class EventModelManager implements EventModel {

    private final EventClient eventClient;

    public EventModelManager() {
        this.eventClient = ClientFactory.getInstance().getEventClient();
    }

    @Override
    public void removeEvent(Event selectedEvent) {
        eventClient.removeEvent(selectedEvent);
    }
}
