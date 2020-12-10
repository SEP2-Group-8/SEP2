package dk.via.sep.client.model.user;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.util.UUID;

public class LoggedUser {
    private User user;
    private Event selectedEvent;
    private static LoggedUser loggedUser;
    private UUID clientID;

    private LoggedUser() {
        clientID = UUID.randomUUID();
    }

    public synchronized static LoggedUser getInstance() {
        if (loggedUser == null)
            loggedUser = new LoggedUser();
        return loggedUser;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public UUID getClientID() {
        return clientID;
    }
}
