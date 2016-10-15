package com.univtln.b00dle.client.model.actor;

import com.univtln.b00dle.client.model.poll.Poll;

/**
 * Created by sbellange845 on 15/10/16.
 */
public class User {

    private String MACAdresse;

    public User(String MACAdresse) {
        this.MACAdresse = MACAdresse;
    }

    public void repondre(Poll poll, String name){

    }

    public void supprimerReponse(Poll poll){

    }

    public void consulter(Poll poll){

    }

    public void communiquer(String message){

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
