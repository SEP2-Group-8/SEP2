package dk.via.sep.shared.transfer;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Event implements Serializable {
    private Date date;
    private Time startTime;
    private String location;
    private String description;
    private String eventName;
    private int eventId;
    private Bus bus;

    public Event(Date date, Time startTime, String location, String description, String eventName, Bus bus) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.description = description;
        this.eventName = eventName;
        this.bus = bus;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getEventName() {
        return eventName;
    }

    public int getEventId() {
        return eventId;
    }

    public Bus getBus() {
        return bus;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return Objects.equals(location, event.location) && Objects.equals(description, event.description)
                && Objects.equals(eventName, event.eventName);
    }

    public String toString() {
        return date + "\n" + startTime + "\n" + location + "\n" + description + "\n" + eventName + "\n" + bus.toString();
    }

}