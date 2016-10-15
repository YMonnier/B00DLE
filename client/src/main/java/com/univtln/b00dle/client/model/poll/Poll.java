package com.univtln.b00dle.client.model.poll;

import java.util.List;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class Poll {

    private String link;
    private String name;
    private String description;
    private String place;
    private List mails;
    private List dates;

    private List<Response> reponses;

    public class Builder{
        private String link;
        private String name;
        private String description;
        private String place;
        private List mails;
        private List dates;

        public Builder(String link, String name, String description, String place, List mails, List dates) {
            this.link = link;
            this.name = name;
            this.description = description;
            this.place = place;
            this.mails = mails;
            this.dates = dates;
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

        public Builder setMails(List mails) {
            this.mails = mails;
            return this;
        }

        public Builder setDates(List dates) {
            this.dates = dates;
            return this;
        }

        public Poll build(){
            return new Poll(link, name, description, place, mails, dates);
        }
    }

    public Poll(String link, String name, String description, String place, List mails, List dates) {
        this.link = link;
        this.name = name;
        this.description = description;
        this.place = place;
        this.mails = mails;
        this.dates = dates;
    }

    public void faireChoixDates(){

    }

    @Override
    public String toString() {
        return "Sondage " + link + " nom : " + name;
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

    public List getDates() {
        return dates;
    }

    public List<Response> getReponses() {
        return reponses;
    }

    public void setReponses(List<Response> reponses) {
        this.reponses = reponses;
    }
}
