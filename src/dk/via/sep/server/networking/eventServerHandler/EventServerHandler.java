package dk.via.sep.server.networking.eventServerHandler;

import dk.via.sep.server.model.eventServerModel.EventServerModel;
import dk.via.sep.shared.transfer.Event;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class EventServerHandler implements EventServer {

    private final EventServerModel eventServerModel;

    public EventServerHandler(EventServerModel eventServerModel) {
        this.eventServerModel = eventServerModel;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createEvent(Event event) {
        eventServerModel.createEvent(event);
    }

    @Override
    public void removeEvent(Event event) {
        eventServerModel.removeEvent(event);
    }

    @Override
    public void editEvent(Event oldEvent, Event newEvent) {
        eventServerModel.editEvent(oldEvent, newEvent);
    }

    @Override
    public ArrayList<Event> getEventList() {
        return eventServerModel.getEventList();
    }
}
