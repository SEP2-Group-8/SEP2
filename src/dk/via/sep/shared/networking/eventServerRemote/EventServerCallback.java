package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

public interface EventServerCallback extends Remote {

    void registerGeneralClientCallback(EventClientCallback clientCallback) throws RemoteException;

    void registerClientCallback(EventClientCallback clientCallback, UUID uuid) throws RemoteException;

    void createEvent(Event event) throws RemoteException;

    void removeEvent(Event event) throws RemoteException;

    void editEvent(Event newEvent) throws RemoteException;

    ArrayList<Event> getEventList() throws RemoteException;

    boolean joinEvent(User user, Event event, boolean b) throws RemoteException;

    boolean leaveEvent(User user, Event event) throws RemoteException;

    ArrayList<User> getUserList(Event event) throws RemoteException;

    void getEventListASync(UUID clientID) throws RemoteException;
}
