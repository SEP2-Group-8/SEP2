package dk.via.sep.client.view.userList;

import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UserListViewModel implements Subject {

    private UserModel modelManager;
    private PropertyChangeSupport support;
    private ObservableList<User> userList;

    public UserListViewModel(UserModel modelManager) {
        this.modelManager = modelManager;
        support = new PropertyChangeSupport(this);
        modelManager.addListener(UserAction.USER_LIST.toString(),this::listModified);
        userList = FXCollections.observableArrayList();
    }

    private void listModified(PropertyChangeEvent propertyChangeEvent) {
        userList.setAll(modelManager.getUserList());
        support.firePropertyChange(UserAction.USER_LIST.toString(),null,userList);
    }

    public ObservableList<User> getUserList() {
        userList.setAll(modelManager.getUserList());
        return userList;
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
