package com.univtln.b00dle.client.model.boodle.actor;

import com.univtln.b00dle.client.model.boodle.poll.Poll;
import com.univtln.b00dle.client.model.boodle.poll.Response;
import com.univtln.b00dle.client.utilities.network.InetAddress;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class User {

    /**
     * MAC adress get by Utilities class
     * Can identify user
     */
    private String MACAdresse;

    public User() {
        this.MACAdresse = InetAddress.getMacAdresse();
    }

    /**
     * Add a Response in poll
     * @param poll
     * @param response
     */
    public void answerPoll(Poll poll, Response response){
        poll.getResponses().add(response);
    }

    /**
     * Remove a Response in a poll
     * @param poll
     */
    public void removeResponse(Poll poll){
        poll.getResponses().remove(poll);
    }

    /**
     *
     * @param poll
     */
    public void consultPoll(Poll poll){

    }

    /**
     * Add a message in chat
     * @param poll
     * @param message
     */
    public void communicate(Poll poll, String message){
        poll.getChat().add(message);
    }

    @Override
    public String toString() {
        return MACAdresse;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof User){
            return this.MACAdresse == ((User)o).MACAdresse;
        }
        else{
            return super.equals(o);
        }
    }

    @Override
    public int hashCode() {
        return MACAdresse.hashCode();
    }

    public String getMACAdresse() {
        return MACAdresse;
    }
}
