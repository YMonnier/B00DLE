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
     * @param builder builder pattern
     */
    public TimeSlot(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
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

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {
        private String from;
        private String to;

        public TimeSlot build() {
            return new TimeSlot(this);
        }

        public Builder setFrom(String from) {
            this.from = from;
            return this;
        }

        public Builder setTo(String to) {
            this.to = to;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (from != null ? !from.equals(timeSlot.from) : timeSlot.from != null) return false;
        return to != null ? to.equals(timeSlot.to) : timeSlot.to == null;

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.from + "\n" + this.to;
    }
}
