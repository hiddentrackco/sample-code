package co.hiddentrack.partners_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;


public class Calendar {
    private String uid;
    private String name;
    private String description;
    @JsonProperty("time_zone")
    private String timeZone;
    @JsonProperty("etag")
    private String eTag;
    private List<Event> events;

    public Calendar(String name, String description) {
        this.uid = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.timeZone = "Asia/Seoul";
        this.eTag = UUID.randomUUID().toString();
        this.events = new ArrayList<Event>();
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.eTag = UUID.randomUUID().toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.eTag = UUID.randomUUID().toString();
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        this.eTag = UUID.randomUUID().toString();
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        event.setCalendarUid(this.uid);
        events.add(event);
    }
}
