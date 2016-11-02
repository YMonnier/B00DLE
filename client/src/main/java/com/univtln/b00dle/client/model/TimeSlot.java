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
}
