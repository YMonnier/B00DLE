package com.univtln.b00dle.client.model;

/**
 * Project client.
 * Package com.univtln.b00dle.client.model.
 * File TimeSlot.java.
 * Created by Ysee on 02/11/2016 - 14:57.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class TimeSlot {
    /**
     * Unique ID
     */
    private int id;

    /**
     * Date from
     */
    private String from;

    /**
     * Date to
     */
    private String to;

    /**
     * CReate a new time slot.
     * @param from date from.
     * @param to date to.
     */
    public TimeSlot(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
}
