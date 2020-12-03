package dk.via.sep.client.view.adminMainEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.sql.Time;

public class AdminMainEventViewModel implements Subject {
    private EventModel eventModel;
    private PropertyChangeSupport support;

    public AdminMainEventViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
        support = new PropertyChangeSupport(this);
        createEvent();
    }

    public void createEvent() {
        Bus bus = new Bus(50,
                new Time(2005,9,21),
                new Time(8,55,00),
                new Time(2005,9,21),
                new Time(8,55,00),
                "Horsens",
                "Copenhagen");
        Event event = new Event(new Date(2020,9,21),
                new Time(8,55,00),
                "Copenhagen",
                "See us win a match by breaking everybody's legs",
                "Copenhagen massacre",
                bus);
        support.firePropertyChange("EventCreated", null, event);
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
