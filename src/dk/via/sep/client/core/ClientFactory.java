package dk.via.sep.client.core;

import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.client.networking.eventClient.EventClientImpl;
import dk.via.sep.client.networking.userClient.UserClient;
import dk.via.sep.client.networking.userClient.UserClientImpl;

public class ClientFactory {

    private UserClient userClient;
    private EventClient eventClient;

    public UserClient getUserClient() {
        if (userClient == null) {
            userClient = new UserClientImpl();
        }
        return userClient;
    }

    public EventClient getEventClient() {
        if (eventClient == null) {
            eventClient = new EventClientImpl();
        }
        return eventClient;
    }


}
