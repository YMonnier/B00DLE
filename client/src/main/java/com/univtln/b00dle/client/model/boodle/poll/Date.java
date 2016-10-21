package com.univtln.b00dle.client.model.boodle.poll;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class Date {

    private String departureDate;
    private String departureTime;
    private String endDate;
    private String endTime;

    public Date(String departureDate, String departureTime, String endDate, String endTime) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return departureDate + " - " + departureTime + "/" + endDate + " - " + endTime;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Date){
            return this.departureDate == ((Date)o).departureDate
                    && this.departureTime == ((Date)o).departureTime
                    && this.endDate == ((Date)o).endDate
                    && this.endTime == ((Date)o).endTime;
        }
        else{
            return super.equals(o);
        }

    }

    /* A revoir */
    @Override
    public int hashCode() {
       return departureDate.hashCode() * 31 + departureTime.hashCode();
    }
}
