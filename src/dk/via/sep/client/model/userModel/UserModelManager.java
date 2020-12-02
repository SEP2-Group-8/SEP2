package dk.via.sep.client.model.userModel;

import dk.via.sep.client.core.ClientFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.networking.userClient.UserClient;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UserModelManager implements UserModel {

    private final UserClient client;
    private final PropertyChangeSupport support;
    private final StringProperty username;

    public UserModelManager() {
        this.client = ClientFactory.getInstance().getUserClient();
        support = new PropertyChangeSupport(this);
        username = new SimpleStringProperty();

        this.client.addListener(UserAction.REGISTER_SUCCESS.toString(), this::onReceiveRequest);
        this.client.addListener(UserAction.REGISTER_FAILED.toString(), this::onReceiveRequest);

        this.client.startClient();
    }

    private void onReceiveRequest(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent);
    }

    @Override
    public void login(String username, String password) {
        User user = client.loginUser(username, password);
        if (user != null) {
            LoggedUser.getInstance().setUser(user);
            support.firePropertyChange(UserAction.LOGIN_SUCCESS.toString(), null, null);
        } else support.firePropertyChange(UserAction.LOGIN_FAILED.toString(), null, null);
    }

    @Override
    public void register(String un, String pw, String e_mail) {
        User user = new User(e_mail, pw, un);
        client.registerAccount(user);
    }

    @Override
    public void logOut() {
        client.logOut();
    }

    @Override
    public ArrayList<User> getUserList() {
        return client.getUserList();
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
