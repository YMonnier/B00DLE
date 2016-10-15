package com.univtln.b00dle.client.model.poll;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class Response {

    private String MACAdresseUser;
    private String linkPoll;
    private Date date;

    public Response(String MACAdresseUser, String linkPoll, Date date) {
        this.MACAdresseUser = MACAdresseUser;
        this.linkPoll = linkPoll;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lien du sondage : " + linkPoll + " de " + MACAdresseUser + " - " + date;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Response){
            return this.linkPoll == ((Response)o).linkPoll && this.date == ((Response)o).date;
        }
        else{
            return super.equals(o);
        }
    }

    /* A revoir */
    @Override
    public int hashCode() {
        return linkPoll.hashCode() * 31 + date.hashCode();
    }

    public String getMACAdresseUser() {
        return MACAdresseUser;
    }

    public String getLinkPoll() {
        return linkPoll;
    }
}
