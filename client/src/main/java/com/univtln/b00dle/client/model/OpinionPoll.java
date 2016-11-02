package com.univtln.b00dle.client.model;

import java.util.List;

/**
 * Project client.
 * Package com.univtln.b00dle.client.model.
 * File OpinionPoll.java.
 * Created by Ysee on 02/11/2016 - 14:54.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class OpinionPoll {
    private int id;
    private String title;
    private String description;
    private String place;
    private boolean close;
    List<TimeSlot> timeSlots;
    private List<Invitation> invitations;

}
