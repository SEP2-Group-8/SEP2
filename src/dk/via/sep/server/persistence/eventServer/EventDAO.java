package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author Constantin Mihail
 * @version 1.0.0
 */
public interface EventDAO {
    /**
     * A method that returns the list of event that is currently available in the database
     * @return ArrayList of type Event
     */
    ArrayList<Event> getEventList();

    /**
     * Method that adds a new event in the database, based on whatever Event class it's receiving. Also this method includes the call
     * of the createBus method, which creates the bus the event comes with.
     * @param event Event class containing the details we need for the database
     * @return boolean that represents if the event was created or not.
     */
    boolean createEvent(Event event);

    /**
     * Method that removes an event from the list in the database.
     * @param event an Event class that represents the event sent forward to remove
     * @return a boolean that represents if the action was successful or not
     */
    boolean removeEvent(Event event);

    /**
     * A method that modifies the event in the database based on the ID of the event, as the method only uses 1 parameter which is an event class.
     * The event contains the ID of the event that needs to be modified.
     * @param newEvent an event class parameter that contains the new data of the event but the old id
     * @return a boolean the represents if the action was successful or not.
     */
    boolean editEvent(Event newEvent);

    /**
     * Gets a specific event from the database that is searched from 2 specific values
     * @param eventName contains the name that needs to be searched in the database
     * @param startDate contains the date that needs to be searched in the database
     * @return an Event class that is found in the database with those 2 specific arguments.
     */
    Event getEvent(String eventName, Date startDate);

    /**
     * This adds the user to a list that contains users and events. The user is represented by his ID and the event is also represented by the
     * ID. The primary keys are both of the IDs and they are also foreign keys.
     * @param user this is the user that wants to join the event. It has its unique id stored in this class.
     * @param event this is the event that a user wants to join. It has its unique id stored in this class.
     * @param joinBus this represents if the user wanted to join the bus from the event or not.
     * @return
     */
    boolean joinEvent(User user, Event event, boolean joinBus);

    /**
     * This removes the user from the list of people going to events, searching for the IDs of both the userid and the eventid.
     * @param user the user that wants to leave the event.
     * @param event the event that the user wants to leave.
     * @return
     */
    boolean leaveEvent(User user, Event event);

    /**
     * This returns an arraylist of users that are present in the list of events. It will only return users that are signed in a specific event.
     * @param event this is the parameter from where you get the ID.
     * @return an ArrayList of users that are signed up in the event parameter.
     */
    ArrayList<User> getUserList(Event event);
}
