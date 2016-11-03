package com.univtln.b00dle.client.model;

import java.util.List;

/**
 * Project client.
 * Package com.univtln.b00dle.client.model.
 * File Answer.java.
 * Created by Ysee on 02/11/2016 - 15:00.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class Answer {
    private String appId;
    private String name;
    private int opinionPollId;
    private List<Integer> timeSlots;

    private Answer(Builder builder)
    {
        this.name = builder.name;
        this.opinionPollId = builder.opinionPollId;
        this.timeSlots = builder.timeSlots;
    }

    public String getAppId() {
        return appId;
    }

    public String getName() {
        return name;
    }

    public int getOpinionPollId() {
        return opinionPollId;
    }

    public List<Integer> getTimeSlots() {
        return timeSlots;
    }

    public static class Builder {
        private String name;
        private int opinionPollId;
        private List<Integer> timeSlots;

        public Answer build() {
            return new Answer(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setOpinionPollId(int opinionPollId) {
            this.opinionPollId = opinionPollId;
            return this;
        }

        public Builder setTimeSlots(List<Integer> timeSlots) {
            this.timeSlots = timeSlots;
            return this;
        }
    }
}
