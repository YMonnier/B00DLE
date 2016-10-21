package com.univtln.b00dle.client.model.boodle.poll;

import com.univtln.b00dle.client.model.boodle.actor.Administrator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class Poll {

    private String link;
    private String name;
    private String description;
    private String place;
    private List<String> mails;
    private List<Date> dates;
    private List<Response> responses;
    private List<String> chat = new ArrayList<String>();
    private Administrator administrator;

    public class Builder{
        private String link;
        private String name;
        private String description;
        private String place;
        private List<String> mails;
        private List<Date> dates;
        private List<Response> responses;
        private Administrator administrator;

        public Builder(String link, String name, String description, String place, Administrator administrator, List<String> mails, List<Date> dates) {
            this.link = link;
            this.name = name;
            this.description = description;
            this.place = place;
            this.mails = mails;
            this.dates = dates;
            this.administrator = administrator;
        }

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
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

        public Builder setMails(List<String> mails) {
            this.mails = mails;
            return this;
        }

        public Builder setDates(List<Date> dates) {
            this.dates = dates;
            return this;
        }

        public Poll build(){
            return new Poll(link, name, description, place, administrator, dates, mails);
        }
    }

    public Poll(String link, String name, String description, String place, Administrator administrator){
        this.link = link;
        this.name = name;
        this.description = description;
        this.place = place;
        this.administrator = administrator;
    }

    public Poll(String link, String name, String description, String place, Administrator administrator, List<Date> dates) {
        this(link, name, description, place, administrator);
        this.dates = dates;
    }

    public Poll(String link, String name, String description, String place, Administrator administrator, List<Date> dates, List<String> mails ) {
        this(link, name, description, place, administrator, dates);
        this.mails = mails;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Poll){
            return this.link == ((Poll)o).link;
        }
        else{
            return super.equals(o);
        }
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List getMails() {
        return mails;
    }

    public void setMails(List mails) {
        this.mails = mails;
    }

    public List<Date> getDates() {
        return dates;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public List<String> getChat(){ return chat; }

    public Administrator getAdministrator(){
        return administrator;
    }

}
