package dk.via.sep.shared.transfer;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

public class Bus implements Serializable {
    private int noSeats;
    private Time departTimeStart;
    private Time departTimeEnd;
    private Time arriveTimeStart;
    private Time arriveTimeEnd;
    private String departLocation;
    private String arriveLocation;
    private int busId;

    private int occupiedSeats;


    public Bus(int noSeats, Time departTimeStart, Time departTimeEnd, Time arriveTimeStart, Time arriveTimeEnd, String departLocation, String arriveLocation) {
        this.noSeats = noSeats;
        this.departTimeStart = departTimeStart;
        this.departTimeEnd = departTimeEnd;
        this.arriveTimeStart = arriveTimeStart;
        this.arriveTimeEnd = arriveTimeEnd;
        this.departLocation = departLocation;
        this.arriveLocation = arriveLocation;
        this.occupiedSeats = 0;
    }

    public int getNoSeats() {
        return noSeats;
    }

    public Time getDepartTimeStart() {
        return departTimeStart;
    }

    public Time getArriveTimeStart() {
        return arriveTimeStart;
    }

    public Time getDepartTimeEnd() {
        return departTimeEnd;
    }

    public Time getArriveTimeEnd() {
        return arriveTimeEnd;
    }

    public String getDepartLocation() {
        return departLocation;
    }

    public String getArriveLocation() {
        return arriveLocation;
    }

    public int getBusId() {
        return busId;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public void setDepartTimeStart(Time departTimeStart) {
        this.departTimeStart = departTimeStart;
    }

    public void setArriveTimeStart(Time arriveTimeStart) {
        this.arriveTimeStart = arriveTimeStart;
    }

    public void setDepartLocation(String departLocation) {
        this.departLocation = departLocation;
    }

    public void setArriveLocation(String arriveLocation) {
        this.arriveLocation = arriveLocation;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public void setDepartTimeEnd(Time departTimeEnd) {
        this.departTimeEnd = departTimeEnd;
    }

    public void setArriveTimeEnd(Time arriveTimeEnd) {
        this.arriveTimeEnd = arriveTimeEnd;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bus bus = (Bus) obj;
        return Objects.equals(noSeats, bus.noSeats) && Objects.equals(departLocation, bus.departLocation) && Objects.equals(arriveLocation, bus.arriveLocation) /*&& Objects.equals(busId, bus.busId)*/
                && Objects.equals(departTimeStart, bus.departTimeStart) && Objects.equals(arriveTimeStart, bus.arriveTimeStart);
    }

    public String toString() {
        return noSeats + "\n" + "\n" + arriveTimeStart + "\n" + departLocation + "\n" + arriveLocation + "\n" + busId;
    }
}