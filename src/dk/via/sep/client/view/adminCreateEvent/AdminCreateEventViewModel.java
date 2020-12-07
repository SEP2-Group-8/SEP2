package dk.via.sep.client.view.adminCreateEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.Subject;
import dk.via.sep.shared.utils.UserAction;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class AdminCreateEventViewModel implements Subject {
    private EventModel eventModel;
    private StringProperty eventName;
    private ObjectProperty<LocalDate> eventDate;
    private ObjectProperty<LocalTime> eventTime;
    private StringProperty eventDescription;
    private StringProperty busDepartLocation;
    private ObjectProperty<LocalTime> busDepartLocationStartTime;
    private ObjectProperty<LocalTime> busDepartLocationEndTime;
    private ObjectProperty<LocalTime> busArriveLocationStartTime;
    private ObjectProperty<LocalTime> busArriveLocationEndTime;
    private StringProperty busSeats;
    private StringProperty eventLocation;
    private PropertyChangeSupport support;


    public AdminCreateEventViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
        eventName = new SimpleStringProperty();
        eventDate = new SimpleObjectProperty<>();
        eventTime = new SimpleObjectProperty<>();
        eventDescription = new SimpleStringProperty();
        eventLocation = new SimpleStringProperty();
        busDepartLocation = new SimpleStringProperty();
        busDepartLocationStartTime = new SimpleObjectProperty<>();
        busDepartLocationEndTime = new SimpleObjectProperty<>();
        busArriveLocationStartTime = new SimpleObjectProperty<>();
        busArriveLocationEndTime = new SimpleObjectProperty<>();
        busSeats = new SimpleStringProperty();
        support= new PropertyChangeSupport(this);
        eventModel.addListener(UserAction.EVENT_CREATE_SUCCESS.toString(),this::onReceiveAction);
    }

    private void onReceiveAction(PropertyChangeEvent evt) {
        support.firePropertyChange(evt.getNewValue().toString(),null,null);
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public ObjectProperty eventDateProperty() {
        return eventDate;
    }

    public StringProperty eventDescriptionProperty() {
        return eventDescription;
    }

    public StringProperty busDepartLocationProperty() {
        return busDepartLocation;
    }

    public ObjectProperty busDepartLocationStartTimeProperty() {
        return busDepartLocationStartTime;
    }

    public ObjectProperty busDepartLocationEndTimeProperty() {
        return busDepartLocationEndTime;
    }

    public ObjectProperty busArriveLocationStartTimeProperty() {
        return busArriveLocationStartTime;
    }

    public ObjectProperty busArriveLocationEndTimeProperty() {
        return busArriveLocationEndTime;
    }

    public StringProperty busSeatsProperty() {
        return busSeats;
    }

    public StringProperty eventLocationProperty() {
        return eventLocation;
    }

    public void createEvent() {

        if(eventName.getValue().equals("") || eventName.getValue() == null) { return; }
        if(eventDate.get() == null){ return;}
        if(eventTime.get() == null){ return;}
        if(eventDescription.getValue().equals("") || eventDescription.getValue() == null){ return; }
        if(eventLocation.getValue().equals("") || eventLocation.getValue() == null){ return; }
        if(busDepartLocation.getValue().equals("") || busDepartLocation.getValue() == null) { return; }
        if(busDepartLocationStartTime.get() == null) { return; }
        if(busDepartLocationEndTime.get() == null) { return; }
        if(busArriveLocationStartTime.get() == null)  { return; }
        if(busArriveLocationEndTime.get() == null)  { return; }
        if(busSeats.getValue().equals("") || busSeats.getValue() == null)  { return; }

        int noOfSeats = Integer.parseInt(busSeats.getValue());
        Date date = new Date(eventDate.getValue().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
        Time startTime = Time.valueOf(eventTime.get());
        Time departStartTime = Time.valueOf(busDepartLocationStartTime.get());
        Time departEndTime = Time.valueOf(busDepartLocationEndTime.get());
        Time arriveStartTime = Time.valueOf(busArriveLocationStartTime.get());
        Time arriveEndTime = Time.valueOf(busArriveLocationEndTime.get());

        Bus bus = new Bus(noOfSeats,departStartTime,departEndTime,arriveStartTime,arriveEndTime,
                busDepartLocation.getValue(),eventLocation.getValue());

        Event event = new Event(date,startTime,eventLocation.getValue(),eventDescription.getValue(),eventName.getValue(),bus);

        eventModel.createEvent(event);

    }

    public ObjectProperty eventTimeProperty() { return eventTime; }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    public void clear() {
        eventName.setValue("");
        eventDate.set(null);
        eventTime.set(null);
        eventDescription.setValue("");
        eventLocation.setValue("");
        busDepartLocation.setValue("");
        busDepartLocationStartTime.set(null);
        busDepartLocationEndTime.set(null);
        busArriveLocationStartTime.set(null);
        busArriveLocationEndTime.set(null);
        busSeats.set("");
    }
}
