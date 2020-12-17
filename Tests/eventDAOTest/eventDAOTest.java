package eventDAOTest;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.view.adminCreateEvent.AdminCreateEventViewModel;
import dk.via.sep.server.core.ServerDAOFactory;
import dk.via.sep.server.persistence.eventServer.EventDAO;
import dk.via.sep.server.persistence.eventServer.EventDAOManager;
import dk.via.sep.server.persistence.userServer.UserDAO;
import dk.via.sep.server.persistence.userServer.UserDAOManager;
import dk.via.sep.shared.transfer.Bus;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class eventDAOTest {

    private Bus bus;
    private Event event;
    private EventDAO eventDAO = new EventDAOManager();
    private UserDAO userDAO = new UserDAOManager();


    @BeforeEach
    public void setup()
    {
        StringProperty helper = new SimpleStringProperty();
        helper.set("test1234");
        StringProperty helper2= new SimpleStringProperty();
        helper2.set("10");
        System.out.println(helper2);
        ObjectProperty<LocalDate> localDate = new SimpleObjectProperty<>();
        ObjectProperty<LocalTime> localTime = new SimpleObjectProperty<>();
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        localDate.set(nowDate);
        localTime.set(nowTime);
        Date date = new Date(nowDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
        Time time = Time.valueOf(nowTime);
        bus = new Bus(Integer.parseInt(helper2.getValue()),time,time,time,time,helper.getValue(),helper.getValue());
        event = new Event(date,time,helper.getValue(),helper.getValue(),helper.getValue(),bus);
        if(eventDAO.getEvent(helper.getValue(),date)!= null)
        {
            event.setEventId(eventDAO.getEvent(helper.getValue(),date).getEventId());
            event.getBus().setBusId(eventDAO.getEvent(helper.getValue(),date).getBus().getBusId());
        }

    }

    @Test
    public void create_valid_event_test_test_currentDate_currentTime()
    {

        assertTrue(eventDAO.createEvent(event));
    }

    @Test
    public void create_existent_event_test_test_currentDate_currentTime()
    {
        assertFalse(eventDAO.createEvent(event));
    }


    @Test
    public void get_event_test_test_currentDate_currentTime()
    {
        Event helper = eventDAO.getEvent(event.getEventName(),event.getDate());
        assertTrue(helper.equals(event));
    }

    @Test
    public void join_event_test_test_user_mihail_1234_joins_bus()
    {
        User user = userDAO.getUser("mihail","1234");
        assertTrue(eventDAO.joinEvent(user,event,false));

    }

    @Test
    public void join_event_test_test_user_mihail_1234_joins_bus_failed()
    {
        User user = userDAO.getUser("mihail","1234");
        assertFalse(eventDAO.joinEvent(user,event,false));
    }

    @Test
    public void exit_event_test_test_user_mihail_1234_success()
    {
        User user = userDAO.getUser("mihail","1234");
        assertTrue(eventDAO.leaveEvent(user,event));
    }

    @Test
    public void get_user_list_event_test_test()
    {
        assertNotNull(eventDAO.getUserList(event));
    }

    @Test
    public void exit_event_test_test_user_mihail_1234_failed()
    {
        User user = userDAO.getUser("mihail","1234");
        assertFalse(eventDAO.leaveEvent(user,event));
    }
    @Test
    public void edit_existent_event_test_test_currentDate_currentTime()
    {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        Date date = new Date(nowDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
        Time time = Time.valueOf(nowTime);
        Event helper = new Event(date,time,"","","",bus);
        helper.setEventName(event.getEventName());
        helper.setDescription(event.getDescription());
        helper.setLocation(event.getLocation());
        helper.setStartTime(event.getStartTime());
        helper.setDate(event.getDate());

        Event newEvent = event;
        newEvent.setEventName("This");
        eventDAO.editEvent(newEvent);
        assertFalse(newEvent.equals(helper));
    }

    @Test
    public void remove_event_test_test()
    {
        eventDAO.removeEvent(event);
    }
}
