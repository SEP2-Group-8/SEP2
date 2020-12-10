package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.client.networking.eventClient.EventClient;
import dk.via.sep.shared.transfer.Event;

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

    void getEventListASync(UUID clientID) throws RemoteException;
}
