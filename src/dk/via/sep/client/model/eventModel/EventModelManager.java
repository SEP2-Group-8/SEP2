package dk.via.sep.client.model.eventModel;

import dk.via.sep.client.core.ClientFactory;
import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.UserAction;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class EventModelManager implements EventModel {

    private final EventClient eventClient;
    private final PropertyChangeSupport support;

    public EventModelManager() {
        this.eventClient = ClientFactory.getInstance().getEventClient();
        support = new PropertyChangeSupport(this);
        this.eventClient.startClient();
        eventClient.addListener(UserAction.EVENT_CREATE_SUCCESS.toString(), this::onReceiveRequest);
        eventClient.addListener(UserAction.EVENT_CREATE_FAILED.toString(), this::onReceiveRequest);
        eventClient.addListener(UserAction.EVENT_CREATE.toString(), this::onReceiveRequest);
        eventClient.addListener(UserAction.EVENT_REMOVE.toString(), this::onReceiveRequest);
        eventClient.addListener(UserAction.EVENT_EDIT.toString(), this::onReceiveRequest);
        eventClient.addListener(UserAction.EVENT_LIST.toString(), this::onReceiveRequest);
    }

    private void onReceiveRequest(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }

    @Override
    public void removeEvent(Event selectedEvent) {
        eventClient.removeEvent(selectedEvent);
    }

    @Override
    public void createEvent(Event event) {
        eventClient.createEvent(event);
    }

    @Override
    public void editEvent(Event selectedEvent) {
        eventClient.editEvent(selectedEvent);
    }

    @Override
    public ArrayList<Event> getEventList() {
        return eventClient.getEventList();
    }

    @Override
    public void getEventListAsync() {
        eventClient.getEventListAsync();
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
