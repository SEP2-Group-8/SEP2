package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.server.persistence.Connection;

import java.sql.SQLException;

public class EventDAOManager extends Connection implements EventDAO {
    public java.sql.Connection getConnection() throws SQLException {
        return super.getConnection();
    }
}
