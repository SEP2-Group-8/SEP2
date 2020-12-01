package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.server.persistence.Connection;
import dk.via.sep.shared.transfer.Event;

import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAOManager extends Connection implements EventDAO {
    public java.sql.Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public ArrayList<Event> getEventList() {
        return null;
    }

    @Override
    public void createEvent(Event event) {

    }

    @Override
    public void removeEvent(Event event) {

    }

    @Override
    public void editEvent(Event oldEvent, Event newEvent) {

    }
}
