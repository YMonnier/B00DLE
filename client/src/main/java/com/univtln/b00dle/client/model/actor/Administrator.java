package com.univtln.b00dle.client.model.actor;

import com.univtln.b00dle.client.model.poll.Poll;

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

    public void creerSondage(){

    }

    public void cloturerSondage(){

    }

    public void modifierSondage(){

    }

    public void envoyerSondage(Poll poll, List<String> mails){

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
