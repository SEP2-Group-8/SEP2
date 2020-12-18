package dk.via.sep.client.networking.eventClient;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

public interface EventClient extends Subject {
    /**
     * Method used to start the event client.
     */
    void startClient();

    /**
     * Method used to create an event in the database.
     *
     * @param event the event to be created.
     */
    void createEvent(Event event);

    /**
     * Method used to remove an event from the database.
     *
     * @param event the event to be removed.
     */
    void removeEvent(Event event);

    /**
     * Method used to edit an event from the database.
     *
     * @param oldEvent the event to be edited, with the changes already applied to it.
     */
    void editEvent(Event oldEvent);

    /**
     * Method used to get the full list of events from the database.
     *
     * @return the list of events from the database.
     * @deprecated
     */
    ArrayList<Event> getEventList();

    /**
     * Method used to join the currently logged in user to an event, and to a bus.
     *
     * @param user  the user to be joined.
     * @param event the event to join to.
     * @param b     boolean that specifies if the user wants a bus seat or not.
     * @return boolean if the user was joined successfully or not.
     */
    boolean joinEvent(User user, Event event, boolean b);

    /**
     * Method used to remove the currently logged in user from an event.
     *
     * @param user  the user to be removed.
     * @param event the event to be removed from.
     * @return boolean if the user has been removed successfully or not.
     */
    boolean leaveEvent(User user, Event event);

    /**
     * Method used to get the list of users from an event.
     *
     * @param event the event to get the list from.
     * @return the list of users.
     */
    ArrayList<User> getUserList(Event event);

    /**
     * Method used to get the list of events from the database. Better asynchronous implementation, removes freezes from the program due to
     * large amounts of data. Does not return anything, but after the call has been completed, the server will notify the client with the list
     * ready.
     */
    void getEventListAsync();
}
