package com.unitln.b00dle.client.controller;

import com.google.gson.Gson;
import com.univtln.b00dle.client.model.OpinionPoll;
import com.univtln.b00dle.client.model.TimeSlot;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Project client.
 * Package com.unitln.b00dle.client.controller.
 * File OpinionTests.java.
 * Created by Ysee on 03/11/2016 - 20:47.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class OpinionTests extends TestCase
{
    OpinionPoll opinionPoll;

    String title = "Title 1";
    String description = "My description";
    String place = "Paris";
    String email = "test@test.org";
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OpinionTests(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( OpinionTests.class );
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TimeSlot t1 = new TimeSlot.Builder()
                .setFrom("2017-02-13 08:00")
                .setTo("2017-02-13 10:00")
                .build();
        t1.setId(1);


        TimeSlot t2 = new TimeSlot.Builder()
                .setFrom("2017-02-20 08:00")
                .setTo("2017-02-20 10:00")
                .build();
        t2.setId(2);

        opinionPoll = new OpinionPoll.Builder()
                .setTitle(title)
                .setDescription(description)
                .setPlace(place)
                .build();

        opinionPoll.getTimeSlots().add(t1);
        opinionPoll.getTimeSlots().add(t2);

        opinionPoll.getInvitations().add(new String(email));
    }

    public void testOptionPoll() {
        assertEquals("title should be the same", title, opinionPoll.getTitle());
        assertEquals("description should be the same", description, opinionPoll.getDescription());
        assertEquals("place should be the same", place, opinionPoll.getPlace());
        assertEquals("The timeSlots size should be 2", 2, opinionPoll.getTimeSlots().size());
        assertFalse(opinionPoll.isClose());
    }

    public void testObjectToJson() {
        Gson gson = new Gson();
        String json = gson.toJson(opinionPoll);
        System.out.println(json);

        OpinionPoll op = gson.fromJson(json, OpinionPoll.class);
        System.out.println(op);
    }
}
