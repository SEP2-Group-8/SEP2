package dk.via.sep.client.view.login;

import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel implements Subject {
    private final UserModel userModel;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty error;
    private final PropertyChangeSupport support;

    public LoginViewModel(UserModel userModel) {
        this.userModel = userModel;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
        error.setValue("");
        support = new PropertyChangeSupport(this);

        this.userModel.addListener(UserAction.LOGIN_SUCCESS.toString(), this::onLoginOK);
        this.userModel.addListener(UserAction.LOGIN_FAILED.toString(), this::onLoginFail);
        this.userModel.addListener(UserAction.LOGIN_SUCCESS_ADMIN.toString(),this::onLoginOK);
    }

    public void login() {
        userModel.login(username.getValue(), password.getValue());
    }

    private void onLoginOK(PropertyChangeEvent event) {
        support.firePropertyChange(event);
    }

    private void onLoginFail(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            error.setValue("Username or password don't match.");
        });
        System.out.println("failed to login");
    }

    public StringProperty getUsernameProperty() {
        return username;
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    public StringProperty getErrorProperty() {
        return error;
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
