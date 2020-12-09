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
    public boolean createEvent(Event event) {
        return eventServerModel.createEvent(event);
    }

    @Override
    public boolean removeEvent(Event event) {
        return eventServerModel.removeEvent(event);
    }

    @Override
    public boolean editEvent(Event newEvent) {
        return eventServerModel.editEvent(newEvent);
    }

    @Override
    public ArrayList<Event> getEventList() {
        return eventServerModel.getEventList();
    }
}
