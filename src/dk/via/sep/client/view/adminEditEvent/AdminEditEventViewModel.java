package dk.via.sep.client.view.adminEditEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminEditEventViewModel {

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

    public AdminEditEventViewModel() {
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

    public void saveEventChanges() {
    }
}
