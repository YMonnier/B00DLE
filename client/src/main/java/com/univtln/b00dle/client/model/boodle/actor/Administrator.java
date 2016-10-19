package com.univtln.b00dle.client.model.boodle.actor;

import com.univtln.b00dle.client.model.boodle.Boodle;
import com.univtln.b00dle.client.model.boodle.poll.Poll;

import java.util.List;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class Administrator {

    private String mail;
    private String password;

    public Administrator(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public void createPoll(Poll poll, Boodle boodle){
        boodle.getListPoll().put(poll.getLink(), poll);
    }

    public void removePoll(Poll poll, Boodle boodle){
        boodle.getListPoll().remove(poll);
    }

    public void modifyPoll(Poll poll, Boodle boodle){
        Poll ancienPoll = boodle.getPollByLink(poll.getLink());
        ancienPoll.setDescription(poll.getDescription());
        ancienPoll.setMails(poll.getMails());
        ancienPoll.setName(poll.getName());
        ancienPoll.setPlace(poll.getPlace());
    }

    public void sendPoll(Poll poll, List<String> mails){

    }

    @Override
    public String toString() {
        return mail;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Administrator){
            return this.mail == ((Administrator)o).mail;
        }
        else{
            return super.equals(o);
        }
    }

    @Override
    public int hashCode() {
        return mail.hashCode();
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
