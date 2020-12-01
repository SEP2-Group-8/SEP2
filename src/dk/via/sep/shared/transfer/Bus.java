package dk.via.sep.shared.transfer;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Bus implements Serializable {
    private int noSeats;
    private Date departDate;
    private Time departTime;
    private Date arriveDate;
    private Time arriveTime;
    private String departLocation;
    private String arriveLocation;
    private int busId;

    public Bus(int noSeats, Date departDate, Time departTime, Date arriveDate, Time arriveTime, String departLocation, String arriveLocation) {
        this.noSeats = noSeats;
        this.departDate = departDate;
        this.departTime = departTime;
        this.arriveDate = arriveDate;
        this.arriveTime = arriveTime;
        this.departLocation = departLocation;
        this.arriveLocation = arriveLocation;
    }

    public int getNoSeats() {
        return noSeats;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public Time getDepartTime() {
        return departTime;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public Time getArriveTime() {
        return arriveTime;
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

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
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
}
