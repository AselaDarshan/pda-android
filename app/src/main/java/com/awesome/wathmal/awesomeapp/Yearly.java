package com.awesome.wathmal.awesomeapp;

/**
 * Created by wathmal on 3/28/15.
 */
public class Yearly {

    private int id;
    private int eventId;

    public Yearly() {
    }

    public Yearly(int eventId) {
        this.eventId = eventId;
    }

    public Yearly(int id, int eventId) {
        this.id = id;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
