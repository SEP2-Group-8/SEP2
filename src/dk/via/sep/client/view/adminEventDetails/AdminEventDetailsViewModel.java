package dk.via.sep.client.view.adminEventDetails;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminEventDetailsViewModel {
    private final EventModel eventModel;
    private final StringProperty eventName;
    private final StringProperty eventDate;
    private final StringProperty eventTime;
    private final StringProperty eventDescription;
    private final StringProperty busID;
    private final StringProperty busDepartLocation;
    private final StringProperty busArriveLocation;
    private final StringProperty busDepartLocationStartTime;
    private final StringProperty busDepartLocationEndTime;
    private final StringProperty busArriveLocationStartTime;
    private final StringProperty busArriveLocationEndTime;
    private final StringProperty busSeats;

    public AdminEventDetailsViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
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

    public void removeEvent(Event selectedEvent) {
        eventModel.removeEvent(selectedEvent);
    }
}
