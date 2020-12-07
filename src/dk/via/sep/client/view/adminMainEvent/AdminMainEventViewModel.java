package dk.via.sep.client.view.adminMainEvent;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.shared.utils.Subject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;



public class AdminMainEventViewModel implements Subject {
    private EventModel eventModel;
    private PropertyChangeSupport support;

    public AdminMainEventViewModel() {
        eventModel = ModelFactory.getInstance().getEventModel();
        support = new PropertyChangeSupport(this);
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
