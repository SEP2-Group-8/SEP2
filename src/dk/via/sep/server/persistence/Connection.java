package dk.via.sep.server.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Connection {

    private static final String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/iggnjjuz";
    private static final String user = "iggnjjuz";
    private static final String password = "9RjTujol-f92I9bCJHciRdFUdCh9cuVU";

    public Connection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}