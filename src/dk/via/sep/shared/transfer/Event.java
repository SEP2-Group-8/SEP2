package dk.via.sep.shared.transfer;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Event implements Serializable {
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private String location;
    private String description;
    private String eventName;
    private int eventId;
    private Bus bus;

    public Event(Date startDate, Time startTime, Date endDate, Time endTime, String location, String description, String eventName, Bus bus) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
        this.eventName = eventName;
        this.bus = bus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Time getEndTime() {
        return endTime;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
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

    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return Objects.equals(startDate, event.startDate) && Objects.equals(startTime, event.startTime) && Objects.equals(endDate, event.endDate)
                &&Objects.equals(endTime,event.endTime) && Objects.equals(location, event.location) && Objects.equals(description, event.description)
                && Objects.equals(eventName, event.eventName);
    }

    public String toString()
    {
        return startDate + "\n" + startTime + "\n" + endDate + "\n" + endTime + "\n" + location + "\n" + description + "\n" + eventName + "\n" + bus.toString();
    }

}