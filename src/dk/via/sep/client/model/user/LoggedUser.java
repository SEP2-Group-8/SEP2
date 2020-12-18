package dk.via.sep.client.model.user;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.util.UUID;

/**
 * This class keeps track of the user that is currently logged into the app.
 * It is used throughout the whole program and facilitates the server/client connection, and makes it easy to
 * keep track of changes and the user's credentials. It is a singleton so it's accessible from anywhere in the program.
 */
public class LoggedUser {
    private User user;
    private Event selectedEvent;
    private static LoggedUser loggedUser;
    private final UUID clientID;

    /**
     * Private constructor that creates this client's UUID.
     * This UUID will change each time the client is started and it is completely random* .
     * It is used for instance verification and custom events. (See the UserClient or the EventClient interfaces for its uses)
     */
    private LoggedUser() {
        clientID = UUID.randomUUID();
    }

    /**
     * Static synchronized getInstance method, used to get access to this class's instance.
     *
     * @return the class's instance.
     */
    public synchronized static LoggedUser getInstance() {
        if (loggedUser == null)
            loggedUser = new LoggedUser();
        return loggedUser;
    }

    /**
     * A method used to set the currently logged in user.
     * This method should only be called at login and whenever the user updates its credentials.
     *
     * @param user the user that is currently logged in.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method used to get the currently logged in user.
     *
     * @return the currently logged in user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Method used to get the selected event from the event list. Used in the Main event views, for both the admin and the user.
     * Whenever an event is created and added to the list, an event listener listens for clicks on it. When clicked, the event is set below in the
     * set method, so it can be used by the other views that need it, and then can be retrieved here.
     *
     * @return the selected event.
     */
    public Event getSelectedEvent() {
        return selectedEvent;
    }

    /**
     * Method used to set the selected event.
     *
     * @param selectedEvent the selected event.
     */
    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    /**
     * Method used to retrieve the client's UUID. For a better explanation of this id see the constructor of this class.
     *
     * @return the client's UUID
     */
    public UUID getClientID() {
        return clientID;
    }
}
