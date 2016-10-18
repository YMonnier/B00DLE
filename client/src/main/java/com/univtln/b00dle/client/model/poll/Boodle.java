package com.univtln.b00dle.client.model.poll;

import java.util.*;

/**
 * Created by Stéphen on 18/10/2016.
 */
public class Boodle {

    private Map<String, Poll> listPoll;

    public Boodle(){
        this.listPoll = new HashMap<String, Poll>();
        /** Add Poll for test view */
        Poll poll1 = new Poll("484f74", "Sondage 1", "Description sondage 1", "Toulon");
        Poll poll2 = new Poll("esdg848r4", "Sondage 2", "Description sondage 2", "Toulon");
        listPoll.put(poll1.getLink(), poll1);
        listPoll.put(poll2.getLink(), poll2);
    }

    public List<String> getChatMessage(String link){
        return listPoll.get(link).getChat();
    }

    public void addChatMessage(String link, String message) {
        listPoll.get(link).getChat().add(message);
    }

    public Poll getPoll(String link) {
        return listPoll.get(link);
    }
}
