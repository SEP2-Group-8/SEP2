package dk.via.sep.client.model;

import dk.via.sep.client.networking.Client;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements ModelInterface {

    private final Client client;
    private final PropertyChangeSupport support;
    private StringProperty username;

    public ModelManager(Client client) {
        this.client = client;
        support = new PropertyChangeSupport(this);
        username = new SimpleStringProperty();

        this.client.addListener(UserAction.LOGIN_SUCCESS.toString(), this::onReceiveRequest);
        this.client.addListener(UserAction.LOGIN_FAILED.toString(), this::onReceiveRequest);
        this.client.addListener(UserAction.REGISTER_SUCCESS.toString(), this::onReceiveRequest);
        this.client.addListener(UserAction.REGISTER_FAILED.toString(), this::onReceiveRequest);

        this.client.startClient();
    }

    private void onReceiveRequest(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent);
        UserAction action = (UserAction) propertyChangeEvent.getNewValue();
        System.out.println("Action in modelManager: "+action.toString());
    }

    @Override
    public void login(String value, String value1) {

    }

    @Override
    public void register(String un, String pw, String e_mail) {
        User user = new User(e_mail,pw,un);
        client.register(user);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
