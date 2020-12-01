package dk.via.sep.shared.transfer;

import java.io.Serializable;

public class Event implements Serializable {
    private final String startDate;
    private final String endDate;
    private final String location;
    private final String description;
    private final String name;
    private Bus bus;

    public Event(String name, String startDate, String endDate, String location, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
    }

    public Event(String name, String startDate, String endDate, String location, String description, Bus bus) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.bus = bus;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Bus getBus() {
        return bus;
    }
}
