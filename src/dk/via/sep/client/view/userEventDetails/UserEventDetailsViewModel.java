package dk.via.sep.client.view.userEventDetails;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UserEventDetailsViewModel implements Subject {

    private EventModel eventModel;
    private PropertyChangeSupport support;

    private StringProperty eventName;
    private StringProperty eventDate;
    private StringProperty eventTime;
    private StringProperty eventDescription;
    private StringProperty busID;
    private StringProperty busDepartLocation;
    private StringProperty busArriveLocation;
    private StringProperty busDepartLocationStartTime;
    private StringProperty busDepartLocationEndTime;
    private StringProperty busArriveLocationStartTime;
    private StringProperty busArriveLocationEndTime;
    private StringProperty busSeats;
    private BooleanProperty busCheck;


    public UserEventDetailsViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
        support = new PropertyChangeSupport(this);
        eventName = new SimpleStringProperty();
        eventDate = new SimpleStringProperty();
        eventDescription = new SimpleStringProperty();
        busID = new SimpleStringProperty();
        busDepartLocation = new SimpleStringProperty();
        busArriveLocation = new SimpleStringProperty();
        busDepartLocationStartTime = new SimpleStringProperty();
        busDepartLocationEndTime = new SimpleStringProperty();
        busArriveLocationStartTime = new SimpleStringProperty();
        busArriveLocationEndTime = new SimpleStringProperty();
        busSeats = new SimpleStringProperty();
        eventTime = new SimpleStringProperty();
        busCheck = new SimpleBooleanProperty();
        eventModel.addListener(UserAction.EVENT_JOIN_SUCCESS.toString(), this::onReceiveRequest);
        eventModel.addListener(UserAction.EVENT_JOIN_FAILED.toString(), this::onReceiveRequest);
        eventModel.addListener(UserAction.EVENT_LEAVE_SUCCESS.toString(), this::onReceiveRequest);
        eventModel.addListener(UserAction.EVENT_LEAVE_FAILED.toString(), this::onReceiveRequest);
    }

    private void onReceiveRequest(PropertyChangeEvent event) {
        System.out.println(event);
        support.firePropertyChange(event);
    }

    public void initView() {
        Event event = LoggedUser.getInstance().getSelectedEvent();
        eventName.setValue(event.getEventName());
        eventDate.setValue(event.getDate().toString());
        eventDescription.setValue(event.getDescription());
        eventTime.setValue(event.getStartTime().toString());
        Bus bus = event.getBus();
        busID.setValue(String.valueOf(bus.getBusId()));
        busArriveLocation.setValue(bus.getArriveLocation());
        busDepartLocation.setValue(bus.getDepartLocation());
        busArriveLocationStartTime.setValue(bus.getArriveTimeStart().toString());
        busArriveLocationEndTime.setValue(bus.getArriveTimeEnd().toString());
        busDepartLocationStartTime.setValue(bus.getDepartTimeStart().toString());
        busDepartLocationEndTime.setValue(bus.getDepartTimeEnd().toString());
        busSeats.setValue(String.valueOf(bus.getNoSeats()));
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public StringProperty eventDateProperty() {
        return eventDate;
    }

    public StringProperty eventDescriptionProperty() {
        return eventDescription;
    }

    public StringProperty busIDProperty() {
        return busID;
    }

    public StringProperty busDepartLocationProperty() {
        return busDepartLocation;
    }

    public StringProperty busArriveLocationProperty() {
        return busArriveLocation;
    }

    public StringProperty busDepartLocationStartTimeProperty() {
        return busDepartLocationStartTime;
    }

    public StringProperty busDepartLocationEndTimeProperty() {
        return busDepartLocationEndTime;
    }

    public StringProperty busArriveLocationStartTimeProperty() {
        return busArriveLocationStartTime;
    }

    public StringProperty busArriveLocationEndTimeProperty() {
        return busArriveLocationEndTime;
    }

    public StringProperty busSeatsProperty() {
        return busSeats;
    }

    public StringProperty eventTimeProperty() {
        return eventTime;
    }

    public BooleanProperty busCheckProperty() {
        return busCheck;
    }

    public void removeEvent(Event selectedEvent) {
        eventModel.removeEvent(selectedEvent);
    }

    public void leaveEvent() {
        eventModel.leaveEvent();
    }

    public void joinEvent() {
        System.out.println(busCheck.get());
        eventModel.joinEvent(busCheck.get());
    }

    public void getUserList() {
        Event event = LoggedUser.getInstance().getSelectedEvent();
        ArrayList<User> userList = eventModel.getUserList(event);
        if (userList != null)
            for (User user : userList)
                support.firePropertyChange(UserAction.EVENT_JOIN.toString(), null, user);
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


