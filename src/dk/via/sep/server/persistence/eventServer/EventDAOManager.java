package dk.via.sep.server.persistence.eventServer;

import dk.via.sep.server.persistence.Connection;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;

import java.sql.*;
import java.util.ArrayList;

public class EventDAOManager extends Connection implements EventDAO {
    public java.sql.Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public ArrayList<Event> getEventList() {
        try (java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.event");
            ArrayList<Event> events = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                int eventID = resultSet.getInt("id");
                Date startDate = resultSet.getDate("eventdate");
                Time startTime = resultSet.getTime("eventtime");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");
                String eventName = resultSet.getString("eventname");
                int busID = resultSet.getInt("busid");
                Event event = new Event(startDate,startTime,location,description,eventName,this.getBus(busID));
                event.setEventId(eventID);
                events.add(event);

            }
            return events;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void createEvent(Event event) {
        try (java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO viaclub.event(eventdate,eventtime," +
                    "location,description,eventname,busid) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1,event.getDate());
            statement.setTime(2,event.getStartTime());
            statement.setString(3,event.getLocation());
            statement.setString(4,event.getDescription());
            statement.setString(5,event.getEventName());
            statement.setInt(6,event.getBus().getBusId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next())
            {
                System.out.println("INSERTED with ID " + resultSet.getInt("id"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void removeEvent(Event event) {
        try (java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM viaclub.event WHERE eventname = ? AND eventdate = ? ");
            statement.setString(1,event.getEventName());
            statement.setDate(2,event.getDate());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                System.out.println("Removed event: " + event.getEventName() + " and id: " + event.getEventId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void editEvent(Event oldEvent, Event newEvent) {
        try (java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE viaclub.event SET eventdate = ?,eventtime = ?," +
                    "location = ?,description= ?,eventname = ?,busid= ? " +
                    "WHERE eventdate = ? AND eventname = ?");
            statement.setDate(1,newEvent.getDate());
            System.out.println(statement);
            statement.setTime(2,newEvent.getStartTime());
            statement.setString(3,newEvent.getLocation());
            statement.setString(4,newEvent.getDescription());
            statement.setString(5,newEvent.getEventName());
            statement.setInt(6,newEvent.getBus().getBusId());
            statement.setDate(7,oldEvent.getDate());
            statement.setString(8,oldEvent.getEventName());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void createBus(Bus bus) {
        try (java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO viaclub.bus(seats,departtimestart,departtimeend,arrivetimestart," +
                    "arrivetimeend,departlocation,arrivelocation) VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,bus.getNoSeats());
            statement.setTime(2,bus.getDepartTimeStart());
            statement.setTime(3,bus.getDepartTimeEnd());
            statement.setTime(4,bus.getArriveTimeStart());
            statement.setTime(5,bus.getArriveTimeEnd());
            statement.setString(6,bus.getDepartLocation());
            statement.setString(7,bus.getArriveLocation());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next())
            {
                System.out.println("INSERTED with ID " + resultSet.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Event getEvent(String eventName1, Date startDate1)
    {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.event WHERE eventname = ? AND eventdate = ?");
            statement.setString(1,eventName1);
            statement.setDate(2,startDate1);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                int eventID = resultSet.getInt("id");
                Date startDate = resultSet.getDate("eventdate");
                Time startTime = resultSet.getTime("eventtime");
                String location = resultSet.getString("location");
                String description = resultSet.getString("description");
                String eventName = resultSet.getString("eventname");
                int busID = resultSet.getInt("busid");
                Event event = new Event(startDate,startTime,location,description,eventName,this.getBus(busID));
                event.setEventId(eventID);
                return event;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    @Override
    public Bus getBus(Date departDate1, Date arriveDate1, String departLocation1, String arriveLocation1)
    {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.bus WHERE departdatestart = ? AND arrivedatestart = ?" +
                    " AND departlocation = ? AND arriveLocation = ?");
            statement.setDate(1,departDate1);
            statement.setDate(2,arriveDate1);
            statement.setString(3,departLocation1);
            statement.setString(4,arriveLocation1);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                int busID = resultSet.getInt("id");
                int seats = resultSet.getInt("seats");
                Time departTimeStart = resultSet.getTime("departdatestart");
                Time departTime = resultSet.getTime("departtimeend");
                Time arriveTimeStart = resultSet.getTime("arrivedatestart");
                Time arriveTime = resultSet.getTime("arrivetimeend");
                String departLocation = resultSet.getString("departlocation");
                String arriveLocation = resultSet.getString("arrivelocation");
                Bus bus = new Bus(seats,departTimeStart,departTime,arriveTimeStart,arriveTime,departLocation,arriveLocation);
                bus.setBusId(busID);
                return bus;
            }        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return null;
    }

    public Bus getBus(int id)
    {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.bus WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                int busID = resultSet.getInt("id");
                int seats = resultSet.getInt("seats");
                Time departTimeStart = resultSet.getTime("departdatestart");
                Time departTime = resultSet.getTime("departtimeend");
                Time arriveTimeStart = resultSet.getTime("arrivedatestart");
                Time arriveTime = resultSet.getTime("arrivetimeend");
                String departLocation = resultSet.getString("departlocation");
                String arriveLocation = resultSet.getString("arrivelocation");
                Bus bus = new Bus(seats,departTimeStart,departTime,arriveTimeStart,arriveTime,departLocation,arriveLocation);
                bus.setBusId(busID);
                return bus;
            }        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return null;
    }
}
