package dk.via.sep.client.core;

import dk.via.sep.client.networking.userClient.UserClient;
import dk.via.sep.client.networking.userClient.UserClientImpl;

public class ClientFactory {

    private UserClient client;

    public UserClient getUserClient() {
        if (client == null) {
            client = new UserClientImpl();
        }
        return client;
    }
}
