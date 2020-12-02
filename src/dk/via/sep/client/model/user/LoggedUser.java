package dk.via.sep.client.model.user;

import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

public class LoggedUser {
    private User user;
    private Event selectedEvent;
    private static LoggedUser loggedUser;

    private LoggedUser() {
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

}
