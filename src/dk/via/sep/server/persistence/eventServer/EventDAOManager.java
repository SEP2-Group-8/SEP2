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
    public boolean createEvent(Event event) {
        this.createBus(event.getBus());
        event.getBus().setBusId(this.getBus(event.getBus().getDepartTimeStart(),event.getBus().getArriveTimeStart()
                ,event.getBus().getDepartLocation(),event.getBus().getArriveLocation()).getBusId());
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
            return false;
        }
        return true;
    }

    @Override
    public boolean removeEvent(Event event) {
        try (java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM viaclub.event WHERE eventname = ? AND eventdate = ? ");
            statement.setString(1,event.getEventName());
            statement.setDate(2,event.getDate());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean editEvent( Event newEvent) {
        editBus(newEvent.getBus());
        try (java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE viaclub.event SET eventdate = ?,eventtime = ?," +
                    "location = ?,description= ?,eventname = ? " + "WHERE id=?");
            statement.setDate(1,newEvent.getDate());
            statement.setTime(2,newEvent.getStartTime());
            statement.setString(3,newEvent.getLocation());
            statement.setString(4,newEvent.getDescription());
            statement.setString(5,newEvent.getEventName());
            statement.setInt(6,newEvent.getEventId());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }


    public void editBus(Bus newBus) {
        try(java.sql.Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE viaclub.bus SET seats=?, departtimestart=?, departtimeend=?" +
                    ",arrivetimestart=?, arrivetimeend=?, departlocation=?, arrivelocation=? WHERE id=?");
            statement.setInt(1,newBus.getNoSeats());
            statement.setTime(2,newBus.getDepartTimeStart());
            statement.setTime(3,newBus.getDepartTimeEnd());
            statement.setTime(4,newBus.getArriveTimeStart());
            statement.setTime(5,newBus.getDepartTimeEnd());
            statement.setString(6,newBus.getDepartLocation());
            statement.setString(7,newBus.getArriveLocation());
            statement.setInt(8,newBus.getBusId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


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


    public Bus getBus(Time departTime1, Time arriveTime1, String departLocation1, String arriveLocation1)
    {
        try (java.sql.Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM viaclub.bus WHERE departtimestart = ? AND arrivetimestart = ?" +
                    " AND departlocation = ? AND arriveLocation = ?");
            statement.setTime(1,departTime1);
            statement.setTime(2,arriveTime1);
            statement.setString(3,departLocation1);
            statement.setString(4,arriveLocation1);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                int busID = resultSet.getInt("id");
                int seats = resultSet.getInt("seats");
                Time departTimeStart = resultSet.getTime("departtimestart");
                Time departTime = resultSet.getTime("departtimeend");
                Time arriveTimeStart = resultSet.getTime("arrivetimestart");
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
                Time departTimeStart = resultSet.getTime("departtimestart");
                Time departTime = resultSet.getTime("departtimeend");
                Time arriveTimeStart = resultSet.getTime("arrivetimestart");
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
