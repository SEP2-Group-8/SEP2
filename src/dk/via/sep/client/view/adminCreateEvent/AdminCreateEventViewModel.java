package dk.via.sep.client.view.adminCreateEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class AdminCreateEventViewModel {
    private EventModel eventModel;
    private StringProperty eventName;
    private StringProperty eventDate;
    private StringProperty eventDescription;
    private StringProperty busDepartLocation;
    private StringProperty busArriveLocation;
    private StringProperty busDepartLocationStartTime;
    private StringProperty busDepartLocationEndTime;
    private StringProperty busArriveLocationStartTime;
    private StringProperty busArriveLocationEndTime;
    private StringProperty busSeats;
    private StringProperty eventLocation;


    public AdminCreateEventViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
        eventName = new SimpleStringProperty();
        eventDate = new SimpleStringProperty();
        eventDescription = new SimpleStringProperty();
        eventLocation = new SimpleStringProperty();
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

    public StringProperty eventLocationProperty() {
        return eventLocation;
    }

    public void createEvent() {
        System.out.println(eventDate.getValue());

        Date date = new Date(1, 1, 1);


        //Event event = new Event();
    }
}
