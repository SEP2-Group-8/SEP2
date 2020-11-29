package dk.via.sep.client.view.register;

import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RegisterViewModel implements Subject {

    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty email;
    private final StringProperty confPassword;
    private final StringProperty error;
    private final UserModel model;
    private final PropertyChangeSupport support;

    public RegisterViewModel(UserModel model) {
        this.model = model;
        model.addListener(UserAction.REGISTER_FAILED.toString(), this::onRegisterFailed);
        model.addListener(UserAction.REGISTER_SUCCESS.toString(), this::onRegisterSuccess);
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        email = new SimpleStringProperty();
        confPassword = new SimpleStringProperty();
        error = new SimpleStringProperty();
        support = new PropertyChangeSupport(this);
    }

    private void onRegisterSuccess(PropertyChangeEvent propertyChangeEvent) {
        System.out.println("Action in modelManager: " + propertyChangeEvent.getPropertyName());
        support.firePropertyChange(propertyChangeEvent);
        error.set("OK");
    }

    private void onRegisterFailed(PropertyChangeEvent evt) {
        Platform.runLater( () ->{
            error.set("This username has already been taken.");
        });
    }

    public void registerUser() {
        String un = username.get();
        if (un == null || un.equals("")) {
            error.set("Username cannot be empty.");
            return;
        }
        String pw = password.get();
        if (pw == null) {
            error.set("Password cannot be empty.");
            return;
        }
        String e_mail = email.get();
        if (e_mail == null || e_mail.equals("")) {
            error.set("Email cannot be empty.");
            return;
        }
        String confPw = confPassword.get();
        if (confPw == null || confPw.equals("")) {
            error.set("Confirmed password cannot be empty.");
            return;
        }
        if (!confPw.equals(pw)) {
            error.set("Confirmed password must be the same as the password.");
            return;
        }
        System.out.println(un + "\n" + pw + "\n" + e_mail);
        model.register(un, pw, e_mail);

    }


    public StringProperty usernameProperty() {
        return username;
    }


    public StringProperty passwordProperty() {
        return password;
    }


    public StringProperty emailProperty() {
        return email;
    }


    public StringProperty confPasswordProperty() {
        return confPassword;
    }

    public StringProperty errorProperty() {
        return error;
    }

    private void clear() {
        username.set("");
        password.set("");
        confPassword.set("");
        email.set("");

    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
