package dk.via.sep.client.view.profile;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.shared.utils.Clock;
import dk.via.sep.shared.utils.Subject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ProfileViewModel implements Subject {

    private UserModel userModel;
    private StringProperty nameLabel;
    private StringProperty passwordLabel;
    private StringProperty emailLabel;
    private StringProperty birthdayLabel;
    private PropertyChangeSupport support;

    public ProfileViewModel() {
        userModel = ModelFactory.getInstance().getUserModelManager();
        nameLabel = new SimpleStringProperty();
        passwordLabel = new SimpleStringProperty();
        emailLabel = new SimpleStringProperty();
        birthdayLabel = new SimpleStringProperty();
        support = new PropertyChangeSupport(this);

    }

    public StringProperty getNameLabel() {
        nameLabel.setValue(LoggedUser.getInstance().getUser().getUsername());
        return nameLabel;
    }

    public StringProperty getPasswordLabel() {
        passwordLabel.setValue(LoggedUser.getInstance().getUser().getPassword());
        return passwordLabel;
    }

    public StringProperty getEmailLabel() {
        emailLabel.setValue(LoggedUser.getInstance().getUser().getEmail());
        return emailLabel;
    }

    public StringProperty getBirthdayLabel() {
        birthdayLabel.setValue("69/420/1337");
        return birthdayLabel;
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
