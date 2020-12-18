package dk.via.sep.client.core;

import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.client.networking.eventClient.EventClientImpl;
import dk.via.sep.client.networking.userClient.UserClient;
import dk.via.sep.client.networking.userClient.UserClientImpl;

/**
 * A class containing the methods necessary to initialize all client connections required
 *
 * @author Bogdan
 * @version 1.0
 */
public class ClientFactory {

    private static ClientFactory clientFactory;

    private UserClient userClient;
    private EventClient eventClient;

    /**
     * No argument private constructor, as this class is made with a singleton pattern
     */
    private ClientFactory() {

    }

    /**
     * A static getInstance method required to have access to the private fields in the method
     *
     * @return The client factory that allows a class to access the necessary client interfaces
     */
    public static ClientFactory getInstance() {
        if (clientFactory == null)
            clientFactory = new ClientFactory();
        return clientFactory;
    }

    /**
     * A getter for the user client interface
     *
     * @return The user client interface
     */
    public UserClient getUserClient() {
        if (userClient == null) {
            userClient = new UserClientImpl();
        }
        return userClient;
    }

    /**
     * A getter for the event client interface
     *
     * @return The event client interface
     */
    public EventClient getEventClient() {
        if (eventClient == null) {
            eventClient = new EventClientImpl();
        }
        return eventClient;
    }


}
