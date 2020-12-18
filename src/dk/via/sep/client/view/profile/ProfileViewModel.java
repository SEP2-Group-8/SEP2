package dk.via.sep.client.view.profile;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ProfileViewModel implements Subject {

    private final UserModel userModel;
    private final StringProperty nameLabel;
    private final StringProperty passwordLabel;
    private final StringProperty emailLabel;
    private final StringProperty birthdayLabel;
    private final PropertyChangeSupport support;

    public ProfileViewModel() {
        userModel = ModelFactory.getInstance().getUserModelManager();
        nameLabel = new SimpleStringProperty();
        passwordLabel = new SimpleStringProperty();
        emailLabel = new SimpleStringProperty();
        birthdayLabel = new SimpleStringProperty();
        support = new PropertyChangeSupport(this);

        userModel.addListener(UserAction.PROFILE_EDIT.toString(), this::updateProfile);
    }

    private void updateProfile(PropertyChangeEvent event) {
        User user = (User) event.getNewValue();
        LoggedUser.getInstance().setUser(user);
        System.out.println("got here");
        getNameLabel();
        getPasswordLabel();
        getEmailLabel();
        getBirthdayLabel();
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

    public void deleteAccount() {
        userModel.logOut();
        userModel.deleteAccount();
    }
}
