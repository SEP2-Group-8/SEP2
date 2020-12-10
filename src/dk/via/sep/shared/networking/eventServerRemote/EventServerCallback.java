package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface EventServerCallback extends Remote {
    boolean createEvent(Event event) throws RemoteException;

    boolean removeEvent(Event event) throws RemoteException;

    boolean editEvent(Event newEvent) throws RemoteException;

    ArrayList<Event> getEventList() throws RemoteException;

    boolean joinEvent(User user, Event event, boolean b) throws RemoteException;

    boolean leaveEvent(User user, Event event) throws RemoteException;

    ArrayList<User> getUserList(Event event) throws RemoteException;
}
