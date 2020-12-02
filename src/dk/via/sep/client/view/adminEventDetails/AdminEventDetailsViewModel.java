package dk.via.sep.client.view.adminEventDetails;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminEventDetailsViewModel {
    private EventModel eventModel;
    private StringProperty eventName;
    private StringProperty eventDate;
    private StringProperty eventDescription;
    private StringProperty busID;
    private StringProperty busDepartLocation;
    private StringProperty busArriveLocation;
    private StringProperty busDepartLocationStartTime;
    private StringProperty busDepartLocationEndTime;
    private StringProperty busArriveLocationStartTime;
    private StringProperty busArriveLocationEndTime;
    private StringProperty busSeats;

    public AdminEventDetailsViewModel(){
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
    }

    public void initView() {
        Event event = LoggedUser.getInstance().getSelectedEvent();
        eventName.setValue(event.getEventName());
        eventDate.setValue(event.getStartDate().toString());
        eventDescription.setValue(event.getDescription());
        Bus bus = event.getBus();
        busID.setValue(String.valueOf(bus.getBusId()));
        busArriveLocation.setValue(bus.getArriveLocation());
        busDepartLocation.setValue(bus.getDepartLocation());
        busArriveLocationStartTime.setValue(bus.getDepartTime().toString());
        busArriveLocationEndTime.setValue(bus.getArriveTime().toString());
        busDepartLocationStartTime.setValue("not now");
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

    public void removeEvent(Event selectedEvent) {
        eventModel.removeEvent(selectedEvent);
    }
}
