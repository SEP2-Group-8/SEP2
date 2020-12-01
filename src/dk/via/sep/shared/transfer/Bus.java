package dk.via.sep.shared.transfer;

import java.io.Serializable;

public class Bus implements Serializable {
    private final String seats;
    private final String departTime;
    private final String arriveTime;
    private final String departLocation;
    private final String arriveLocation;

    public Bus(String seats, String departTime, String arriveTime, String departLocation, String arriveLocation) {
        this.seats = seats;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.departLocation = departLocation;
        this.arriveLocation = arriveLocation;
    }

    public String getSeats() {
        return seats;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public String getDepartLocation() {
        return departLocation;
    }

    public String getArriveLocation() {
        return arriveLocation;
    }

}
