package com.univtln.b00dle.client.model.boodle;

import com.univtln.b00dle.client.model.boodle.actor.Administrator;
import com.univtln.b00dle.client.model.boodle.poll.Poll;

import java.util.*;

/**
 * Created by Stéphen on 18/10/2016.
 */
public class Boodle {

    private Map<String, Poll> listPoll;

    public Boodle(){
        this.listPoll = new HashMap<String, Poll>();
        /** Add Poll for test view */
        Poll poll1 = new Poll("484f74", "Sondage 1", "Description sondage 1", "Toulon", new Administrator("s@s.fr", "ok"));
        Poll poll2 = new Poll("esdg848r4", "Sondage 2", "Description sondage 2", "Toulon", new Administrator("s@s.fr", "ok"));
        listPoll.put(poll1.getLink(), poll1);
        listPoll.put(poll2.getLink(), poll2);
    }

    public List<String> getChatMessage(String link){
        return listPoll.get(link).getChat();
    }

    public void addChatMessage(String link, String message) {
        listPoll.get(link).getChat().add(message);
    }

    public Poll getPollByLink(String link) {
        return listPoll.get(link);
    }

    public Map<String, Poll> getListPoll(){
        return listPoll;
    }

    //Error java.util.hashmapNode
    public List<Poll> getPoll(String email){
        List<Poll> listPollAdministrator = new ArrayList<>();
        for(Map.Entry<String, Poll> node : listPoll.entrySet()){
            if(node.getValue().getAdministrator().getMail().equals(email)){
                listPollAdministrator.add(node.getValue());
            }
        }
        return listPollAdministrator;
    }
}
