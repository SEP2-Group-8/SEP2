package dk.via.sep.client.core;

import dk.via.sep.client.networking.Client;
import dk.via.sep.client.networking.ClientImpl;

public class ClientFactory {

  private Client client;


  public Client getClientNetwork() {
    if (client == null) {
      client = new ClientImpl();
    }
    return client;
  }
}
