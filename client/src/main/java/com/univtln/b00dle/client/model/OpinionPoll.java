package com.univtln.b00dle.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Project client.
 * Package com.univtln.b00dle.client.model.
 * File OpinionPoll.java.
 * Created by Ysee on 02/11/2016 - 14:54.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class OpinionPoll {
    private int id;
    private String title;
    private String description;
    private String place;
    private boolean close;
    private List<TimeSlot> timeSlots;
    private List<Invitation> invitations;
    private List<Answer> answers;

    private OpinionPoll(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.place = builder.place;
        this.close = builder.close;
        this.timeSlots = builder.timeSlots;
        this.invitations = builder.invitations;
        this.answers = builder.answers;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public boolean isClose() {
        return close;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public static class Builder {
        private String title;
        private String description;
        private String place;
        private boolean close = false;
        private List<TimeSlot> timeSlots = new ArrayList<>();
        private List<Invitation> invitations = new ArrayList<>();
        private List<Answer> answers;


        public OpinionPoll build() {
            return new OpinionPoll(this);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPlace(String place) {
            this.place = place;
            return this;
        }

        public Builder setTimeSlots(List<TimeSlot> timeSlots) {
            this.timeSlots = timeSlots;
            return this;
        }

        public Builder setAnswers(List<Answer> answers) {
            this.answers = answers;
            return this;
        }

        public Builder setInvitations(List<Invitation> invitations) {
            this.invitations = invitations;
            return this;
        }
    }

    @Override
    public String toString() {
        return "OpinionPoll{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                ", close=" + close +
                ", timeSlots=" + timeSlots +
                ", invitations=" + invitations +
                ", answers=" + answers +
                '}';
    }
}
