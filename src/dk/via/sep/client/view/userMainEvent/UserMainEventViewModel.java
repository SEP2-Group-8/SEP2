package dk.via.sep.client.view.userMainEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UserMainEventViewModel implements Subject {

    private final EventModel eventModel;
    private final PropertyChangeSupport support;

    public UserMainEventViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
        support = new PropertyChangeSupport(this);
        eventModel.addListener(UserAction.EVENT_CREATE_SUCCESS.toString(), this::onReceiveRequest);
        eventModel.addListener(UserAction.EVENT_CREATE_FAILED.toString(), this::onReceiveRequest);
        eventModel.addListener(UserAction.EVENT_REMOVE.toString(), this::onReceiveRequest);
        eventModel.addListener(UserAction.EVENT_EDIT.toString(), this::onReceiveRequest);
    }

    private void onReceiveRequest(PropertyChangeEvent event) {
        support.firePropertyChange(event);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    public void getEventList() {
        ArrayList<Event> events = eventModel.getEventList();
        support.firePropertyChange(UserAction.EVENT_CREATE.toString(), null, events);
    }
}
