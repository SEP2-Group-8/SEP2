package dk.via.sep.client.view.adminEditEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class AdminEditEventViewModel {

    private EventModel eventModel;
    private StringProperty eventName;
    private ObjectProperty<LocalDate> eventDate;
    private StringProperty eventDescription;
    private StringProperty eventLocation;
    private ObjectProperty<LocalTime> eventTime;
    private StringProperty busDepartLocation;
    private ObjectProperty<LocalTime> busDepartLocationStartTime;
    private ObjectProperty<LocalTime> busDepartLocationEndTime;
    private ObjectProperty<LocalTime> busArriveLocationStartTime;
    private ObjectProperty<LocalTime> busArriveLocationEndTime;
    private StringProperty busSeats;

    public AdminEditEventViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
        eventName = new SimpleStringProperty();
        eventDate = new SimpleObjectProperty();
        eventDescription = new SimpleStringProperty();
        eventLocation = new SimpleStringProperty();
        busDepartLocation = new SimpleStringProperty();
        busDepartLocationStartTime = new SimpleObjectProperty();
        busDepartLocationEndTime = new SimpleObjectProperty();
        busArriveLocationStartTime = new SimpleObjectProperty();
        busArriveLocationEndTime = new SimpleObjectProperty();
        busSeats = new SimpleStringProperty();
        eventTime = new SimpleObjectProperty();
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

    public ObjectProperty eventTimeProperty() {
        return eventTime;
    }

    public StringProperty eventLocationProperty() { return eventLocation; }

    public void saveEventChanges() {

        if(!(eventName.getValue().equals("") || eventName.getValue() == null))
        { LoggedUser.getInstance().getSelectedEvent().setEventName(eventName.getValue()); }
        if(!(eventDate.get() == null)) {
            Date date = new Date(eventDate.getValue().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
            LoggedUser.getInstance().getSelectedEvent().setDate(date);}
        if(!(eventTime.get() == null))
        {
            Time startTime = Time.valueOf(eventTime.get());
            LoggedUser.getInstance().getSelectedEvent().setStartTime(startTime);
        }
        if(!(eventDescription.getValue().equals("") || eventDescription.getValue() == null))
        { LoggedUser.getInstance().getSelectedEvent().setEventName(eventName.getValue()); }
        if(!(eventLocation.getValue().equals("") || eventLocation.getValue() == null))
        {LoggedUser.getInstance().getSelectedEvent().setLocation(eventLocation.getValue()); }
        if(!(busDepartLocation.getValue().equals("") || busDepartLocation.getValue() == null))
        { LoggedUser.getInstance().getSelectedEvent().getBus().setDepartLocation(busDepartLocation.getValue()); }
        if(!(busDepartLocationStartTime.get() == null))
        {
            Time departStartTime = Time.valueOf(busDepartLocationStartTime.get());
            LoggedUser.getInstance().getSelectedEvent().getBus().setDepartTimeStart(departStartTime);
        }
        if(!(busDepartLocationEndTime.get() == null))
        {
            Time departEndTime = Time.valueOf(busDepartLocationEndTime.get());
            LoggedUser.getInstance().getSelectedEvent().getBus().setDepartTimeEnd(departEndTime);
        }
        if(!(busArriveLocationStartTime.get() == null))
        {
            Time arriveStartTime = Time.valueOf(busArriveLocationStartTime.get());
            LoggedUser.getInstance().getSelectedEvent().getBus().setArriveTimeStart(arriveStartTime);
        }
        if(!(busArriveLocationEndTime.get() == null))
        {
            Time arriveEndTime = Time.valueOf(busArriveLocationEndTime.get());
            LoggedUser.getInstance().getSelectedEvent().getBus().setArriveTimeEnd(arriveEndTime); }
        if(!(busSeats.getValue().equals("") || busSeats.getValue() == null))
        {
            int noOfSeats = Integer.parseInt(busSeats.getValue());
            LoggedUser.getInstance().getSelectedEvent().getBus().setNoSeats(noOfSeats);
        }

        eventModel.editEvent(LoggedUser.getInstance().getSelectedEvent());
    }
}
