package dk.via.sep.shared.transfer;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Event implements Serializable {
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private String location;
    private String description;
    private String eventName;
    private int eventId;
    private int busId;

    public Event(Date startDate, Time startTime, Date endDate, Time endTime, String location, String description, String eventName, int busId) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
        this.eventName = eventName;
        this.busId = busId;
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

    public int getBusId() {
        return busId;
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

    public void setBusId(int busId) {
        this.busId = busId;
    }
}
