package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.shared.transfer.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The client callback interface that the server needs to call methods on the client.
 * If you need to call methods on the client from the server, add them below.
 * <b>All methods implemented here need to throw a Remote Exception as per RMI's specification</b>.
 */
public interface EventClientCallback extends Remote {

    /**
     * Method used to notify a client of a newly added event.
     *
     * @param event the newly added event.
     * @throws RemoteException if the connection cannot be established.
     */
    void eventCreated(Event event) throws RemoteException;

    /**
     * Method used to notify a client of a newly edited event.
     *
     * @param event the newly edited event.
     * @throws RemoteException if the connection cannot be established.
     */
    void eventEdited(Event event) throws RemoteException;

    /**
     * Method used to notify a client of a removed event.
     *
     * @param event the removed event.
     * @throws RemoteException if the connection cannot be established.
     */
    void eventRemoved(Event event) throws RemoteException;

    /**
     * Method used to send the list of events back to the client.
     * This method will only be called by the server if the client first sent a request for it.
     *
     * @param events the list of events.
     * @throws RemoteException if the connection cannot be established.
     */
    void retrieveEventList(ArrayList<Event> events) throws RemoteException;
}
