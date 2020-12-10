package dk.via.sep.client.model.eventModel;

import dk.via.sep.client.core.ClientFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
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
    public void joinEvent(boolean b) {
        User user = LoggedUser.getInstance().getUser();
        Event event = LoggedUser.getInstance().getSelectedEvent();
        System.out.println("I got here -> model");
        boolean success = eventClient.joinEvent(user,event, b);
        if(success) support.firePropertyChange(UserAction.EVENT_JOIN_SUCCESS.toString(),user,event);
        else support.firePropertyChange(UserAction.EVENT_JOIN_FAILED.toString(),null,null);
    }

    @Override
    public void leaveEvent() {
        User user = LoggedUser.getInstance().getUser();
        Event event = LoggedUser.getInstance().getSelectedEvent();
        boolean success = eventClient.leaveEvent(user,event);
        if(success) support.firePropertyChange(UserAction.EVENT_LEAVE_SUCCESS.toString(),user,event);
        else support.firePropertyChange(UserAction.EVENT_LEAVE_FAILED.toString(),null,null);

    }

    @Override
    public ArrayList<User> getUserList(Event event) {
        return eventClient.getUserList(event);
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
