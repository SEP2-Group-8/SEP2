package dk.via.sep.shared.transfer;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Bus implements Serializable {
    

    public Bus(int noSeats, Date departingDate, Time departTime, Date arrivingDate, Time arriveTime, String departLocation, String arriveLocation) {
    }

    public  int getSeats() {
        return 1;
    }

    public  Time getDepartTime() {
        return null;
    }

    public  Time getArriveTime() {
        return null;
    }

    public  String getDepartLocation(){
        return null;

    }

    public  String getArriveLocation() {
        return null;
    }

    public Date getDepartDate() {
        return null;
    }

    public Date getArriveDate() {
        return null;
    }

    public void setID(int busID) {
    }
}
