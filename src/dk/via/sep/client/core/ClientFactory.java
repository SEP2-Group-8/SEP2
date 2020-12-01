package dk.via.sep.client.core;

import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.client.networking.eventClient.EventClientImpl;
import dk.via.sep.client.networking.userClient.UserClient;
import dk.via.sep.client.networking.userClient.UserClientImpl;

public class ClientFactory {

    private static ClientFactory clientFactory;

    private UserClient userClient;
    private EventClient eventClient;

    private ClientFactory() {

    }

    public static ClientFactory getInstance() {
        if (clientFactory == null)
            clientFactory = new ClientFactory();
        return clientFactory;
    }

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
