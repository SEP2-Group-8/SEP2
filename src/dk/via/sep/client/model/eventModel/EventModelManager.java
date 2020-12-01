package dk.via.sep.client.model.eventModel;

import dk.via.sep.client.core.ClientFactory;
import dk.via.sep.client.networking.eventClient.EventClient;

public class EventModelManager implements EventModel {

    private final EventClient eventClient;

    public EventModelManager() {
        this.eventClient = ClientFactory.getInstance().getEventClient();
    }
}
