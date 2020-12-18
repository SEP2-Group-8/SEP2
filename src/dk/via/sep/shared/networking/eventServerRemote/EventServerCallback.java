package dk.via.sep.shared.networking.eventServerRemote;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Callback interface for the event server, extends remote so it is accessible over the network by the client.
 * Every method here is <b>only</b> called by the client. If you wish to add methods that the client should not have access to,
 * do so in the EventServer interface.
 * <b><i>All methods written here need to throw a RemoteException, as per RMI's specification.</i></b>
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 2.4
 */
public interface EventServerCallback extends Remote {

    /**
     * Method used to register a client to a <b>general callback</b>. A general callback will notify every active user of an event.
     *
     * @param clientCallback the client's callback interface, so it can be accessed by the server.
     * @throws RemoteException if the connection cannot be established.
     */
    void registerGeneralClientCallback(EventClientCallback clientCallback) throws RemoteException;

    /**
     * Method used to register a client to a <b>private callback</b>. A private callback will notify only the specified user, and no one else.
     * If you need to send a private set of data, you need to provide the client's UUID which is created at start-up and changes every time the
     * program is opened.
     *
     * @param clientCallback the client's callback interface
     * @param uuid           the client's UUID
     * @throws RemoteException if the connection cannot be established.
     */
    void registerClientCallback(EventClientCallback clientCallback, UUID uuid) throws RemoteException;

    /**
     * Method used to create an event in the database.
     * <b>May only be called by a user with administrator privileges.</b>
     *
     * @param event the event to be added.
     * @throws RemoteException if the connection cannot be established.
     */
    void createEvent(Event event) throws RemoteException;

    /**
     * Method used to remove an event from the database.
     * <b>May only be called by a user with administrator privileges.</b>
     *
     * @param event the event to be removed.
     * @throws RemoteException if the connection cannot be established.
     */
    void removeEvent(Event event) throws RemoteException;

    /**
     * Method used to edit an event from the database.
     * You need to provide the changes through an event object which has the same id as the event you're editing, otherwise the
     * event DAO will throw an exception.
     *
     * @param newEvent the event to be edited, along with the changes made to it.
     * @throws RemoteException if the connection cannot be established.
     */
    void editEvent(Event newEvent) throws RemoteException;

    /**
     * Method used to retrieve the event list from the database.
     *
     * @return the event list.
     * @throws RemoteException if the connection cannot be established.
     * @deprecated <b>User the async version instead.</b>
     */
    ArrayList<Event> getEventList() throws RemoteException;

    /**
     * Method used to join a user to an event.
     *
     * @param user  the user to be added to an event.
     * @param event the event to add the user to.
     * @param b     boolean if the user decides to ride the bus to the event or not.
     * @return boolean if the operation was successful or not.
     * @throws RemoteException if the connection cannot be established.
     */
    boolean joinEvent(User user, Event event, boolean b) throws RemoteException;

    /**
     * Method used to remove a user from an event.
     *
     * @param user  the user to be removed from an event.
     * @param event the event to be removed from.
     * @return boolean if the operation was successful or not.
     * @throws RemoteException if the connection cannot be established.
     */
    boolean leaveEvent(User user, Event event) throws RemoteException;

    /**
     * Method used to retrieve the list of users of an event from the database.
     *
     * @param event the event to get the list from.
     * @return the list of users.
     * @throws RemoteException if the connection cannot be established.
     */
    ArrayList<User> getUserList(Event event) throws RemoteException;

    /**
     * Method used to get the list of events asynchronous. This method will then call another method that will send the event list
     * back to the client that requested it.
     *
     * @param clientID the client's UUID, necessary to send back the list to the client that requested it.
     * @throws RemoteException if the connection cannot be established.
     */
    void getEventListASync(UUID clientID) throws RemoteException;
}
