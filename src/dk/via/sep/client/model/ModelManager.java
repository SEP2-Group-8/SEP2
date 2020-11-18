package dk.via.sep.client.model;

import dk.via.sep.client.networking.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class ModelManager implements ModelInterface {

  private final Client client;
  private final PropertyChangeSupport support;
  private StringProperty username;

  public ModelManager(Client client) {
    this.client = client;
    support = new PropertyChangeSupport(this);
    username = new SimpleStringProperty();

//    add listeners
//    this.client.addListener(UserAction.LOGIN_SUCCESS.toString(), this::onReceiveRequest);
//    this.client.addListener(UserAction.LOGIN_FAILED.toString(), this::onReceiveRequest);

//    try {
//      this.client.startClient();
//    } catch (RemoteException e) {
//      e.printStackTrace();
//    }
  }

//  private void onReceiveRequest(PropertyChangeEvent propertyChangeEvent) {
//    support.firePropertyChange(propertyChangeEvent);
//  }


  @Override public void createUser(String username, String password) {
    // client.login(username, password);
  }
}
