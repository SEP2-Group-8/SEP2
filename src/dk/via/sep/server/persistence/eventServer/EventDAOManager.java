package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.server.persistence.Connection;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Constantin Mihail
 * @version 1.0.0
 * Implementation for the eventDAO interface that will send forward data towards a database hosted on a cloud.
 */
public class EventDAOManager extends Connection implements EventDAO {
    public java.sql.Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    /**
     * A method that returns the list of event that is currently available in the database
     *
     * @return ArrayList of type Event
     */
    @Override
    public ArrayList<Event> getEventList() {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.event");
            ArrayList<Event> events = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int eventID = resultSet.getInt("id");
                Date startDate = resultSet.getDate("eventdate");
                Time startTime = resultSet.getTime("eventtime");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");
                String eventName = resultSet.getString("eventname");
                int busID = resultSet.getInt("busid");
                Event event = new Event(startDate, startTime, location, description, eventName, this.getBus(busID));
                event.setEventId(eventID);
                events.add(event);

            }
            return events;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    /**
     * Method that adds a new event in the database, based on whatever Event class it's receiving. Also this method includes the call
     * of the createBus method, which creates the bus the event comes with.
     *
     * @param event Event class containing the details we need for the database
     * @return boolean that represents if the event was created or not.
     */
    @Override
    public boolean createEvent(Event event) {
        this.createBus(event.getBus());
        event.getBus().setBusId(this.getBus(event.getBus().getDepartTimeStart(), event.getBus().getArriveTimeStart()
                , event.getBus().getDepartLocation(), event.getBus().getArriveLocation()).getBusId());
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO viaclub.event(eventdate,eventtime," +
                    "location,description,eventname,busid) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, event.getDate());
            statement.setTime(2, event.getStartTime());
            statement.setString(3, event.getLocation());
            statement.setString(4, event.getDescription());
            statement.setString(5, event.getEventName());
            statement.setInt(6, event.getBus().getBusId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("INSERTED with ID " + resultSet.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Method that removes an event from the list in the database.
     *
     * @param event an Event class that represents the event sent forward to remove
     * @return a boolean that represents if the action was successful or not
     */
    @Override
    public boolean removeEvent(Event event) {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM viaclub.event WHERE eventname = ? AND eventdate = ? ");
            statement.setString(1, event.getEventName());
            statement.setDate(2, event.getDate());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * A method that modifies the event in the database based on the ID of the event, as the method only uses 1 parameter which is an event class.
     * The event contains the ID of the event that needs to be modified.
     *
     * @param newEvent an event class parameter that contains the new data of the event but the old id
     * @return a boolean the represents if the action was successful or not.
     */
    @Override
    public boolean editEvent(Event newEvent) {
        editBus(newEvent.getBus());
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE viaclub.event SET eventdate = ?,eventtime = ?," +
                    "location = ?,description= ?,eventname = ? " + "WHERE id=?");
            statement.setDate(1, newEvent.getDate());
            statement.setTime(2, newEvent.getStartTime());
            statement.setString(3, newEvent.getLocation());
            statement.setString(4, newEvent.getDescription());
            statement.setString(5, newEvent.getEventName());
            statement.setInt(6, newEvent.getEventId());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Modifies the bus of an existing event. This method is called when the editEvent method is called, as some data for the bus might be
     * modified too.
     *
     * @param newBus contains the new data of the bus, except for the ID which is the id of the bus that needs to be search on.
     */
    public void editBus(Bus newBus) {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE viaclub.bus SET seats=?,occupiedseats = ?, departtimestart=?, departtimeend=?" +
                    ",arrivetimestart=?, arrivetimeend=?, departlocation=?, arrivelocation=? WHERE id=?");
            statement.setInt(1, newBus.getNoSeats());
            statement.setInt(2, newBus.getOccupiedSeats());
            statement.setTime(3, newBus.getDepartTimeStart());
            statement.setTime(4, newBus.getDepartTimeEnd());
            statement.setTime(5, newBus.getArriveTimeStart());
            statement.setTime(6, newBus.getDepartTimeEnd());
            statement.setString(7, newBus.getDepartLocation());
            statement.setString(8, newBus.getArriveLocation());
            statement.setInt(9, newBus.getBusId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Creates the bus that comes along with an event in the database, giving it an unique id
     *
     * @param bus contains the data of the bus that needs to be added in the database
     */
    public void createBus(Bus bus) {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO viaclub.bus(seats,departtimestart,departtimeend,arrivetimestart," +
                    "arrivetimeend,departlocation,arrivelocation) VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bus.getNoSeats());
            statement.setTime(2, bus.getDepartTimeStart());
            statement.setTime(3, bus.getDepartTimeEnd());
            statement.setTime(4, bus.getArriveTimeStart());
            statement.setTime(5, bus.getArriveTimeEnd());
            statement.setString(6, bus.getDepartLocation());
            statement.setString(7, bus.getArriveLocation());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("INSERTED with ID " + resultSet.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Gets a specific event from the database that is searched from 2 specific values
     *
     * @param eventName1 contains the name that needs to be searched in the database
     * @param startDate1 contains the date that needs to be searched in the database
     * @return an Event class that is found in the database with those 2 specific arguments.
     */
    @Override
    public Event getEvent(String eventName1, Date startDate1) {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.event WHERE eventname = ? AND eventdate = ?");
            statement.setString(1, eventName1);
            statement.setDate(2, startDate1);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int eventID = resultSet.getInt("id");
                Date startDate = resultSet.getDate("eventdate");
                Time startTime = resultSet.getTime("eventtime");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");
                String eventName = resultSet.getString("eventname");
                int busID = resultSet.getInt("busid");
                Event event = new Event(startDate, startTime, location, description, eventName, this.getBus(busID));
                event.setEventId(eventID);
                return event;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    /**
     * This adds the user to a list that contains users and events. The user is represented by his ID and the event is also represented by the
     * ID. The primary keys are both of the IDs and they are also foreign keys.
     *
     * @param user    this is the user that wants to join the event. It has its unique id stored in this class.
     * @param event   this is the event that a user wants to join. It has its unique id stored in this class.
     * @param joinBus this represents if the user wanted to join the bus from the event or not.
     * @return
     */
    @Override
    public boolean joinEvent(User user, Event event, boolean joinBus) {
        System.out.println(joinBus);
        if (joinBus) {
            Bus bus = this.getBus(event.getBus().getBusId());
            if (!(bus.getOccupiedSeats() >= bus.getNoSeats())) {
                System.out.println(bus.getBusId());
                bus.setOccupiedSeats(bus.getOccupiedSeats() + 1);
                this.editBus(bus);
            } else return false;

        }
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO viaclub.eventlist(eventid,userid,isinbus) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, event.getEventId());
            statement.setInt(2, user.getUser_id());
            statement.setBoolean(3, joinBus);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("INSERTED the user with the id: " + resultSet.getInt("userid") + "\n in the event with the ID: " + resultSet.getInt("eventid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This removes the user from the list of people going to events, searching for the IDs of both the userid and the eventid.
     *
     * @param user  the user that wants to leave the event.
     * @param event the event that the user wants to leave.
     * @return
     */
    @Override
    public boolean leaveEvent(User user, Event event) {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM viaclub.eventlist WHERE userid = ? AND eventid = ?", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getUser_id());
            statement.setInt(2, event.getEventId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                if (resultSet.getBoolean("isinbus")) {
                    Bus bus = this.getBus(event.getBus().getBusId());
                    bus.setOccupiedSeats(bus.getOccupiedSeats() - 1);
                    this.editBus(bus);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This returns an arraylist of users that are present in the list of events. It will only return users that are signed in a specific event.
     *
     * @param event this is the parameter from where you get the ID.
     * @return an ArrayList of users that are signed up in the event parameter.
     */
    @Override
    public ArrayList<User> getUserList(Event event) {
        try (java.sql.Connection connection = getConnection()) {
            ArrayList<User> users = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.useraccount NATURAL JOIN viaclub.eventlist WHERE eventlist.eventid = ?" +
                    " AND viaclub.useraccount.user_id = eventlist.userid");
            statement.setInt(1, event.getEventId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password_name = resultSet.getString("password");
                String username_name = resultSet.getString("username");
                User user = new User(email, password_name, username_name);
                user.setUser_id(user_id);
                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * This method returns a bus that is present in the database with the information given from the 4 parameters.
     *
     * @param departTime1     the departing time of the bus
     * @param arriveTime1     the arriving time of the bus
     * @param departLocation1 the departing location of the bus
     * @param arriveLocation1 the arriving location of the bus
     * @return a Bus class that contains the information of the bus from the database
     */
    public Bus getBus(Time departTime1, Time arriveTime1, String departLocation1, String arriveLocation1) {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.bus WHERE departtimestart = ? AND arrivetimestart = ?" +
                    " AND departlocation = ? AND arriveLocation = ?");
            statement.setTime(1, departTime1);
            statement.setTime(2, arriveTime1);
            statement.setString(3, departLocation1);
            statement.setString(4, arriveLocation1);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int busID = resultSet.getInt("id");
                int seats = resultSet.getInt("seats");
                Time departTimeStart = resultSet.getTime("departtimestart");
                Time departTime = resultSet.getTime("departtimeend");
                Time arriveTimeStart = resultSet.getTime("arrivetimestart");
                Time arriveTime = resultSet.getTime("arrivetimeend");
                String departLocation = resultSet.getString("departlocation");
                String arriveLocation = resultSet.getString("arrivelocation");
                Bus bus = new Bus(seats, departTimeStart, departTime, arriveTimeStart, arriveTime, departLocation, arriveLocation);
                bus.setBusId(busID);
                return bus;
            }
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * This method is for internal use in the DAO, as it searches for the bus using the ID. ID is unique so there cannot be multiple instances
     * of the same bus with that ID.
     *
     * @param id an integer representing the ID of the bus
     * @return the Bus that is found in the database with the given id.
     */
    public Bus getBus(int id) {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.bus WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int busID = resultSet.getInt("id");
                int seats = resultSet.getInt("seats");
                Time departTimeStart = resultSet.getTime("departtimestart");
                Time departTime = resultSet.getTime("departtimeend");
                Time arriveTimeStart = resultSet.getTime("arrivetimestart");
                Time arriveTime = resultSet.getTime("arrivetimeend");
                String departLocation = resultSet.getString("departlocation");
                String arriveLocation = resultSet.getString("arrivelocation");
                Bus bus = new Bus(seats, departTimeStart, departTime, arriveTimeStart, arriveTime, departLocation, arriveLocation);
                bus.setBusId(busID);
                return bus;
            }
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return null;
    }
}
