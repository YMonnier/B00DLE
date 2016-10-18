package com.univtln.b00dle.client.model;

import com.univtln.b00dle.client.model.poll.Boodle;
import com.univtln.b00dle.client.model.poll.Poll;

import java.util.List;

/**
 * Created by Stéphen on 18/10/2016.
 */
public class Model {

    private Boodle boodle;

    public Model(){
        this.boodle = new Boodle();
    }

    public List<String> getChatMessage(String link){
        return boodle.getChatMessage(link);
    }

    public void addChatMessage(String link, String message) {
        boodle.addChatMessage(link, message);
    }

    public Poll getPoll(String link){
        return boodle.getPoll(link);
    }
}
