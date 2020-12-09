package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.shared.transfer.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface EventServerCallback extends Remote {
    boolean createEvent(Event event) throws RemoteException;

    boolean removeEvent(Event event) throws RemoteException;

    boolean editEvent(Event newEvent) throws RemoteException;

    ArrayList<Event> getEventList() throws RemoteException;
}
