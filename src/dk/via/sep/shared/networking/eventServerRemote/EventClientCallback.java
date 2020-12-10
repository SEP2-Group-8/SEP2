package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.shared.transfer.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface EventClientCallback extends Remote {

    void eventCreated(Event event) throws RemoteException;

    void eventEdited(Event event) throws RemoteException;

    void eventRemoved(Event event) throws RemoteException;

    void retrieveEventList(ArrayList<Event> events) throws RemoteException;
}
