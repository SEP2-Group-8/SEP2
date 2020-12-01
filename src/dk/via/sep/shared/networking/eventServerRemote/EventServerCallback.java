package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.shared.transfer.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface EventServerCallback extends Remote {
    void createEvent(Event event) throws RemoteException;

    void removeEvent(Event event) throws RemoteException;

    void editEvent(Event oldEvent, Event newEvent) throws RemoteException;

    ArrayList<Event> getEventList() throws RemoteException;
}
