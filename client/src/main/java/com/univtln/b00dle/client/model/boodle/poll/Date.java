package com.univtln.b00dle.client.model.boodle.poll;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class Date {

    private Date date;
    private Date time;

    public Date(Date date, Date time) {
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return date + " - " + time;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Date){
            return this.date == ((Date)o).date && this.time == ((Date)o).time;
        }
        else{
            return super.equals(o);
        }

    }

    /* A revoir */
    @Override
    public int hashCode() {
       return date.hashCode() * 31 + time.hashCode();
    }
}
