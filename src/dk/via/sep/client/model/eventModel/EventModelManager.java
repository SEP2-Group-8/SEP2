package dk.via.sep.client.model.eventModel;

import dk.via.sep.client.networking.eventClient.EventClient;

public class EventModelManager implements EventModel {

    private EventClient eventClient;

    public EventModelManager(EventClient eventClient) {
        this.eventClient = eventClient;
    }
}
