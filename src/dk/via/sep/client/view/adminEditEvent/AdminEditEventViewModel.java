package dk.via.sep.client.view.adminEditEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.user.LoggedUser;
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

    private final EventModel eventModel;
    private final StringProperty eventName;
    private final ObjectProperty<LocalDate> eventDate;
    private final StringProperty eventDescription;
    private final StringProperty eventLocation;
    private final ObjectProperty<LocalTime> eventTime;
    private final StringProperty busDepartLocation;
    private final ObjectProperty<LocalTime> busDepartLocationStartTime;
    private final ObjectProperty<LocalTime> busDepartLocationEndTime;
    private final ObjectProperty<LocalTime> busArriveLocationStartTime;
    private final ObjectProperty<LocalTime> busArriveLocationEndTime;
    private final StringProperty busSeats;

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

    public StringProperty eventLocationProperty() {
        return eventLocation;
    }

    public void saveEventChanges() {
        Event currentEvent = LoggedUser.getInstance().getSelectedEvent();
        //TODO checks for the time so the time of the bus isn't redundant if it is introduced. Might need to switch the structure of the ifs

        if (!(eventName.getValue().equals("") || eventName.getValue() == null)) {
            currentEvent.setEventName(eventName.getValue());
        }
        if (!(eventDate.get() == null)) {
            Date date = new Date(eventDate.getValue().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
            currentEvent.setDate(date);
        }
        if (!(eventTime.get() == null)) {
            Time startTime = Time.valueOf(eventTime.get());
            currentEvent.setStartTime(startTime);
        }
        if (!(eventDescription.getValue() == null)) {
            currentEvent.setDescription(eventDescription.getValue());
        }
        if (!(eventLocation.getValue() == null)) {
            currentEvent.setLocation(eventLocation.getValue());
        }
        if (!(busDepartLocation.getValue() == null)) {
            currentEvent.getBus().setDepartLocation(busDepartLocation.getValue());
        }
        if (!(busDepartLocationStartTime.get() == null)) {
            Time departStartTime = Time.valueOf(busDepartLocationStartTime.get());
            currentEvent.getBus().setDepartTimeStart(departStartTime);
        }
        if (!(busDepartLocationEndTime.get() == null)) {
            Time departEndTime = Time.valueOf(busDepartLocationEndTime.get());
            currentEvent.getBus().setDepartTimeEnd(departEndTime);
        }
        if (!(busArriveLocationStartTime.get() == null)) {
            Time arriveStartTime = Time.valueOf(busArriveLocationStartTime.get());
            currentEvent.getBus().setArriveTimeStart(arriveStartTime);
        }
        if (!(busArriveLocationEndTime.get() == null)) {
            Time arriveEndTime = Time.valueOf(busArriveLocationEndTime.get());
            currentEvent.getBus().setArriveTimeEnd(arriveEndTime);
        }
        if (!(busSeats.getValue() == null)) {
            int noOfSeats = Integer.parseInt(busSeats.getValue());
            currentEvent.getBus().setNoSeats(noOfSeats);
        }
        LoggedUser.getInstance().setSelectedEvent(currentEvent);
        eventModel.editEvent(currentEvent);
    }
}
